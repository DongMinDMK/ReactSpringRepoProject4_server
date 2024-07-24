package org.example.srshopserver.service;

import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.example.srshopserver.dao.OrderDAO;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Order_Detail;
import org.example.srshopserver.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderService {
    @Autowired
    OrderDAO orderDAO;

    public int insertOrders(String userid) {
        Orders orders = new Orders();

        orders.setUserid(userid);

        orderDAO.insertOrders(orders);

        int oseq = orderDAO.orderMaxOseq();

        return oseq;
    }

    public void insertOrderDetail(int oseq, int cseq) {
        Cart cart = orderDAO.getCart(cseq);
        Order_Detail orderDetail = new Order_Detail();

        orderDetail.setOseq(oseq);
        orderDetail.setPseq(cart.getPseq());
        orderDetail.setQuantity(cart.getQuantity());
        orderDetail.setResult("1");

        orderDAO.insertOrderDetail(orderDetail);

        orderDAO.deleteCart(cart);
    }
}
