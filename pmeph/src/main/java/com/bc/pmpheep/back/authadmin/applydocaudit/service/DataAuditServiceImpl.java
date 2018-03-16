package com.bc.pmpheep.back.authadmin.applydocaudit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.authadmin.applydocaudit.dao.DataAuditDao;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessage;
import com.bc.pmpheep.back.commuser.mymessage.bean.MyMessageVO;
import com.bc.pmpheep.back.commuser.mymessage.dao.MyMessageDao;
import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import com.bc.pmpheep.back.commuser.personalcenter.bean.WriterUserTrendst;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.general.pojo.Message;
import com.bc.pmpheep.general.service.MessageService;

/**
 *
 * @ClassName: DataAuditServiceImpl
 * @Description: TODO
 * @author SunZhuoQun
 * @date 2017-12-5 上午9:29:34
 *
 */
@Service("com.bc.pmpheep.back.authadmin.applydocaudit.service.DataAuditServiceImpl")
public class DataAuditServiceImpl implements DataAuditService {
	@Autowired
	DataAuditDao dataAuditDao;

    @Autowired
    MyMessageDao myMessageDao;

	@Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;

    @Autowired
    @Qualifier("com.bc.pmpheep.general.service.MessageService")
    MessageService messageService;


	/**
	 * 查询列表
	 */
	@Override
	public List<Map<String, Object>> findDataAudit(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("startNum", pageParameter.getStart());
		paraMap.put("pageSize", pageParameter.getPageSize());
		paraMap.put("material_id",pageParameter.getParameter().get("material_id"));
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("userId", pageParameter.getParameter().get("userId"));
		List<Map<String, Object>> resultList = dataAuditDao
				.findDataAudit(paraMap);
		return resultList;
	}

	/**
	 * 查询条数
	 */
	@Override
	public int findDataAuditCount(
			PageParameter<Map<String, Object>> pageParameter) {
		Map<String, Object> paraMap = pageParameter.getParameter();
		paraMap.put("queryName", pageParameter.getParameter().get("queryName"));
		paraMap.put("material_id",pageParameter.getParameter().get("material_id"));
		paraMap.put("userId",pageParameter.getParameter().get("userId"));
		Integer count = dataAuditDao.findDataAuditCount(paraMap);
		Integer maxPageNum = (int) Math.ceil(1.0 * count
				/ pageParameter.getPageSize());
		return maxPageNum;
	}

	/**
	 * 标题
	 */
	@Override
	public String findTitleName(Map<String, Object> map) {
		String resultList = dataAuditDao.findTitleName(map);
		return resultList;
	}



	//申报审核通过
		@Override
		public int updateDeclarationPass(Map<String, Object> map) {
			Map<String,Object> dmap =personalService.queryDeclarationById(map.get("declaration_id").toString());
			WriterUserTrendst wut = new WriterUserTrendst(dmap.get("user_id").toString(), 8, dmap.get("material_id").toString());
			wut.declarationAuditDetail(dmap,"3");
			personalService.saveUserTrendst(wut);//机构申报审核 生成动态
			//通过后发送消息给申报人员
			String uidString=String.valueOf(map.get("auth_user_id"));
			Long thisId=Long.valueOf(uidString);
			String writer_id=String.valueOf(map.get("writer_id"));
			Long frendId=Long.valueOf(writer_id);
			String mid=String.valueOf(dmap.get("material_id"));
			Long material_id=Long.valueOf(mid);

			//发送者id   发送者类型    接收者id  接收者类型   标题   内容
			senNewMsgPass(material_id,thisId, new Short("3"), frendId, new Short("2"), "系统消息", "恭喜！您提交的《"+dmap.get("material_name").toString()+"》申报表已通过[学校管理员]审核!");
			return this.dataAuditDao.updateDeclarationPass(map);
		}

	//申报审核退回
	@Override
	public int updateDeclaration(Map<String, Object> map) {
		Map<String,Object> dmap =personalService.queryDeclarationById(map.get("declaration_id").toString());
		WriterUserTrendst wut = new WriterUserTrendst(dmap.get("user_id").toString(), 8, dmap.get("material_id").toString());
		wut.declarationAuditDetail(dmap,"2");
		//机构申报审核 生成动态
		personalService.saveUserTrendst(wut);
		//退回后发送消息给申报人员
		String uidString=String.valueOf(map.get("auth_user_id"));
		Long thisId=Long.valueOf(uidString);
		String writer_id=String.valueOf(map.get("writer_id"));
		Long frendId=Long.valueOf(writer_id);
		String mid=String.valueOf(dmap.get("material_id"));
		Long material_id=Long.valueOf(mid);
        String returnCause = dmap.get("return_cause").toString();
		senNewMsgBack(material_id,thisId, new Short("3"), frendId, new Short("2"), "系统消息", " 	抱歉，" +
                "您提交的《"+dmap.get("material_name").toString()+"》申报表被[学校管理员]退回，退回原因：" +returnCause+
                "，请您核对后重试!");
		return this.dataAuditDao.updateDeclaration(map);
	}

	//通过后发送消息给申报人员
	@Override
	public void senNewMsgPass(Long material_id,Long thisId, Short thisType, Long frendId,
			Short friendIdType, String title, String content) {
		   Message message = new Message();
	        message.setContent(content);
	        messageService.add(message);
	        MyMessage userMessage = new MyMessage();
	        userMessage.setMaterial_id(material_id);
	        userMessage.setMsgId(message.getId());
            userMessage.setMaterial_id(0L);
            userMessage.setMsgType(new Short("0"));
            userMessage.setTitle(title);
            userMessage.setSenderId(0L);
            userMessage.setSenderType(new Short("0"));
            userMessage.setReceiverId(frendId);

            userMessage.setReceiverType(new Short("2"));
	        myMessageDao.addUserMessage(userMessage);

	}
	//退回后发送消息给申报人员
	@Override
	public void senNewMsgBack(Long material_id,Long thisId, Short thisType, Long frendId,
			Short friendIdType, String title, String content) {
		// TODO
		   Message message = new Message();
	        message.setContent(content);
	        messageService.add(message);
	        MyMessage userMessage = new MyMessage();
	        userMessage.setMsgId(message.getId());
	        userMessage.setMaterial_id(0L);
	        userMessage.setMsgType(new Short("0"));
	        userMessage.setTitle(title);
	        userMessage.setSenderId(0L);
            userMessage.setSenderType(new Short("0"));
	        userMessage.setReceiverId(frendId);

	        userMessage.setReceiverType(new Short("2"));

	        myMessageDao.addUserMessage(userMessage);

	}

	//通过教材ID查出教材
	@Override
	public Map<String, Object> queryMaterialbyId(String material_id) {
		return this.dataAuditDao.queryMaterialbyId(material_id);
	}
	

	//更新Declaration时间
	@Override
	public void updateDeclarationUpdateTime(Map<String, Object> queryMap) {
		dataAuditDao.updateDeclarationUpdateTime(queryMap);

	}


    //通过教材ID查出所有教材下的书籍
    @Override
    public List<Map<String, Object>> queryBookById(String material_id) {
        return this.dataAuditDao.queryBookById(material_id);
    }

    @Override
    public List<Map<String, Object>> queryZjkzxxById(String material_id) {
        return this.dataAuditDao.queryZjkzxxById(material_id);
    }

    /**
     * 图书申报职位暂存
     */
    @Override
    public List<Map<String, Object>> queryTssbZc(Map<String, Object> map) {
        return this.dataAuditDao.queryTssbZc(map);
    }


    @Override
    public List<Map<String, Object>> queryPerson(Map<String, Object> map) {
        return this.dataAuditDao.queryPerson(map);
    }



    @Override
    public List<Map<String, Object>> queryStu(Map<String, Object> map) {
        return this.dataAuditDao.queryStu(map);
    }

    @Override
    public List<Map<String, Object>> queryWork(Map<String, Object> map) {
        return this.dataAuditDao.queryWork(map);
    }

    @Override
    public List<Map<String, Object>> queryStea(Map<String, Object> map) {
        return this.dataAuditDao.queryStea(map);
    }

    @Override
    public List<Map<String, Object>> queryJcbj(Map<String, Object> map) {
        return this.dataAuditDao.queryJcbj(map);
    }

    @Override
    public List<Map<String, Object>> queryGjkcjs(Map<String, Object> map) {
        return this.dataAuditDao.queryGjkcjs(map);
    }

    @Override
    public List<Map<String, Object>> rwsjcList(Map<String, Object> map) {
        return this.dataAuditDao.queryRwsjc(map);
    }

    @Override
    public List<Map<String, Object>> queryqtJcbx(Map<String, Object> map) {
        map.put("rank", "0");
        return this.dataAuditDao.queryJcbx(map);
    }

    @Override
    public List<Map<String, Object>> queryTsxz(Map<String, Object> map) {
        return this.dataAuditDao.queryTsxz(map);
    }




    @Override
    public List<Map<String, Object>> queryZjxs(Map<String, Object> map) {
        return this.dataAuditDao.queryZjxs(map);
    }


    @Override
    public List<Map<String, Object>> queryZjkyqk(Map<String, Object> map) {
        return this.dataAuditDao.queryZjkyqk(map);
    }



    @Override
    public List<Map<String, Object>> queryGjghjc(Map<String, Object> map) {
        return this.dataAuditDao.queryGjghjc(map);
    }



    @Override
    public List<Map<String, Object>> queryOrgById(String material_id) {
        return this.dataAuditDao.queryOrgById(material_id);
    }

    @Override
    public List<Map<String, Object>> queryZjkzbb(Map<String, Object> map) {
        return this.dataAuditDao.queryZjkzbb(map);
    }


    @Override
    public Map<String, Object> queryAchievement(Map<String, Object> map) {
        return this.dataAuditDao.queryAchievement(map);
    }



    @Override
    public List<Map<String, Object>> queryMonograph(Map<String, Object> map) {
        return this.dataAuditDao.queryMonograph(map);
    }


    @Override
    public List<Map<String, Object>> queryPublish(Map<String, Object> map) {
        return this.dataAuditDao.queryPublish(map);
    }


    @Override
    public List<Map<String, Object>> querySci(Map<String, Object> map) {
        return this.dataAuditDao.querySci(map);
    }



    @Override
    public List<Map<String, Object>> queryClinicalreward(Map<String, Object> map) {
        return this.dataAuditDao.queryClinicalreward(map);
    }


    @Override
    public List<Map<String, Object>> queryAcadereward(Map<String, Object> map) {
        return this.dataAuditDao.queryAcadereward(map);
    }

   
    @Override
    public Map<String, Object> queryMoocdigital(Map<String, Object> map) {
        return this.dataAuditDao.queryMoocdigital(map);
    }

    @Override
    public Map<String, Object> queryIntention(Map<String, Object> map) {
        return this.dataAuditDao.queryIntention(map);
    }

	
	
}
