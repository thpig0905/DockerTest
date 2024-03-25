package kr.study.test.service;

import kr.study.test.domain.Member;
import kr.study.test.domain.StudyRecord;
import kr.study.test.form.StudyForm;
import kr.study.test.repository.StudyRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class StudyRecordService {
    private final StudyRecordRepository repository;

    public List<StudyRecord> getAllRecordList() {
        return repository.findAll();
    }

    public StudyRecord getOneRecord(Long id) {
        Optional<StudyRecord> list = repository.findById(id);
        if (list.isEmpty()) {
            throw new IllegalStateException("기록데이터가 없습니다");
        }
        return list.get();
    }

    @Transactional
    public void saveRecord(StudyForm form, Member member) {
        StudyRecord record = StudyRecord.createRecord(form, member);
        repository.save(record);
        //StudyRecord rd = new StudyRecord();
    }

    @Transactional
    public void updateRecord(StudyRecord record, StudyForm form) {
        StudyRecord newRecord = StudyRecord.modyfiyRecord(record, form);
        repository.save(newRecord);
    }

    @Transactional
    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void deleteAllRecordByMember(Member member) {
        List<StudyRecord> list = repository.searchStudyRecodeByMemberId(member.getId());
        if (list != null) {
            list.forEach(recode -> {
                log.trace("delete recode={}", recode);
                repository.deleteByMember(recode.getMember());
            });
        }
    }

}
