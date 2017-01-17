package com.kris.portal.service.impl;

import com.kris.portal.pojo.OrderInfo;
import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.service.OrderService;
import com.kris.portal.utils.HttpClientUtil;
import com.kris.portal.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author kris
 * @create 2017-01-17 16:02
 */
@Service
public class OrderServiceImpl implements OrderService{

    @Value("${ORDER_BASE_URL}")
    private String ORDER_BASE_URL;
    @Value("${ORDER_CREATE_URL}")
    private String ORDER_CREATE_URL;

    @Override
    public String createOrder(OrderInfo orderInfo) {
        //把orderInfo转换成json
        String json = JsonUtils.objectToJson(orderInfo);
        //提交订单数据
        String jsonResult = HttpClientUtil.doPostJson(ORDER_BASE_URL + ORDER_CREATE_URL, json);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.format(jsonResult);
        //取订单号
        String orderId = taotaoResult.getData().toString();
        return orderId;
    }
}
