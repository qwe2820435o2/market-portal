package com.kris.portal.service.impl;

import com.kris.portal.pojo.SearchResult;
import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.service.SearchService;
import com.kris.portal.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author kris
 * @create 2017-01-03 13:45
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Value("${SEARCH_BASE_URL}")
    private String SEARCH_BASE_URL;

    @Override
    public SearchResult search(String keyword, int page, int rows) {
        //调用服务查询商品列表
        Map<String, String> param = new HashMap<>();
        param.put("keyword", keyword);
        param.put("page", page+"");
        param.put("rows", rows+"");
        //调用服务
        String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, SearchResult.class);
        //取返回的结果
        SearchResult searchResult = (SearchResult) taotaoResult.getData();

        return searchResult;
    }
}
