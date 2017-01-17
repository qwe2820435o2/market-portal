package com.kris.portal.service;

import com.kris.portal.pojo.CartItem;
import com.kris.portal.pojo.TaotaoResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author kris
 * @create 2017-01-16 17:12
 */
public interface CartService {
    TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
    List<CartItem> getCartItems(HttpServletRequest request);
    TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response);
    TaotaoResult deleteCartItem(long itemId,HttpServletRequest request,HttpServletResponse response);
}
