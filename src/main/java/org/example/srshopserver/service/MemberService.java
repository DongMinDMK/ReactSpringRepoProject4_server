package org.example.srshopserver.service;

import org.example.srshopserver.dao.MemberDAO;
import org.example.srshopserver.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    MemberDAO memberDAO;

    public Member getMember(String userid) {
        Member member = memberDAO.getMember(userid);
        return member;
    }

    public void insertMember(Member member) {
        memberDAO.insertMember(member);
    }
}
