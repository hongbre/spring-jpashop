package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //Spring Data JPA 가 생성자 인젝션 할 수 있도록
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    //단건조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    //전체조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    //이름조회
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
