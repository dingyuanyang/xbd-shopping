package com.xbd.note.shopping.cache.hystrix.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.xbd.note.shopping.cache.local.LocationCache;

/**
 * @author yuanyang
 */
public class GetCityNameCommand extends HystrixCommand<String> {
    private Long cityId;

    public GetCityNameCommand(Long cityId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetCityNameGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
        .withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
                .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)));
        this.cityId = cityId;
    }

    @Override
    protected String run() throws Exception {
        return LocationCache.cityName(cityId);
    }
}
