package com.lszt.order_service.controller;

import com.lszt.order_service.service.ProductOrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/order")
public class OrderController {
    @Value("${server.port}")
    private String port;

    @Autowired
    private ProductOrderService productOrderService;
    //redis 模板
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @param userId    用户id
     * @param productId 订单id
     * @return
     */
    @RequestMapping("save")
    @HystrixCommand(fallbackMethod = "savePrderFail")
    public Object save(@RequestParam("userId") int userId, @RequestParam("productId") int productId,HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", 0);
        data.put("data", productOrderService.save(userId, productId));
        return data;
    }

    //方法熔断报错回调 方法 跟参数都要一致
    private Object savePrderFail(int userId, int productId, HttpServletRequest request) {
        //监控报警
        String savOrderKye = "save-order";
        final  String ip = request.getRemoteAddr();
        String sendValue = redisTemplate.opsForValue().get(savOrderKye);
        //开启一个线程  Lambda表达式
        new Thread(() -> {
            if (StringUtils.isBlank(sendValue)) {
                System.out.println("紧急短信，发送 ip=" + ip);
                //设置key过期时间
                redisTemplate.opsForValue().set(savOrderKye, "save-order-fail", 20, TimeUnit.SECONDS);
            } else {
                System.out.println("已经发送过短信,20秒不重复发送");
            }
        }).start();

        Map<String, Object> msg = new HashMap<>();
        msg.put("code", -1);
        msg.put("msg", "抢购太多，稍后重试!");
        return msg;
    }

}
