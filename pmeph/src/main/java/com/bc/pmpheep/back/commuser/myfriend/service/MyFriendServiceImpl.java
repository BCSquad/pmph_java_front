package com.bc.pmpheep.back.commuser.myfriend.service;


import com.bc.pmpheep.back.commuser.myfriend.dao.MyFriendDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by cyx  on 2017/11/21
 */
@Service("com.bc.pmpheep.back.commuser.myfriend.service.MyFriendServiceImpl")
public class MyFriendServiceImpl implements MyFriendService {

    @Autowired
    private MyFriendDao myFriendDao;
    @Override
    public List<Map<String,Object>> getMyFriends(String userId) {
        return myFriendDao.getMyFriends(userId);
    }
}
