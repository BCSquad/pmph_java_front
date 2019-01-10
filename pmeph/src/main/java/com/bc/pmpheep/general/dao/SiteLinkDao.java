package com.bc.pmpheep.general.dao;

import java.util.List;

public interface SiteLinkDao {

    /**
     * 每行链接的html代码作为一个字符串，逐行以list集合返回
     * @return
     */
    List<String> getSiteHtmlPerRow();
}
