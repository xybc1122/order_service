package com.lszt.order_service.fallback;

import com.lszt.order_service.service.ProductClient;
import org.springframework.stereotype.Component;

/**
 * 针对商品服务，做降级处理
 */
@Component
public class ProductClientFallback implements ProductClient {


    @Override
    public String findById(int id) {
        System.out.println("feign 调用 product-service异常");

        return null;
    }
}
