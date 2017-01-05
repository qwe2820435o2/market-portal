package com.kris.portal.controller;

import com.kris.portal.pojo.TbItem;
import com.kris.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kris
 * @create 2016-12-12 15:47
 */
@Controller
public class ItemController {
    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    private String getItemById(@PathVariable Long itemId, Model model) {
        TbItem item = itemService.getItemById(itemId);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}", produces = MediaType.TEXT_HTML_VALUE + ";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable Long itemId) {
        String desc = itemService.getItemDescById(itemId);
        return desc;
    }

}
