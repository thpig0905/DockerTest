package kr.study.test.domain;


import jakarta.persistence.*;

import kr.study.test.form.StudyForm;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// 테이블과 직접적으로 연결된 클래스
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudyRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_id")
    private Long id;

    private LocalDate studyDay; // 공부시작날
    private LocalTime startTime; // 공부시작시간
    private int studyMins; // 공부 시간(분)
    @Lob
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 비지니스 로직 , 생성자 로직

    //==생성 메서드 ==
    public static StudyRecord createRecord(StudyForm form, Member member) {
        StudyRecord study = new StudyRecord();
        study.member = member;
        study.studyDay = LocalDate.parse(form.getStudyDay(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        study.startTime = LocalTime.parse(form.getStartTime() + ":00", DateTimeFormatter.ofPattern("HH:mm:ss"));
        study.studyMins = form.getStudyMins();
        study.contents = form.getContents();
        return study;
    }


    public static StudyRecord modyfiyRecord(StudyRecord record, StudyForm form) {
        DateTimeFormatter strDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter strTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        record.studyDay = LocalDate.parse(form.getStudyDay(), strDate);
        record.startTime = LocalTime.parse(form.getStartTime() + ":00", strTime);
        record.studyMins = form.getStudyMins();
        record.contents = form.getContents();
        return record;
    }

    //==비즈니스 로직==//
    public String getEndStudyDay() {
        String pattern = "yyyy/MM/dd HH:mm";
        LocalDateTime endStudyTime = LocalDateTime.of(this.studyDay, this.startTime);
        endStudyTime = endStudyTime.plusMinutes(this.studyMins);
        String data = endStudyTime.format(DateTimeFormatter.ofPattern(pattern));

        return data;
    }


}
