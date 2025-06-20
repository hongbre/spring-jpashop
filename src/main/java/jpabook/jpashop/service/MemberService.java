package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final 인 필드만 생성
public class MemberService {

    private final MemberRepository memberRepository;

    //회원가입
    @Transactional //기본값인 readOnly = false 적용
    public Long join(Member member) {
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //중복 회원일 경우 예외 발생
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    //단건조회
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    //전체조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
}
