package com.app.springapp.service;

import com.app.springapp.domain.dto.response.ApiResponseDTO;
import com.app.springapp.domain.dto.response.LogListResponseDTO;
import com.app.springapp.repository.LogDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogDAO logDAO;

    @Override
    public ApiResponseDTO getLogList(int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogList(offset, size, sort);
        return new ApiResponseDTO(true, "로그 목록 조회 성공", list);
    }

    @Override
    public ApiResponseDTO getLogListByKeyword(String keyword, int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogListByKeyword(keyword, offset, size, sort);
        return new ApiResponseDTO(true, "검색 결과 조회 성공", list);
    }

    @Override
    public ApiResponseDTO getLogListByCategory(String category, int page, int size, String sort) {
        int offset = page * size;
        List<LogListResponseDTO> list = logDAO.findLogListByCategory(category, offset, size, sort);
        return new ApiResponseDTO(true, "카테고리 조회 성공", list);
    }
}