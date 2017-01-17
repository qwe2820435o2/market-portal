package com.kris.portal.controller;

import com.kris.portal.pojo.CartItem;
import com.kris.portal.pojo.OrderInfo;
import com.kris.portal.service.CartService;
import com.kris.portal.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单接口
 *
 * @author kris
 * @create 2017-01-17 15:37
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService mOrderService;
    @Autowired
    private CartService mCartService;

    @RequestMapping("/order-cart")
    public String showOrderCart(Model model, HttpServletRequest request) {
        //取购物车商品列表
        List<CartItem> list = mCartService.getCartItems(request);
        model.addAttribute("cartList", list);
        return "order-cart";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createOrder(OrderInfo orderInfo, Model model, HttpServletRequest request) {
        //TODO 拦截器登录，传登录信息
        //取用户信息
        //TbUser user = (TbUser) request.getAttribute("user");
        //补全orderInfo的属性
        //orderInfo.setUserId(user.getId());
        //orderInfo.setBuyerNick(user.getUsername());
        orderInfo.setUserId(1L);
        orderInfo.setBuyerNick("kris");
        //调用服务
        String orderId = mOrderService.createOrder(orderInfo);
        //把订单号传递给页面
        model.addAttribute("orderId", orderId);
        model.addAttribute("payment", orderInfo.getPayment());

        Date date = new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String time=sdf.format(date);//将Date对象转化为yyyy-MM-dd形式的字符串
        model.addAttribute("date", time);
        //返回逻辑视图
        return "success";
    }
}
