package com.app.springapp.mapper;

import com.app.springapp.domain.vo.AiChatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

// AI 챗봇 대화 내역 관련 SQL을 처리하는 Mapper 인터페이스
@Mapper
public interface AiChatMapper {

    // 대화 메시지 저장 (사용자 또는 AI 메시지 구분하여 저장)
    void insertAiChat(@Param("memberId") Long memberId,
                      @Param("content") String content,
                      @Param("role") String role);

    // 회원별 대화 내역 전체 조회
    List<AiChatVO> selectChatHistory(@Param("memberId") Long memberId);
}