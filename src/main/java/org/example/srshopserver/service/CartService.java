package org.example.srshopserver.service;

import org.example.srshopserver.dao.CartDAO;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Cart_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartService {

    @Autowired
    CartDAO cartDAO;

    public void insertCart(Cart cart) {
        Cart cart1 = new Cart();

        cart1.setPseq(cart.getPseq());
        cart1.setUserid(cart.getUserid());
        cart1.setQuantity(cart.getQuantity());

        cartDAO.insertCart(cart1);
    }

    public List<Cart_View> cartList(String userid) {
        List<Cart_View> cartViewList = cartDAO.cartList(userid);
        return cartViewList;
    }

    public void deleteCart(int cseq) {
        cartDAO.deleteCart(cseq);
    }
}
