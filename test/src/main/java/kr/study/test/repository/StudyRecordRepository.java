package kr.study.test.repository;


import kr.study.test.domain.Member;
import kr.study.test.domain.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {

    @Query(value = "select * from study_record where member_id =:memberId", nativeQuery = true)
    List<StudyRecord> searchStudyRecodeByMemberId(@Param(value = "memberId") Long memberId);

    //delete study_recode where member_id = memberId;
    void deleteByMember(Member member);

}
