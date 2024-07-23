package org.example.srshopserver.dao;

import jakarta.persistence.EntityManager;
import org.example.srshopserver.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    @Autowired
    EntityManager em;

    public List<Product> bestProduct() {
        String sql = "select p from Product p where p.bestyn=:bestyn";

        List<Product> bestProduct = em.createQuery(sql, Product.class)
                .setParameter("bestyn", "Y")
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();

        return bestProduct;
    }

    public List<Product> newProduct() {
        String sql = "select p from Product p where p.useyn=:useyn";

        List<Product> newProduct = em.createQuery(sql, Product.class)
                .setParameter("useyn", "Y")
                .setFirstResult(0)
                .setMaxResults(4)
                .getResultList();

        return newProduct;
    }

    public List<Product> kindList(String kind) {
        String sql = "select p from Product p where p.kind=:kind";


        List<Product> kindList = em.createQuery(sql, Product.class)
                .setParameter("kind", kind)
                .getResultList();

        return kindList;
    }

    public Product getProduct(int pseq) {
        Product product = em.find(Product.class, pseq);
        return product;
    }
}
