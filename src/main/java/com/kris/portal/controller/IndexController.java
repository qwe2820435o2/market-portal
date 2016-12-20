package com.kris.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示首页
 *
 * @author kris
 * @create 2016-12-20 12:50
 */
@Controller
public class IndexController {

    @RequestMapping("index")
    public String showIndexPage(){
        return "index";
    }
}
