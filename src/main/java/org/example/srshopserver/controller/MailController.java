package org.example.srshopserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.srshopserver.service.MailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mails")
public class MailController {

    private final MailService mailService;
    private int number;

    @PostMapping("/sendMail")
    public HashMap<String, Object> sendMail(@RequestParam("email") String email){
        HashMap<String, Object> result = new HashMap<>();

        try{
            number = mailService.sendMail(email);
            String num = String.valueOf(number); // int 형 정수로 받은 number 를 String 로 변경
            System.out.println(num);
            result.put("message", "OK");
            result.put("number", num);
        }catch(Exception e){
            e.printStackTrace();
            result.put("message", "이메일 전송에 실패하였습니다.");
        }

        return result;
    }
}
