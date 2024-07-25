package org.example.srshopserver.dao;

import jakarta.persistence.EntityManager;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Order_Detail;
import org.example.srshopserver.entity.Order_View;
import org.example.srshopserver.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    public List<Order_View> getOrders(int oseq) {
        String sql = "select ov from Order_View ov where ov.oseq=:oseq";

        List<Order_View> orderViewList = em.createQuery(sql, Order_View.class)
                .setParameter("oseq", oseq)
                .getResultList();
        return orderViewList;
    }

    public List<Integer> getOrdering(String userid) {
        String sql = "select distinct ov.oseq from Order_View ov where ov.userid=:userid and ov.result <> '4' order by oseq desc";
        List<Integer> list = em.createQuery(sql, Integer.class)
                .setParameter("userid", userid)
                .getResultList();
        return list;
    }

    public List<Order_View> getOseqByOseq(int oseq) {
        String sql = "select ov from Order_View ov where ov.oseq=:oseq";
        List<Order_View> list = em.createQuery(sql, Order_View.class)
                .setParameter("oseq", oseq)
                .getResultList();
        return list;
    }

    public List<Integer> orderAll(String userid) {
        String sql = "select distinct ov.oseq from Order_View ov where ov.userid=:userid order by oseq desc";
        List<Integer> list = em.createQuery(sql, Integer.class)
                .setParameter("userid", userid)
                .getResultList();
        return list;
    }


}
