package com.kris.portal.service.impl;

import com.kris.portal.pojo.CartItem;
import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.pojo.TbItem;
import com.kris.portal.service.CartService;
import com.kris.portal.service.ItemService;
import com.kris.portal.utils.CookieUtils;
import com.kris.portal.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kris
 * @create 2017-01-16 17:14
 */
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private ItemService mItemService;
    @Value("${COOKIE_EXPIRE}")
    private Integer COOKIE_EXPIRE;

    @Override
    public TaotaoResult addCart(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //接收商品id
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItemList(request);
        //从商品列表中查询列表是否存在此商品
        boolean havaFlg = false;
        for (CartItem cartItem :
                itemList) {
            //如果商品存在数量相加
            //如果存在商品的数量加上参数中的商品数量
            if (cartItem.getId().longValue() == itemId) {
                cartItem.setNum(cartItem.getNum() + num);
                havaFlg = true;
                break;
            }
        }
        //如果不存在，调用rest服务，根据商品id获得商品数据
        if (!havaFlg) {
            TbItem item = mItemService.getItemById(itemId);
            //转换成CartItem
            CartItem cartItem = new CartItem();
            cartItem.setId(itemId);
            cartItem.setNum(num);
            cartItem.setPrice(item.getPrice());
            cartItem.setTitle(item.getTitle());
            if (StringUtils.isNoneBlank(item.getImage())) {
                String image = item.getImage();
                String[] strings = image.split(",");
                cartItem.setImage(strings[0]);
            }
            //添加到购物车商品列表
            //把商品数据添加到列表中
            itemList.add(cartItem);
        }
        //把购物车商品列表写入cookie
        CookieUtils.setCookie(request,response,"MARKET_CART",JsonUtils.objectToJson(itemList),COOKIE_EXPIRE,true);
        //返回TaotaoResult
        return TaotaoResult.ok();
    }

    /**
     * 取购物车商品列表
     *
     * @param request
     * @return
     */
    private List<CartItem> getCartItemList(HttpServletRequest request) {
        try {
            //从cookie中取商品列表
            String json = CookieUtils.getCookieValue(request, "MARKET_CART", true);
            //把json转换成java对象
            List<CartItem> list = JsonUtils.jsonToList(json, CartItem.class);

            return list == null ? new ArrayList<CartItem>() : list;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<CartItem>();
        }
    }

    public List<CartItem> getCartItems(HttpServletRequest request){
        List<CartItem> list = getCartItemList(request);
        return list;
    }

    @Override
    public TaotaoResult updateCartItem(long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItems(request);
        //根据商品id查询商品
        for (CartItem cartItem :
                itemList) {
            if (cartItem.getId() == itemId) {
                //更新数量
                cartItem.setNum(num);
                break;
            }
        }
        //写入cookie
        CookieUtils.setCookie(request,response,"MARKET_CART",JsonUtils.objectToJson(itemList),COOKIE_EXPIRE,true);

        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult deleteCartItem(long itemId, HttpServletRequest request, HttpServletResponse response) {
        //接收商品id
        //从cookie中取购物车商品列表
        List<CartItem> itemList = getCartItems(request);

        //找到对应id的商品
        for (CartItem cartItem:
             itemList) {
            if (cartItem.getId() == itemId) {
                //删除商品
                itemList.remove(cartItem);
                break;
            }
        }
        //再重新把商品列表写入cookie
        CookieUtils.setCookie(request, response, "MARKET_CART", JsonUtils.objectToJson(itemList),COOKIE_EXPIRE,true);
        //返回成功
        return TaotaoResult.ok();
    }
}
