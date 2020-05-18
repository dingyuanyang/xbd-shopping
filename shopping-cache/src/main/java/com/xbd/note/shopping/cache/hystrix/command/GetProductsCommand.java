package com.xbd.note.shopping.cache.hystrix.command;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixObservableCommand;
import com.xbd.note.shopping.cache.http.HttpClientUtils;
import com.xbd.note.shopping.cache.model.Product;
import rx.Observable;
import rx.Subscriber;

public class GetProductsCommand extends HystrixObservableCommand<Product> {
    private String[] ids;

    public GetProductsCommand(String[] ids) {
        super(HystrixCommandGroupKey.Factory.asKey("getProductGroup"));
        this.ids = ids;
    }

    @Override
    protected Observable<Product> construct() {
        return Observable.create(new Observable.OnSubscribe<Product>() {

            @Override
            public void call(Subscriber<? super Product> observer) {
                for (String id : ids) {
                    String url = "http://localhost:8081/api/v1/product/" + id;
                    String response = HttpClientUtils.sendGetRequest(url);
                    Product product = JSONObject.parseObject(response, Product.class);
                    observer.onNext(product);
                }
                observer.onCompleted();
            }
        });
    }
}
