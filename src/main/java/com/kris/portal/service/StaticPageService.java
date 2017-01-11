package com.kris.portal.service;

import com.kris.portal.pojo.TaotaoResult;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * @author kris
 * @create 2017-01-11 15:04
 */
public interface StaticPageService {
    TaotaoResult getItemHtml(Long itemId) throws IOException, TemplateException;
}
