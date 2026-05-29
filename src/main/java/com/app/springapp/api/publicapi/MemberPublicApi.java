package com.app.springapp.api.publicapi;

import com.app.springapp.domain.dto.MemberDTO;
import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Tag(name = "회원 공개 API", description = "비로그인 접근 가능한 회원 기본 정보 조회")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberPublicApi {

    private final MemberService memberService;

    @Operation(summary = "회원 공개 정보 조회", description = "다른 사람의 프로필 페이지에서 닉네임/프로필 이미지를 표시하는 데 사용.")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO> getMemberPublicInfo(@PathVariable Long id) {
        com.app.springapp.domain.dto.response.MemberResponseDTO dto =
                (com.app.springapp.domain.dto.response.MemberResponseDTO) memberService.me(id).getData();
        Map<String, Object> pub = new HashMap<>();
        pub.put("memberNickname", dto.getMemberNickname());
        pub.put("memberProfileImageUrl", dto.getMemberPicture());
        return ResponseEntity.ok(ApiResponseDTO.of(true, "회원 공개 정보 조회 성공", pub));
    }
}
