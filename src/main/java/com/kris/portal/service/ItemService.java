package com.kris.portal.service;

import com.kris.portal.pojo.TbItem;

/**
 * @author kris
 * @create 2016-12-12 15:44
 */
public interface ItemService {
    TbItem getItemById(Long itemId);
    String getItemDescById(Long itemId);
    String getItemParamById(Long itemId);
}
