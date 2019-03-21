package com.bc.pmpheep.general.service;

import com.bc.pmpheep.general.dao.SiteLinkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service("com.bc.pmpheep.general.service.SiteLinkService")
public class SiteLinkService {

    @Autowired
    SiteLinkDao siteLinkDao;

    public List<String> getSiteHtmlPerRow(){
        List<String> result = siteLinkDao.getSiteHtmlPerRow();
        return result;
    }

}
