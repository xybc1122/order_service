package com.lszt.order_service.service;

import com.lszt.order_service.domain.ProductOrder;

/**
 * 订单业务
 */
public interface ProductOrderService {
    /**
     * 下单接口
     * @param userId
     * @param productId
     * @return
     */
    ProductOrder save(int userId, int productId);

}
