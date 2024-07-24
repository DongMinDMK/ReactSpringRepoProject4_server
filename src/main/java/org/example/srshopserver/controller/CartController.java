package org.example.srshopserver.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Cart_View;
import org.example.srshopserver.entity.Member;
import org.example.srshopserver.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/carts")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/insertCart")
    public HashMap<String, Object> insertCart(@RequestBody Cart cart){
        HashMap<String, Object> hm = new HashMap<>();

        cartService.insertCart(cart);

        hm.put("message", "OK");

        return hm;
    }

    @GetMapping("/cartList")
    public HashMap<String, Object> cartList(HttpServletRequest request){
        HashMap<String, Object> hm = new HashMap<>();

        HttpSession session = request.getSession();
        Member member = (Member)session.getAttribute("loginUser");

        List<Cart_View> cartViewList =  cartService.cartList(member.getUserid());

        int totalPrice = 0;
        for(Cart_View cartView : cartViewList){
            totalPrice += cartView.getPrice2() * cartView.getQuantity();
        }

        hm.put("cartList", cartViewList);
        hm.put("totalPrice", totalPrice);




        return hm;

    }

    @DeleteMapping("/deleteCart/{cseq}")
    public HashMap<String, Object> deleteCart(@PathVariable("cseq") int cseq){
        HashMap<String, Object> hm = new HashMap<>();

        cartService.deleteCart(cseq);

        hm.put("message", "OK");

        return hm;
    }
}
