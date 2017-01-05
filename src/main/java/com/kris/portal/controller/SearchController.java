package com.kris.portal.controller;

import com.kris.portal.pojo.SearchResult;
import com.kris.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 调用rest搜索服务
 * @author kris
 * @create 2017-01-03 13:54
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService mSearchService;

    @RequestMapping("/search")
    public String search(@RequestParam("q")String keyword,
                         @RequestParam(defaultValue="1")Integer page,
                         @RequestParam(defaultValue="60")Integer rows, Model model){
        //get乱码处理
        try {
            keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
        } catch (Exception e) {
            keyword = "";
            e.printStackTrace();
        }
        SearchResult searchResult = mSearchService.search(keyword, page, rows);
        //参数传递给页面
        model.addAttribute("query", keyword);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", searchResult.getCurPage());

        //返回逻辑视图
        return "search";
    }
}
