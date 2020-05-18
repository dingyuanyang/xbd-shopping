package com.xbd.note.shopping.cache.api;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixObservableCommand;
import com.xbd.note.shopping.cache.http.HttpClientUtils;
import com.xbd.note.shopping.cache.hystrix.command.GetBrandNameCommand;
import com.xbd.note.shopping.cache.hystrix.command.GetCityNameCommand;
import com.xbd.note.shopping.cache.hystrix.command.GetProductCommand;
import com.xbd.note.shopping.cache.hystrix.command.GetProductsCommand;
import com.xbd.note.shopping.cache.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Observer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yuanyang
 */
@RequestMapping("/api/v1/")
@RestController
public class CacheRestController {

    @GetMapping("/cache")
    public String changeCache(@RequestParam("id") long productId) {
        String url = "http://localhost:8081/api/v1/product/" + productId;
        String response = HttpClientUtils.sendGetRequest(url);
        System.out.println(response);
        return "success";
    }

    /**
     * 缓存时效
     *
     * @param productId
     * @return
     */
    @GetMapping("/getProduct")
    public Product getProduct(@RequestParam("id") long productId) {
        HystrixCommand<Product> command = new GetProductCommand(productId);
        Product pro = command.execute();

//        Future<Product> queue = command.queue();
//        try {
//            Thread.sleep(1000);
//            if (queue.isDone()) {
//                System.out.println(queue.get());
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        Long cityId = pro.getCityId();
        HystrixCommand<String> cityNameCommand = new GetCityNameCommand(cityId);
        String cityName = cityNameCommand.execute();
        pro.setCityName(cityName);

        Long brandId = pro.getBrandId();
        HystrixCommand<String> branNameCommand = new GetBrandNameCommand(brandId);
        String brandName = branNameCommand.execute();
        pro.setBrandName(brandName);

        System.out.println(pro);
        return pro;
    }

    /**
     * 缓存时效
     *
     * @param ids
     * @return
     */
    @GetMapping("/getProducts")
    public String getProducts(@RequestParam("id") String ids) {
        HystrixObservableCommand<Product> command =
                new GetProductsCommand(ids.split(","));
        Observable<Product> observe = command.observe();
        observe.subscribe(new Observer<Product>() {
            @Override
            public void onCompleted() {
                System.out.println("接收完成");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(Product product) {
                System.out.println(product);
            }
        });
        return "success";
    }
    @GetMapping("/getProduct/list")
    public String getProductList(@RequestParam("id") String ids) {
        for (String productId : ids.split(",")) {
            GetProductCommand command = new GetProductCommand(Long.valueOf(productId));
            Product product = command.execute();
            System.out.println(product);
            System.out.println(command.isResponseFromCache());
        }
        return "success";
    }
}
