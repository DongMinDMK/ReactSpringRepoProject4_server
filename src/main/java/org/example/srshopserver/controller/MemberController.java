package org.example.srshopserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.srshopserver.entity.Member;
import org.example.srshopserver.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    @PostMapping("/localLogin")
    public HashMap<String, Object> localLogin(@RequestBody Member member, HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        Member member1 = memberService.getMember(member.getUserid());

        if(member1 == null){
            hm.put("message", "아이디가 존재하지 않습니다.");
        }else if(!member1.getPwd().equals(member.getPwd())){
            hm.put("message", "비밀번호가 일치하지 않습니다.");
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("loginUser", member1);
            hm.put("message", "OK");
        }

        return hm;
    }

    @GetMapping("/getLoginUser")
    public HashMap<String, Object> getLoginUser(HttpServletRequest request){
        HashMap<String, Object> result = new HashMap<>();

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginUser");

        result.put("loginUser", member);


        return result;
    }

    @GetMapping("/logout")
    public HashMap<String, Object> logout(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        HttpSession session = request.getSession();
        session.removeAttribute("loginUser");

        hm.put("message", "OK");


        return hm;
    }

    @GetMapping("/idCheck")
    public HashMap<String, Object> idCheck(@RequestParam("userid") String userid){
        HashMap<String,Object> hm = new HashMap<>();

        Member member = memberService.getMember(userid);

        if(member == null){
            hm.put("res", "1");
        }else{
            hm.put("res", "0");
        }

        return hm;
    }

    @PostMapping("/insertMember")
    public HashMap<String, Object> insertMember(@RequestBody Member member){
        HashMap<String, Object> hm = new HashMap<>();

        memberService.insertMember(member);

        hm.put("message", "OK");

        return hm;
    }
}
