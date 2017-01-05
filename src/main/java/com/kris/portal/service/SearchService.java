package com.kris.portal.service;

import com.kris.portal.pojo.SearchResult;

/**
 * @author kris
 * @create 2017-01-03 13:44
 */
public interface SearchService {
    SearchResult search(String keyword, int page, int rows);
}
