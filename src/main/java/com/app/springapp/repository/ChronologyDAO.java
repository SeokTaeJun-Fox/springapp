package com.app.springapp.repository;

import com.app.springapp.mapper.ChronologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class ChronologyDAO {

    private final ChronologyMapper chronologyMapper;

    public String findNicknameByProjectId(Long projectId) {
        return chronologyMapper.findNicknameByProjectId(projectId);
    }

    public Long findMemberIdByProjectId(Long projectId) {
        return chronologyMapper.findMemberIdByProjectId(projectId);
    }

    public int countCompletedChecklistsByMemberId(Long memberId) {
        return chronologyMapper.countCompletedChecklistsByMemberId(memberId);
    }

    public int findAvgCompletedChecklists() {
        return chronologyMapper.findAvgCompletedChecklists();
    }

    public List<Map<String, Object>> findTop3ChecklistsByMemberId(Long memberId) {
        return chronologyMapper.findTop3ChecklistsByMemberId(memberId);
    }

    public int findAvgProjectDays() {
        return chronologyMapper.findAvgProjectDays();
    }

    public int findAvgProjectCount() {
        return chronologyMapper.findAvgProjectCount();
    }

    public int countMembersWithMoreChecklists(int totalChecklists) {
        return chronologyMapper.countMembersWithMoreChecklists(totalChecklists);
    }

    public int countMembersWithChecklists() {
        return chronologyMapper.countMembersWithChecklists();
    }
}
