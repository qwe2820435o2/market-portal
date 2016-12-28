package com.kris.portal.controller;

import com.kris.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页
 *
 * @author kris
 * @create 2016-12-20 12:50
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService mContentService;

    @RequestMapping("index")
    public String showIndexPage(Model model){
        //取大广告位内容
        String json = mContentService.getAdList();
        //传递给界面
        model.addAttribute("ad1", json);
        return "index";
    }
}
