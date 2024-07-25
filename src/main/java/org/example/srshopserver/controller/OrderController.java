package org.example.srshopserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.srshopserver.entity.Member;
import org.example.srshopserver.entity.Order_View;
import org.example.srshopserver.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @GetMapping("/getOrders/{oseq}")
    public HashMap<String, Object> getOrders(@PathVariable("oseq") int oseq){
        HashMap<String, Object> hm = new HashMap<>();

        List<Order_View> orderViewList = orderService.getOrders(oseq);

        int totalPrice = 0;
        for(Order_View orderView : orderViewList){
            totalPrice += orderView.getPrice2() * orderView.getQuantity();
        }

        Order_View orderView = orderViewList.get(0);

        hm.put("orders", orderViewList);
        hm.put("totalPrice", totalPrice);
        hm.put("orderDetail", orderView);


        return hm;
    }
    @GetMapping("/getOrdering")
    public HashMap<String, Object> getOrdering(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginUser");

        ArrayList<Order_View> list = orderService.getOrdering(member.getUserid());

        hm.put("orderList", list);


        return hm;
    }

    @GetMapping("/orderAll")
    public HashMap<String, Object> orderAll(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("loginUser");

        ArrayList<Order_View> list = orderService.orderAll(member.getUserid());

        hm.put("orderList", list);


        return hm;
    }

    @PostMapping("/insertOrderOne")
    public HashMap<String, Object> insertOrderOne(@RequestParam("userid") String userid, @RequestParam("pseq") int pseq, @RequestParam("quantity") int quantity){
        HashMap<String, Object> hm = new HashMap<>();

        int oseq = orderService.insertOrderOne(userid, pseq, quantity);

        hm.put("oseq", oseq);

        return hm;
    }
}
