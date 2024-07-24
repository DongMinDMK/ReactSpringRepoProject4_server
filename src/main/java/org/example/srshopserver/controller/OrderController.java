package org.example.srshopserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.srshopserver.entity.Member;
import org.example.srshopserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/insertOrders")
    HashMap<String, Object> insertOrders(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginUser");

        int oseq = orderService.insertOrders(member.getUserid());

        hm.put("oseq", oseq);

        return hm;

    }

    @PostMapping("/insertOrderDetail")
    public HashMap<String, Object> insertOrderDetail(@RequestParam("oseq") int oseq, @RequestParam("cseq") int cseq){

        HashMap<String, Object> hm = new HashMap<>();

        orderService.insertOrderDetail(oseq, cseq);

        hm.put("message", "OK");

        return hm;
    }
}
