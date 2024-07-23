package org.example.srshopserver.service;

import org.example.srshopserver.dao.ProductDAO;
import org.example.srshopserver.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public List<Product> bestProduct() {
        List<Product> bestProduct = productDAO.bestProduct();
        return bestProduct;
    }

    public List<Product> newProduct() {
        List<Product> newProduct = productDAO.newProduct();
        return newProduct;
    }

    public List<Product> kindList(String kind) {
        List<Product> list = productDAO.kindList(kind);
        return list;
    }

    public Product getProduct(int pseq) {
        Product product = productDAO.getProduct(pseq);
        return product;
    }
}
