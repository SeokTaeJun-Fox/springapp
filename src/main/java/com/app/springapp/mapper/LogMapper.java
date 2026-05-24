package com.app.springapp.mapper;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.domain.dto.response.LogPopularSolutionResponseDTO;
import com.app.springapp.domain.dto.response.LogResponseDTO;
import com.app.springapp.domain.vo.LogVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

// 로그 관련 SQL을 처리하는 Mapper 인터페이스
@Mapper
public interface LogMapper {

    // 전체 로그 목록 조회 (정렬 기준, 페이징 적용)
    public List<LogListResponseDTO> selectLogList(
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    // 키워드로 로그 목록 조회 (제목 기준 검색, 페이징 적용)
    public List<LogListResponseDTO> selectLogListByKeyword(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    // 카테고리로 로그 목록 조회 (페이징 적용)
    public List<LogListResponseDTO> selectLogListByCategory(
            @Param("category") String category,
            @Param("offset") int offset,
            @Param("size") int size,
            @Param("sort") String sort
    );

    // 회원 ID로 내 로그 목록 전체 조회
    List<LogListResponseDTO> findAllByMemberId(Long memberId);
    // 로그 작성
    void insert(LogVO logVO);

    // 로그 상세 조회
    Optional<LogResponseDTO> selectById(Long id);


    // 좋아요 10개 이상인 인기 솔루션 조회 (로그 목록 페이지 노출용)
    List<LogPopularSolutionResponseDTO> selectPopularSolutions();


    // 조회수 +1
    void increaseReadCount(Long id);

}