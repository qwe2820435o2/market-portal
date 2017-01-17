package com.kris.portal.controller;

import com.kris.portal.pojo.CartItem;
import com.kris.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    private CartService mCartService;

    @RequestMapping("/order-cart")
    public String showOrderCart(Model model, HttpServletRequest request) {
        //取购物车商品列表
        List<CartItem> list = mCartService.getCartItems(request);
        model.addAttribute("cartList", list);
        return "order-cart";
    }

}
