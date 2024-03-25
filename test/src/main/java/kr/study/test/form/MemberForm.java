package kr.study.test.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
// 화면에서만 받아오는  userDate
public class MemberForm {
    private String id;
    private String pw;
    private String name;
}
