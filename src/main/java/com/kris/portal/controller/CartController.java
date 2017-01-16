package com.kris.portal.controller;

import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 商品添加到购物车
 *
 * @author kris
 * @create 2017-01-16 17:41
 */
@Controller
public class CartController {

    @Autowired
    private CartService mCartService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        TaotaoResult result = mCartService.addCart(itemId, num, request, response);
        return "cartSuccess";
    }
}
