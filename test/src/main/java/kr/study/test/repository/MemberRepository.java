package kr.study.test.repository;


import kr.study.test.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findMemberByLoginId(String loginId);

    public void deleteById(Long id);
}
