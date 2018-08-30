package com.lszt.order_service.controller;

import com.lszt.order_service.service.ProductOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductOrderService productOrderService;

    /**
     * @param userId    用户id
     * @param productId 订单id
     * @return
     */
    @RequestMapping("save")
    public Object save(@RequestParam("userId") int userId, @RequestParam("productId") int productId) {
        return productOrderService.save(userId, productId);
    }


}
