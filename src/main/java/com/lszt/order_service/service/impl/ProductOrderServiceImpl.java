package com.lszt.order_service.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.lszt.order_service.Utils.JsonUtils;
import com.lszt.order_service.domain.ProductOrder;
import com.lszt.order_service.service.ProductClient;
import com.lszt.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Date;
import java.util.UUID;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {
    //ribbon方式调用
    @Autowired
    private RestTemplate restTemplate;

    //feign方式调用
    @Autowired
    private ProductClient productClient;

    /*  @Override  ribbon方式
      public ProductOrder save(int userId, int productId) {
          Map<String, Object> productMap = restTemplate.getForObject("http://PRODUCT-SERVICE/api/product/find?id=" + productId, Map.class);
          System.out.println(productMap);
          ProductOrder productOrder = new ProductOrder();
          productOrder.setCreateTime(new Date());
          productOrder.setUserId(userId);
          productOrder.setTradeNo(UUID.randomUUID().toString());
          productOrder.setProductName(productMap.get("name").toString());
          productOrder.setPrice(Integer.parseInt(productMap.get("price").toString()));
          return productOrder;
      }*/
    //feign方式
    @Override
    public ProductOrder save(int userId, int productId) {
        //调用订单服务
        String response = productClient.findById(productId);
        //调用用户服务，主要是获取用户名称，用户的级别或者积分信息
        //TODO



        JsonNode jsonNode = JsonUtils.str2JsonNode(response);
        ProductOrder productOrder = new ProductOrder();
        productOrder.setCreateTime(new Date());
        productOrder.setUserId(userId);
        productOrder.setTradeNo(UUID.randomUUID().toString());
        productOrder.setProductName(jsonNode.get("name").toString());
        productOrder.setPrice(Integer.parseInt(jsonNode.get("price").toString()));
        return productOrder;
    }
}
