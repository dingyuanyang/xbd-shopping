package com.xbd.note.shopping.cache.api;


import com.xbd.note.shopping.cache.http.HttpClientUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yuanyang
 */
@RequestMapping("/api/v1/")
@RestController
public class CacheRestController {

    @GetMapping("/cache")
    public String changeCache(@RequestParam("id") long productId) {
        String url = "http://localhost:8081/api/v1/product/"+productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        return "success";
    }
}
