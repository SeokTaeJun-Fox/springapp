package com.app.springapp.repository;

import com.app.springapp.domain.dto.request.PostReportCreateRequestDTO;
import com.app.springapp.mapper.PostReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostReportDAO {

    private final PostReportMapper postReportMapper;

    public void save(PostReportCreateRequestDTO postReportCreateRequestDTO) {
        postReportMapper.insert(postReportCreateRequestDTO);
    }
}
