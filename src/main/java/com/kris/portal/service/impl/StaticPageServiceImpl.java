package com.kris.portal.service.impl;

import com.kris.portal.pojo.TaotaoResult;
import com.kris.portal.pojo.TbItem;
import com.kris.portal.service.ItemService;
import com.kris.portal.service.StaticPageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * @author kris
 * @create 2017-01-11 15:06
 */
@Service
public class StaticPageServiceImpl implements StaticPageService{

    @Autowired
    private ItemService mItemService;
    @Autowired
    private FreeMarkerConfigurer mFreeMarkerConfigurer;
    @Value("${STATIC_PAGE_PATH}")
    private String STATIC_PAGE_PATH;

    @Override
    public TaotaoResult getItemHtml(Long itemId) throws IOException, TemplateException {
        //商品基本信息
        TbItem tbItem = mItemService.getItemById(itemId);
        //商品描述
        String itemDesc = mItemService.getItemDescById(itemId);
        //规格参数
        String itemParam = mItemService.getItemParamById(itemId);
        //生成静态页面
        Configuration configuration = mFreeMarkerConfigurer.getConfiguration();
        Template template = configuration.getTemplate("item.ftl");
        //创建一个数据集
        Map root = new HashMap<>();
        //向数据集中添加属性
        root.put("item", tbItem);
        root.put("itemDesc", itemDesc);
        root.put("itemParam", itemParam);
        //创建一个Writer对象
        Writer out =new FileWriter(new File(STATIC_PAGE_PATH+itemId+".html"));
        //生成静态文件
        template.process(root, out);
        out.flush();
        out.close();

        return TaotaoResult.ok();
    }
}
