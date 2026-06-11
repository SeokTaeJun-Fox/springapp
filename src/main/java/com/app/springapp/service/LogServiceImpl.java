package com.app.springapp.service;

import com.app.springapp.domain.dto.request.LogCreateRequestDTO;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.dto.response.LogPopularSolutionResponseDTO;
import com.app.springapp.domain.dto.response.LogResponseDTO;
import com.app.springapp.domain.vo.LogVO;
import com.app.springapp.domain.vo.LogLikeVO;
import com.app.springapp.domain.dto.request.LogLikeRequestDTO;
import com.app.springapp.domain.dto.response.LogLikeResponseDTO;
import com.app.springapp.mapper.LogLikeMapper;
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
    private final LogLikeMapper logLikeMapper;

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
     * 회원 ID로 내 로그 목록 전체 조회 (삭제된 로그 제외)
     * - 프로젝트 생성 모달에서 사용자의 로그 목록을 표시할 때 사용
     *
     * @param memberId 현재 로그인한 회원 ID
     * @return 내 로그 목록 (ApiResponseDTO)
     */
    @Override
    public ApiResponseDTO getMyLogList(Long memberId) {
        List<LogListResponseDTO> list = logDAO.findAllByMemberId(memberId);
        return new ApiResponseDTO(true, "내 로그 목록 조회 성공", list);
    }

    // 회원 ID로 내 휴지통 로그 목록 전체 조회
    @Override
    public ApiResponseDTO getTrashedLogList(Long memberId) {
        List<LogListResponseDTO> list = logDAO.findAllTrashedByMemberId(memberId);
        return new ApiResponseDTO(true, "내 휴지통 로그 목록 조회 성공", list);
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

    // 로그 수정 (임시저장 덮어쓰기)
    @Override
    public ApiResponseDTO updateLog(Long id, com.app.springapp.domain.dto.request.LogUpdateRequestDTO dto, Long memberId) {
        LogResponseDTO existingLog = logDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 로그입니다."));

        if (!existingLog.getMemberId().equals(memberId)) {
            return new ApiResponseDTO(false, "본인이 작성한 로그만 수정할 수 있습니다.", null);
        }

        LogVO logVO = new LogVO();
        logVO.setId(id);
        logVO.setMemberId(memberId);
        logVO.setLogTitle(dto.getLogTitle() != null ? dto.getLogTitle() : existingLog.getLogTitle());
        logVO.setVisionTitle(dto.getVisionTitle() != null ? dto.getVisionTitle() : existingLog.getVisionTitle());
        logVO.setLogContent(dto.getLogContent() != null ? dto.getLogContent() : existingLog.getLogContent());
        logVO.setLogThumbnailUrl(dto.getLogThumbnailUrl() != null ? dto.getLogThumbnailUrl() : existingLog.getLogThumbnailUrl());
        logVO.setCategoryId(dto.getCategoryId() != null ? dto.getCategoryId() : existingLog.getCategoryId());
        logVO.setLogStatus(dto.getLogStatus() != null ? dto.getLogStatus() : existingLog.getLogStatus());
        logVO.setLogProgress(dto.getLogProgress() != null ? dto.getLogProgress() : existingLog.getLogProgress());

        logDAO.update(logVO);
        return ApiResponseDTO.of(true, "로그 수정 성공", logVO.getId());
    }

    // 로그 상세 조회 (조건부 조회수 +1)
    @Override
    @Transactional
    public ApiResponseDTO getLog(Long id, Long memberId, boolean shouldIncreaseReadCount) {
        LogResponseDTO log = logDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 로그입니다."));
        
        // 본인이 아니고 쿠키가 없을 때만 조회수 증가
        if (memberId != null && memberId.equals(log.getMemberId())) {
            log.setAuthor(true);
        } else {
            log.setAuthor(false);
            if (shouldIncreaseReadCount) {
                logDAO.increaseReadCount(id);
                log.setLogReadCount(log.getLogReadCount() + 1);
            }
        }
        
        return ApiResponseDTO.of(true, "로그 상세 조회 성공", log);
    }

    // 인기 솔루션 조회 (좋아요 10개 이상, 로그 목록 페이지 노출용)
    @Override
    public ApiResponseDTO getPopularSolutions() {
        List<LogPopularSolutionResponseDTO> list = logDAO.findPopularSolutions();
        return ApiResponseDTO.of(true, "인기 솔루션 조회 성공", list);
    }

    // 로그 좋아요 토글
    @Override
    public ApiResponseDTO toggleLike(Long logId, Long memberId) {
        LogLikeRequestDTO requestDTO = new LogLikeRequestDTO(logId, memberId);
        LogLikeResponseDTO responseDTO = logLikeMapper.selectLikeCountAndIsLiked(requestDTO);

        LogLikeVO vo = new LogLikeVO();
        vo.setLogId(logId);
        vo.setMemberId(memberId);

        if (responseDTO.getIsLiked() == 1) {
            // 이미 좋아요를 누른 상태라면 취소
            logLikeMapper.delete(vo);
            responseDTO.setIsLiked(0);
            responseDTO.setLikeCount(responseDTO.getLikeCount() - 1);
        } else {
            // 좋아요를 누르지 않은 상태라면 추가
            logLikeMapper.insert(vo);
            responseDTO.setIsLiked(1);
            responseDTO.setLikeCount(responseDTO.getLikeCount() + 1);
        }

        return ApiResponseDTO.of(true, "좋아요 토글 성공", responseDTO);
    }

    // 로그 다중 소프트 삭제
    @Override
    public ApiResponseDTO deleteLogs(List<Long> ids, Long memberId) {
        if (ids == null || ids.isEmpty()) {
            return new ApiResponseDTO(false, "휴지통으로 이동할 로그 ID가 없습니다.", null);
        }
        
        for (Long id : ids) {
            LogResponseDTO log = logDAO.findById(id).orElse(null);
            if (log != null && !log.getMemberId().equals(memberId)) {
                return new ApiResponseDTO(false, "본인이 작성한 로그만 휴지통으로 이동할 수 있습니다.", null);
            }
        }

        logDAO.deleteLogs(ids);
        return new ApiResponseDTO(true, "선택한 로그들이 휴지통으로 이동되었습니다.", null);
    }

    // 로그 다중 복원
    @Override
    public ApiResponseDTO restoreLogs(List<Long> ids, Long memberId) {
        if (ids == null || ids.isEmpty()) {
            return new ApiResponseDTO(false, "복원할 로그 ID가 없습니다.", null);
        }
        
        for (Long id : ids) {
            LogResponseDTO log = logDAO.findById(id).orElse(null);
            if (log != null && !log.getMemberId().equals(memberId)) {
                return new ApiResponseDTO(false, "본인이 작성한 로그만 복원할 수 있습니다.", null);
            }
        }

        logDAO.restoreLogs(ids);
        return new ApiResponseDTO(true, "선택한 로그들이 복원되었습니다.", null);
    }

    // 로그 다중 영구 삭제
    @Override
    public ApiResponseDTO hardDeleteLogs(List<Long> ids, Long memberId) {
        if (ids == null || ids.isEmpty()) {
            return new ApiResponseDTO(false, "삭제할 로그 ID가 없습니다.", null);
        }
        
        for (Long id : ids) {
            LogResponseDTO log = logDAO.findById(id).orElse(null);
            if (log != null && !log.getMemberId().equals(memberId)) {
                return new ApiResponseDTO(false, "본인이 작성한 로그만 삭제할 수 있습니다.", null);
            }
        }

        logDAO.hardDeleteLogs(ids);
        return new ApiResponseDTO(true, "선택한 로그들이 영구 삭제되었습니다.", null);
    }
}