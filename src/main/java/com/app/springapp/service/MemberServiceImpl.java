package com.app.springapp.service;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.request.MemberPasswordUpdateRequestDTO;
import com.app.springapp.domain.dto.request.MemberPasswordVerifyRequestDTO;
import com.app.springapp.domain.dto.request.MemberUpdateRequestDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.MemberResponseDTO;
import com.app.springapp.domain.vo.MemberVO;
import com.app.springapp.domain.vo.SocialMemberVO;
import com.app.springapp.exception.MemberException;
import com.app.springapp.repository.MemberDAO;
import com.app.springapp.repository.SocialMemberDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class})
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;
    private final SocialMemberDAO socialMemberDAO;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplate restTemplate;

    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String naverClientId;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String naverClientSecret;

//    회원 가입
    @Override
    public ApiResponseDTO join(MemberDTO memberDTO) {
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO();
        Map<String, Object> claims = new HashMap<>();

        if(memberDAO.existsMemberByMemberEmailAndSocialMemberProvider(memberDTO)){
            throw new MemberException("중복된 이메일 입니다.", HttpStatus.BAD_REQUEST);
        }

        MemberVO memberVO = MemberVO.from(memberDTO);
        SocialMemberVO socialMemberVO = SocialMemberVO.from(memberDTO);

//        socialMemberVO.getSocialMemberProvider().equals("local")
        if("local".equals(socialMemberVO.getSocialMemberProvider())){
            memberVO.setMemberPassword(passwordEncoder.encode(memberDTO.getMemberPassword()));
        }

        memberDAO.save(memberVO);
        socialMemberVO.setMemberId(memberVO.getId());

        socialMemberDAO.save(socialMemberVO);

        claims.put("id", memberVO.getId());
        claims.put("memberEmail", memberVO.getMemberEmail());
        claims.put("memberProvider", socialMemberVO.getSocialMemberProvider());

        apiResponseDTO.setSuccess(true);
        apiResponseDTO.setMessage("회원가입이 완료되었습니다.");
        apiResponseDTO.setData(claims);

        return apiResponseDTO;
    }

    // 토큰 -> 회원 정보를 조회하는 서비스
    @Override
    public ApiResponseDTO me(Long id) {
        MemberDTO foundMember = memberDAO.findMemberById(id)
                .orElseThrow(() -> {
                    throw new MemberException("me 회원 조회 실패", HttpStatus.BAD_REQUEST);
                });

        MemberResponseDTO memberResponseDTO = MemberResponseDTO.from(foundMember);
        ApiResponseDTO apiResponseDTO = new ApiResponseDTO(true, "회원 조회 성공", memberResponseDTO);
        return apiResponseDTO;
    }

    @Override
    public ApiResponseDTO updatePicture(MemberVO memberVO) {
        Map<String, Object> datas = new HashMap<>();
        memberDAO.updatePicture(memberVO);
        datas.put("updatedMemberPictureUrl", memberVO.getMemberProfileImageUrl());
        return ApiResponseDTO.of(true, "사진 변경 완료", datas);
    }

    // 회원 정보 수정 (닉네임, 프로필 이미지, 전화번호)
    @Override
    public ApiResponseDTO update(Long id, MemberUpdateRequestDTO memberUpdateRequestDTO) {
        memberDAO.findMemberById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND));

        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        memberVO.setMemberNickname(memberUpdateRequestDTO.getMemberNickname());
        memberVO.setMemberProfileImageUrl(memberUpdateRequestDTO.getMemberProfileImageUrl());
        memberVO.setMemberPhone(memberUpdateRequestDTO.getMemberPhone());
        if (Boolean.TRUE.equals(memberUpdateRequestDTO.getVerifyPhone())) {
            memberVO.setMemberPhoneVerifiedAt("verified");
        }

        memberDAO.update(memberVO);
        return ApiResponseDTO.of(true, "회원 정보가 수정되었습니다.");
    }

    // 비밀번호 변경
    @Override
    public ApiResponseDTO updatePassword(Long id, MemberPasswordUpdateRequestDTO memberPasswordUpdateRequestDTO) {
        MemberDTO foundMember = memberDAO.findMemberById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(memberPasswordUpdateRequestDTO.getCurrentPassword(), foundMember.getMemberPassword())) {
            throw new MemberException("현재 비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        MemberVO memberVO = new MemberVO();
        memberVO.setId(id);
        memberVO.setMemberPassword(passwordEncoder.encode(memberPasswordUpdateRequestDTO.getNewPassword()));

        memberDAO.update(memberVO);
        return ApiResponseDTO.of(true, "비밀번호가 변경되었습니다.");
    }

    // 비밀번호 확인
    @Override
    public ApiResponseDTO verifyPassword(Long id, MemberPasswordVerifyRequestDTO memberPasswordVerifyRequestDTO) {
        MemberDTO foundMember = memberDAO.findMemberById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(memberPasswordVerifyRequestDTO.getPassword(), foundMember.getMemberPassword())) {
            throw new MemberException("비밀번호가 일치하지 않습니다.", HttpStatus.BAD_REQUEST);
        }

        return ApiResponseDTO.of(true, "비밀번호가 확인되었습니다.");
    }

    // 회원 탈퇴
    @Override
    public ApiResponseDTO withdraw(Long id) {
        memberDAO.findMemberById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND));

        socialMemberDAO.findByMemberId(id).ifPresent(this::unlinkSocialMember);
        socialMemberDAO.invalidateByMemberId(id);

        memberDAO.withdrawMember(id);
        return ApiResponseDTO.of(true, "회원 탈퇴가 완료되었습니다.");
    }

    // 소셜 플랫폼 연결 해제
    private void unlinkSocialMember(SocialMemberVO socialMemberVO) {
        String provider = socialMemberVO.getSocialMemberProvider();
        String accessToken = socialMemberVO.getSocialAccessToken();

        if ("local".equals(provider) || accessToken == null) {
            return;
        }

        try {
            switch (provider) {
                case "kakao" -> unlinkKakao(accessToken);
                case "google" -> revokeGoogle(accessToken);
                case "naver" -> unlinkNaver(accessToken);
                default -> log.warn("[회원 탈퇴] 알 수 없는 소셜 플랫폼: {}", provider);
            }
        } catch (Exception e) {
            log.warn("[회원 탈퇴] 소셜 플랫폼 연결 해제 실패 - provider: {}, message: {}", provider, e.getMessage());
        }
    }

    // 카카오 연결 해제
    private void unlinkKakao(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(new LinkedMultiValueMap<>(), headers);
        restTemplate.postForEntity("https://kapi.kakao.com/v1/user/unlink", request, String.class);
    }

    // 구글 토큰 해지
    private void revokeGoogle(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", accessToken);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);
        restTemplate.postForEntity("https://oauth2.googleapis.com/revoke", request, String.class);
    }

    // 네이버 연결 해제
    private void unlinkNaver(String accessToken) {
        String url = "https://nid.naver.com/oauth2.0/token"
                + "?grant_type=delete"
                + "&client_id=" + naverClientId
                + "&client_secret=" + naverClientSecret
                + "&access_token=" + accessToken
                + "&service_provider=NAVER";

        restTemplate.getForEntity(url, String.class);
    }

    // 이메일 핸들(@ 앞부분)로 공개 정보 조회
    @Override
    public ApiResponseDTO getByHandle(String handle) {
        MemberDTO foundMember = memberDAO.findMemberByEmailHandle(handle)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND));

        Map<String, Object> pub = new HashMap<>();
        pub.put("memberId", foundMember.getId());
        pub.put("memberNickname", foundMember.getMemberNickname());
        pub.put("memberProfileImageUrl", foundMember.getMemberPicture());
        pub.put("memberHandle", handle);

        return ApiResponseDTO.of(true, "회원 공개 정보 조회 성공", pub);
    }

    // ID -> 이메일 핸들(@ 앞부분) 조회
    @Override
    public ApiResponseDTO getHandle(Long id) {
        String handle = memberDAO.findHandleById(id)
                .orElseThrow(() -> new MemberException("존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND));

        Map<String, Object> data = new HashMap<>();
        data.put("memberHandle", handle);

        return ApiResponseDTO.of(true, "회원 핸들 조회 성공", data);
    }
}

















