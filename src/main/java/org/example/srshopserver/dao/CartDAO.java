package org.example.srshopserver.dao;

import jakarta.persistence.EntityManager;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Cart_View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@Repository
public class CartDAO {

    @Autowired
    EntityManager em;

    public void insertCart(Cart cart1) {
        em.persist(cart1);
    }

    public List<Cart_View> cartList(String userid) {
        String sql = "select c from Cart_View c where c.userid=:userid";

        List<Cart_View> cartViewList = em.createQuery(sql, Cart_View.class)
                .setParameter("userid", userid)
                .getResultList();

        return cartViewList;
    }

    public void deleteCart(int cseq) {
        Cart cart = em.find(Cart.class, cseq);
        em.remove(cart);
    }
}
