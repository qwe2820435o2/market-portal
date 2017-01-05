package com.kris.portal.pojo;

/**
 * TbItem适配扩展
 *
 * @author kris
 * @create 2017-01-05 10:26
 */
public class PortalItem extends TbItem {
    public String[] getImages(){
        String images = this.getImage();
        if (images != null && !images.equals("")) {
            String[] imgs = images.split(",");
            return imgs;
        }
        return null;
    }
}
