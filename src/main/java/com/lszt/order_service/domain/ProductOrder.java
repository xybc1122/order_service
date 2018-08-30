package com.lszt.order_service.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品订单实体类
 */
public class ProductOrder implements Serializable {
    private String userName;

    private int userId;
    /**
     * 订单id
     */
    private int id;
    /**
     * 订单流水号
     */
    private String tradeNo;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 价格, 分
     */
    private int price;
    /**
     * 创建时间
     */
    private Date createTime;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
