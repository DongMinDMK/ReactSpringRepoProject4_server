package org.example.srshopserver.dao;

import jakarta.persistence.EntityManager;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Order_Detail;
import org.example.srshopserver.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDAO {

    @Autowired
    EntityManager em;

    public void insertOrders(Orders orders) {
        em.persist(orders);
    }

    public int orderMaxOseq() {
        String sql = "select max(o.oseq) from Orders o";

        int oseq = (Integer) em.createQuery(sql)
                .getSingleResult();

        return oseq;
    }

    public Cart getCart(int cseq) {
        Cart cart = em.find(Cart.class, cseq);
        return cart;
    }

    public void insertOrderDetail(Order_Detail orderDetail) {
        em.persist(orderDetail);
    }

    public void deleteCart(Cart cart) {
        em.remove(cart);
    }
}
