package org.example.srshopserver.dao;

import jakarta.persistence.EntityManager;
import org.example.srshopserver.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAO {

    @Autowired
    EntityManager em;

    public Member getMember(String userid) {
        Member member = em.find(Member.class, userid);
        return member;
    }

    public void insertMember(Member member) {
        em.persist(member);
    }
}
