package com.xbd.note.shopping.product.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyang
 */
@RequestMapping("/api/v1/")
@RestController
public class ProductionRestController {

    @GetMapping("product/{id}")
    public String getProductInfo(@PathVariable("id")long productId){
        return "{\"id\": " + productId + ", \"name\": \"iphone7手机\", \"price\": 5599, \"pictureList\":\"a.jpg,b.jpg\", \"specification\": \"iphone7的规格\", \"service\": \"iphone7的售后服务\", \"color\": \"红色,白色,黑色\", \"size\": \"5.5\", \"shopId\": 1, \"modifiedTime\": \"2017-01-01 12:00:00\", \"cityId\": 1, \"brandId\": 1}";
    }
}
