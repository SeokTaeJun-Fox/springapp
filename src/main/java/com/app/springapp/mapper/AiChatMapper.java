package com.app.springapp.mapper;

import com.app.springapp.domain.vo.AiChatVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface AiChatMapper {
    void insertAiChat(@Param("memberId") Long memberId,
                      @Param("content") String content,
                      @Param("role") String role);

    List<AiChatVO> selectChatHistory(@Param("memberId") Long memberId);
}