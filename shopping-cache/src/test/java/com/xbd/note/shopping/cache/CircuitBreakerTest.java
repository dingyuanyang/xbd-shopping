package com.xbd.note.shopping.cache;

import com.xbd.note.shopping.cache.http.HttpClientUtils;

public class CircuitBreakerTest {

    public static void main(String[] args) throws InterruptedException {
        String url = "http://localhost:8080/api/v1/getProduct?id=";
        for (int i = 0; i < 15; i++) {
            String request = HttpClientUtils.sendGetRequest(url + i);
            System.out.println("第" + (i + 1) + "次：" + request);
        }
        for (int i = 0; i < 25; i++) {
                String request = HttpClientUtils.sendGetRequest(url + "-1");
                System.out.println("第" + (i + 1) + "次：" + request);
        }
        Thread.sleep(5000);

        //等待5秒，短路后的请求发送
        System.out.println("请求短路以后的情况");
        for (int i = 0; i < 10; i++) {
            String request = HttpClientUtils.sendGetRequest(url + i);
            System.out.println("第" + (i + 1) + "次：" + request);
        }
        System.out.println("尝试等待5秒,是否恢复");
        Thread.sleep(5000);
        for (int i = 0; i < 10; i++) {
            String request = HttpClientUtils.sendGetRequest(url + i);
            System.out.println("第" + (i + 1) + "次：" + request);
        }
    }
}
