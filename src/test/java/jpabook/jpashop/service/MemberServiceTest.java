package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //@Rollback(false) 테스트 끝나면 기본적으로 롤백하지만, 데이터 들어가는지 확인용
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("A");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("B");

        Member member2 = new Member();
        member2.setName("B");

        //when
        memberService.join(member1);

        //then
        //lambda 의 실행결과가 왼쪽이어야 한다
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}