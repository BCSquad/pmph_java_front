package com.bc.pmpheep.back.authadmin.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.message.dao.OrgMessageDao;

@Service("com.bc.pmpheep.back.authadmin.message.service.MessageServiceImpl")
public class OrgMessageServiceImpl implements OrgMessageService {
	
	@Autowired
	OrgMessageDao orgMessageDao;

	
	
	
}
