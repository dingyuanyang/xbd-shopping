package com.xbd.note.shopping.cache.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.*;
import com.xbd.note.shopping.cache.http.HttpClientUtils;
import com.xbd.note.shopping.cache.local.LocationCache;
import com.xbd.note.shopping.cache.model.Product;

/**
 * @author yuanyang
 */
public class GetProductCommand extends HystrixCommand<Product> {
    private long productId;

    public GetProductCommand(Long productId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ProductService"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("GetProductCommand"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("GetProductCommandPool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(20)
                        .withMaxQueueSize(10)
                        .withMaximumSize(50)));
        this.productId = productId;
    }

    @Override
    protected Product run() throws Exception {
        String url = "http://localhost:8081/api/v1/product/" + productId;
        System.out.println(url);
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        Product product = JSONObject.parseObject(response, Product.class);
        return product;
    }

    @Override
    protected String getCacheKey() {
        return "product_" + productId;
    }
}
