package kr.study.test.form;

import lombok.Builder;
import lombok.Getter;

// 화면에서 사용자에게 받아오는 값
@Getter
@Builder  // 생성자로 사용 -> 초기에만 값 넣을 수 있음 set 중간에 값 변경
public class StudyForm {
    private Long memberId;
    private String studyDay; // 미래시간 x 현재,과거에만 선택
    private String startTime;
    private int studyMins;
    private String contents;
}
