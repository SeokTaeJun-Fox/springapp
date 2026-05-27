package com.app.springapp.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ChronologyMapper {

    String findNicknameByProjectId(Long projectId);

    int countCompletedChecklistsByProjectId(Long projectId);

    int findAvgCompletedChecklists();

    List<Map<String, Object>> findTop3ChecklistsByProjectId(Long projectId);

    int findAvgProjectDays();

    int findAvgProjectCount();

    int countMembersWithMoreChecklists(@Param("totalChecklists") int totalChecklists);

    int countMembersWithChecklists();
}
