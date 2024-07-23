package org.example.srshopserver.controller;

import org.example.srshopserver.entity.Product;
import org.example.srshopserver.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/bestProduct")
    public HashMap<String, Object> bestProduct(){
        HashMap<String, Object> hm = new HashMap<>();

        List<Product> bestProduct = productService.bestProduct();

        hm.put("bestProduct", bestProduct);

        return hm;
    }

    @GetMapping("/newProduct")
    public HashMap<String, Object> newProduct() {
        HashMap<String, Object> hm = new HashMap<>();

        List<Product> newProduct = productService.newProduct();

        hm.put("newProduct", newProduct);

        return hm;
    }

    @GetMapping("/kindList/{kindNum}")
    public HashMap<String, Object> kindList(@PathVariable("kindNum") String kind){
        HashMap<String, Object> hm = new HashMap<>();

        hm.put("kindList", productService.kindList(kind));

        return hm;
    }

    @GetMapping("/getProduct/{pseq}")
    public HashMap<String, Object> getProduct(@PathVariable("pseq") int pseq){
        HashMap<String, Object> hm = new HashMap<>();

        hm.put("product", productService.getProduct(pseq));

        return hm;
    }
}
