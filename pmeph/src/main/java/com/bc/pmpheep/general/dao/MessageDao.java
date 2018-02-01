package com.bc.pmpheep.general.dao;

import com.bc.pmpheep.general.pojo.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * @Author: SuiXinYang
 * @Description: MongoDB消息内容数据访问层
 * @Date: Created in 10:20 2017/11/15
 * @Modified: SuiXinYang
 **/
public interface MessageDao extends CrudRepository<Message, String> {
}
