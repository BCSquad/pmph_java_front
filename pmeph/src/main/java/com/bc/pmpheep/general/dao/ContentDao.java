package com.bc.pmpheep.general.dao;

import com.bc.pmpheep.general.pojo.Content;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: SuiXinYang
 * @Description: MongoDB CMS内容数据访问层
 * @Date: Created in 10:09 2017/11/15
 * @Modified: SuiXinYang
 **/
public interface ContentDao extends CrudRepository<Content,String>{
}
