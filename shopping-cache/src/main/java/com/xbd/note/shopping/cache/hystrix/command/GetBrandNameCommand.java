package com.xbd.note.shopping.cache.hystrix.command;


import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.xbd.note.shopping.cache.local.BrandCache;

/**
 * 获取品牌名称
 *
 * @author yuanyang
 */
public class GetBrandNameCommand extends HystrixCommand<String> {

    private Long brandId;

    public GetBrandNameCommand(Long brandId) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("GetBrandNameCommand"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter()));
        this.brandId = brandId;
    }

    @Override
    protected String run() throws Exception {
        throw new Exception("");
    }

    @Override
    protected String getFallback() {
        return BrandCache.getBrandName(brandId);
    }
}
