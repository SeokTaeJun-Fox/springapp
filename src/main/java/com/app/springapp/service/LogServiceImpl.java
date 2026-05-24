package com.app.springapp.service;

import com.app.springapp.domain.dto.request.LogCreateRequestDTO;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.dto.response.LogPopularSolutionResponseDTO;
import com.app.springapp.domain.dto.response.LogResponseDTO;
import com.app.springapp.domain.vo.LogVO;
import com.app.springapp.repository.LogDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// 로그 서비스 구현체 - 로그 목록 조회, 검색, 카테고리 필터, 내 로그 조회 비즈니스 로직 처리
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {

    private final LogDAO logDAO;

    /**
     * 전체 로그 목록 조회
     * - page와 size를 기반으로 offset을 계산하여 페이징 처리
     *
     * @param page 현재 페이지 번호 (0부터 시작)
     * @param size 페이지당 항목 수
     * @param sort 정렬 기준 (최신순/좋아요순/조회순)
     * @return 로그 목록 (ApiResponseDTO)
     */
    @Override
    public ApiResponseDTO getLogList(int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogList(offset, size, sort);
        return new ApiResponseDTO(true, "로그 목록 조회 성공", list);
    }

    /**
     * 키워드로 로그 목록 조회
     * - 제목 기준으로 검색하며 페이징 처리
     *
     * @param keyword 검색 키워드
     * @param page    현재 페이지 번호 (0부터 시작)
     * @param size    페이지당 항목 수
     * @param sort    정렬 기준 (최신순/좋아요순/조회순)
     * @return 검색된 로그 목록 (ApiResponseDTO)
     */
    @Override
    public ApiResponseDTO getLogListByKeyword(String keyword, int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogListByKeyword(keyword, offset, size, sort);
        return new ApiResponseDTO(true, "검색 결과 조회 성공", list);
    }

    /**
     * 카테고리로 로그 목록 조회
     * - 선택한 카테고리 기준으로 필터링하며 페이징 처리
     *
     * @param category 조회할 카테고리
     * @param page     현재 페이지 번호 (0부터 시작)
     * @param size     페이지당 항목 수
     * @param sort     정렬 기준 (최신순/좋아요순/조회순)
     * @return 카테고리별 로그 목록 (ApiResponseDTO)
     */
    @Override
    public ApiResponseDTO getLogListByCategory(String category, int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogListByCategory(category, offset, size, sort);
        return new ApiResponseDTO(true, "카테고리 조회 성공", list);
    }

    /**
     * 회원 ID로 내 로그 목록 전체 조회
     * - 프로젝트 생성 모달에서 사용자의 로그 목록을 표시할 때 사용
     *
     * @param memberId 현재 로그인한 회원 ID
     * @return 내 로그 목록 (ApiResponseDTO)
     */
    @Override
    public ApiResponseDTO getMyLogList(Long memberId) {
        List<LogListResponseDTO> list = logDAO.findAllByMemberId(memberId);
        return ApiResponseDTO.of(true, "내 로그 목록 조회 성공", list);
    }

    // 로그 작성
    @Override
    public ApiResponseDTO createLog(LogCreateRequestDTO dto, Long memberId) {
        LogVO logVO = new LogVO();
        logVO.setLogTitle(dto.getLogTitle());
        logVO.setVisionTitle(dto.getVisionTitle());
        logVO.setLogContent(dto.getLogContent());
        logVO.setLogThumbnailUrl(dto.getLogThumbnailUrl());
        logVO.setCategoryId(dto.getCategoryId());
        logVO.setLogStatus(dto.getLogStatus() != null ? dto.getLogStatus() : "PUBLISHED");
        logVO.setLogProgress(dto.getLogProgress() != null ? dto.getLogProgress() : 0);
        logVO.setMemberId(memberId);
        logDAO.save(logVO);
        return ApiResponseDTO.of(true, "로그 작성 성공", logVO.getId());
    }

    // 로그 상세 조회 (조회수 +1)
    @Override
    @Transactional
    public ApiResponseDTO getLog(Long id) {
        LogResponseDTO log = logDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 로그입니다."));
        logDAO.increaseReadCount(id);
        return ApiResponseDTO.of(true, "로그 상세 조회 성공", log);
    }

    // 인기 솔루션 조회 (좋아요 10개 이상, 로그 목록 페이지 노출용)
    @Override
    public ApiResponseDTO getPopularSolutions() {
        List<LogPopularSolutionResponseDTO> list = logDAO.findPopularSolutions();
        return ApiResponseDTO.of(true, "인기 솔루션 조회 성공", list);
    }


}