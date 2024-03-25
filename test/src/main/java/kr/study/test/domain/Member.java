package kr.study.test.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // jpa 객체를 만들기위해서는 기본생성자가 필요
// 기본생성자 --> 빈객체 생성할 수 있음 (EntityManeger 가 Member 클래스 )
@Entity
@Table(name = "member")
public class Member {

    // protected Member(){}
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id; // wrapper class long -> 디폴트 값이 0 이 아니라 null 을 주기 위해서 // int가 아니라 Integer
    private String loginId;
    private String password;
    private String name;
    @Enumerated(EnumType.STRING)
    private Role role;

    // 빌더에 필수 파라미터값 셋팅하기 -> memberlistRepository
//    public static MemberBuilder builder(Long id){
//        if(id == null) throw new IllegalArgumentException("필수 파라미터 누락");
//        return new MemberBuilder().id(id);
//    }

    public void setKeyId(Long key) {
        this.id = key;
    }

    //art + insert
    @Builder
    public Member(Long id, String loginId, String password, String name, Role role) {
        this.id = id;
        this.loginId = loginId;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
