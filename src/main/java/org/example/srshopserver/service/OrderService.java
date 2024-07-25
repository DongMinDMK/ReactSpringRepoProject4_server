package org.example.srshopserver.service;

import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.example.srshopserver.dao.OrderDAO;
import org.example.srshopserver.entity.Cart;
import org.example.srshopserver.entity.Order_Detail;
import org.example.srshopserver.entity.Order_View;
import org.example.srshopserver.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Order_View> getOrders(int oseq) {
        List<Order_View> orderViewList = orderDAO.getOrders(oseq);
        return orderViewList;
    }

    public ArrayList<Order_View> getOrdering(String userid) {
        ArrayList<Order_View> orderViewList = new ArrayList<>();

        List<Integer> list = orderDAO.getOrdering(userid);
        for(int oseq : list){
            // oseq 로 주문조회
            List<Order_View> ovList = orderDAO.getOseqByOseq(oseq);
            // 정리된 주문내역이 저장될 dto 생성(Entity x)
            Order_View ov = new Order_View();
            ov.setPname(ovList.get(0).getPname() + "포함 " + ovList.size() + "건");
            int totalPrice = 0;
            for(Order_View oView : ovList){
                totalPrice = oView.getPrice2() * oView.getQuantity();
            }
            ov.setPrice2(totalPrice);
            //oseq, indate 도 dto 에 저장
            ov.setOseq(ovList.get(0).getOseq());
            ov.setIndate(ovList.get(0).getIndate());
            orderViewList.add(ov);
        }
        return orderViewList;
    }

    public ArrayList<Order_View> orderAll(String userid) {
        ArrayList<Order_View> orderViewList = new ArrayList<>();

        List<Integer> list = orderDAO.orderAll(userid);
        for(int oseq : list){
            // oseq 로 주문조회
            List<Order_View> ovList = orderDAO.getOseqByOseq(oseq);
            // 정리된 주문내역이 저장될 dto 생성(Entity x)
            Order_View ov = new Order_View();
            ov.setPname(ovList.get(0).getPname() + "포함 " + ovList.size() + "건");
            int totalPrice = 0;
            for(Order_View oView : ovList){
                totalPrice = oView.getPrice2() * oView.getQuantity();
            }
            ov.setPrice2(totalPrice);
            //oseq, indate 도 dto 에 저장
            ov.setOseq(ovList.get(0).getOseq());
            ov.setIndate(ovList.get(0).getIndate());
            orderViewList.add(ov);
        }



        return orderViewList;
    }

    public int insertOrderOne(String userid, int pseq, int quantity) {
        Orders orders = new Orders();
        orders.setUserid(userid);

        orderDAO.insertOrders(orders);

        int oseq = orderDAO.orderMaxOseq();

        Order_Detail orderDetail = new Order_Detail();

        orderDetail.setOseq(oseq);
        orderDetail.setPseq(pseq);
        orderDetail.setQuantity(quantity);
        orderDetail.setResult("1");
        orderDAO.insertOrderDetail(orderDetail);


        return oseq;
    }
}
