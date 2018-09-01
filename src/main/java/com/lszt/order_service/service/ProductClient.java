package com.lszt.order_service.service;

import com.lszt.order_service.fallback.ProductClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FeignClient 调用 注册中心 product-service
 * fallback 如果报错熔断返回降级到 ProductClientFallback.class去
 */
@FeignClient(name = "product-service", fallback = ProductClientFallback.class )
public interface ProductClient {

    @GetMapping("/api/product/find")
    String findById(@RequestParam(value = "id") int id);
}
