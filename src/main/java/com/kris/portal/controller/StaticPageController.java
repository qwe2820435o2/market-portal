package com.kris.portal.controller;

import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.service.StaticPageService;
import com.kris.portal.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 根据商品id生成静态页面
 *
 * @author kris
 * @create 2017-01-11 16:08
 */
@Controller
public class StaticPageController {

    @Autowired
    private StaticPageService mStaticPageService;

    @RequestMapping("/gen/item/{itemId}")
    @ResponseBody
    public TaotaoResult genItemPage(@PathVariable Long itemId) {
        try {
            TaotaoResult result = mStaticPageService.getItemHtml(itemId);
            return result;
        } catch(Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
