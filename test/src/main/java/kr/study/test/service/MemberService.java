package kr.study.test.service;

import kr.study.test.domain.Member;
import kr.study.test.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)  // 읽기
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    @Transactional  // 읽기, 수정,삭제
    public Long join(Member member) {
        validateMemberId(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 아이디중복체크
    private void validateMemberId(Member member) {
        if (memberRepository.findMemberByLoginId(member.getLoginId()) != null) {
            throw new IllegalStateException("이미 존재하는 회원아이디입니다");
        }
    }

    // 전체회원조회
    public List<Member> findAllMembers() {
        List<Member> list = memberRepository.findAll();
        if (list.isEmpty()) throw new IllegalStateException("데이터가 존재하지않습니다");
        return list;
    }

    // 회원한명탈퇴
    @Transactional
    public void removeMember(Long id) {
        memberRepository.deleteById(id);
    }

    // 아이디로 회원찾기
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId).isPresent() ? memberRepository.findById(memberId).get() : null;
    }
}
