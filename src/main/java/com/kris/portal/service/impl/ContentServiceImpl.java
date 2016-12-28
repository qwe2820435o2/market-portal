package com.kris.portal.service.impl;

import com.kris.portal.pojo.AdNode;
import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.pojo.TbContent;
import com.kris.portal.service.ContentService;
import com.kris.portal.utils.HttpClientUtil;
import com.kris.portal.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kris
 * @create 2016-12-28 11:10
 */
@Service
public class ContentServiceImpl implements ContentService{

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_CONTENT_URL}")
    private String REST_CONTENT_URL;
    @Value("${REST_CONTENT_AD_CID}")
    private String REST_CONTENT_AD_CID;

    @Override
    public String getAdList() {
        //调用服务获得数据
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_URL + REST_CONTENT_AD_CID);
        //把json转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToList(json, TbContent.class);
        //取data属性，内容列表
        List<TbContent> contentList = (List<TbContent>) taotaoResult.getData();
        //把内容列表转换成AdNode列表
        List<AdNode> resultList = new ArrayList<>();
        for (TbContent tbcontent : contentList) {
            AdNode node = new AdNode();
            node.setHeight(240);
            node.setWidth(670);
            node.setSrc(tbcontent.getPic());

            node.setHeightB(240);
            node.setWidthB(550);
            node.setSrcB(tbcontent.getPic2());

            node.setAlt(tbcontent.getSubTitle());
            node.setHref(tbcontent.getUrl());

            resultList.add(node);
        }
        //需要把resultList转换成json数据
        String resultJson = JsonUtils.objectToJson(resultList);
        return resultJson;
    }
}
