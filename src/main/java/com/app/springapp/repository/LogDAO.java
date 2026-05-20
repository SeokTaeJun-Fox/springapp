package com.app.springapp.repository;

import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.mapper.LogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LogDAO {
    private final LogMapper logMapper;

    //  로그 목록 조회
    public List<LogListResponseDTO> findLogList(int offset, int size, String sort) {
        return logMapper.selectLogList(offset, size, sort);
    }

    //  키워드 검색
    public List<LogListResponseDTO> findLogListByKeyword(String keyword, int offset, int size, String sort) {
        return logMapper.selectLogListByKeyword(keyword, offset, size, sort);
    }

    // 카테고리 검색
    public List<LogListResponseDTO> findLogListByCategory(String category, int offset, int size, String sort) {
        return logMapper.selectLogListByCategory(category, offset, size, sort);
    }
}