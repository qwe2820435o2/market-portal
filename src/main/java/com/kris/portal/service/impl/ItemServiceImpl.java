package com.kris.portal.service.impl;

import com.kris.portal.pojo.PortalItem;
import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.pojo.TbItem;
import com.kris.portal.pojo.TbItemDesc;
import com.kris.portal.service.ItemService;
import com.kris.portal.utils.HttpClientUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author kris
 * @create 2016-12-12 15:45
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_ITEM_BASE_URL}")
    private String REST_ITEM_BASE_URL;
    @Value("${REST_ITEM_DESC_URL}")
    private String REST_ITEM_DESC_URL;


    @Override
    public TbItem getItemById(Long itemId) {
        //根据商品id查询商品基本信息
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_BASE_URL + itemId);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, PortalItem.class);
        //取商品对象
        TbItem item = (TbItem) taotaoResult.getData();
        return item;

    }

    @Override
    public String getItemDescById(Long itemId) {
        //根据商品id调用rest的服务获得数据
        //http://localhost:8081/rest/item/desc/xxx
        String json = HttpClientUtil.doGet(REST_BASE_URL + REST_ITEM_DESC_URL + itemId);
        //转换成java对象
        TaotaoResult taotaoResult = TaotaoResult.formatToPojo(json, TbItemDesc.class);
        //取商品描述
        TbItemDesc itemDesc= (TbItemDesc) taotaoResult.getData();
        String desc = itemDesc.getItemDesc();
        return desc;
    }
}
