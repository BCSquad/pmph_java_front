package com.bc.pmpheep.back.commuser.materialdec.service;

import com.bc.pmpheep.back.commuser.materialdec.dao.ExpertationDao;
import com.bc.pmpheep.back.commuser.materialdec.dao.MaterialDetailDao;
import com.bc.pmpheep.back.commuser.materialdec.dao.PersonInfoDao;
import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;
import com.bc.pmpheep.utils.UUIDTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("com.bc.pmpheep.back.commuser.materialdec.service.ExpertationServiceImpl")
public class ExpertationServiceImpl implements ExpertationService {

    @Autowired
    private MaterialDetailDao madd;
    @Autowired
    private ExpertationDao exdao;
    @Autowired
    private PersonInfoDao peradd;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
    MyMessageService messageService;
    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;

    public UUIDTool utool = new UUIDTool();

    //通过教材ID查出教材
    @Override
    public Map<String, Object> queryMaterialbyId(String product_id) {
        return this.exdao.queryProductbyId(product_id);
    }

    @Override
    public List<Map<String, Object>> selectSubject(Map<String, Object> map) {
        return this.exdao.selectSubject(map);
    }

    @Override
    public List<Map<String, Object>> selectContent(Map<String, Object> map) {
        return this.exdao.selectContent(map);
    }
    @Override
    public List<Map<String, Object>> selectSbzy(Map<String, Object> map) {
        return this.exdao.selectSbzy(map);
    }

    @Override
    public List<Map<String, Object>> queryPerson(Map<String, Object> map) {
        return this.exdao.queryPerson(map);
    }

    @Override
    public Map<String,Object> insertJcsbxx(
            Map<String, Object> perMap,
            List<Map<String, Object>> stuList,
            List<Map<String, Object>> workList,
            List<Map<String, Object>> zjxsList,
            List<Map<String, Object>> zjkzqkList,
            List<Map<String, Object>> monographList,
            List<Map<String, Object>> pmphList,
            List<Map<String, Object>> subjectList,
            List<Map<String, Object>> contentList,
            List<Map<String,Object>> sbzyList,
            List<Map<String, Object>> editorList,
            List<Map<String, Object>> wzfbqkList,
            List<Map<String, Object>> bzyhjqkList
    ) {
        //1.新增申报表
    	if(perMap.get("org_id")!=null && "".equals(perMap.get("org_id").toString().trim()) ){
    		perMap.put("org_id",null); //若暂存未选机构
    	}
    	
        this.exdao.insertPerson(perMap);
        //2.更新人员信息表
        if (perMap.get("type").equals("1")) { //提交
            this.madd.updateWriter(perMap);
        }
        //查询上面新增的申报表ID
        List<Map<String, Object>> perList = this.exdao.queryPerson(perMap);
        Object declaration_id = perList.get(0).get("id");
        //获取userid
        String user_id = perMap.get("user_id").toString();

        //学科分类
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Map<String, Object> map : subjectList) {
                map.put("expertation_id",declaration_id);
                this.exdao.insertSubject(map);
            }
        }
        //内容分类
        if (contentList != null && !contentList.isEmpty()) {
            for (Map<String, Object> map : contentList) {
                map.put("expertation_id",declaration_id);
                this.exdao.insertContent(map);
            }
        }
        //专业分类
        if (sbzyList != null && !sbzyList.isEmpty()) {
            for (Map<String, Object> map : sbzyList) {
                map.put("expertation_id",declaration_id);
                this.exdao.insertSbzy(map);
            }
        }
        //3.作家学习经历新增
        if (stuList != null && !stuList.isEmpty()) {
            for (Map<String, Object> map : stuList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerStu(map);
                    }else{
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerStu(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertStu(map);
            }
        }
        //4.作家工作经历新增
        if (workList != null && !workList.isEmpty()) {
            for (Map<String, Object> map : workList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerWork(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerWork(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertWork(map);
            }
        }
        //6.作家兼职学术新增
        if (zjxsList != null && !zjxsList.isEmpty()) {
            for (Map<String, Object> map : zjxsList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerZjxs(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerZjxs(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertZjxs(map);
            }
        }
        //7.主编或参编图书情况
        if (editorList != null && !editorList.isEmpty()) {
            for (Map<String, Object> map : editorList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerEditor(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerEditor(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertEditor(map);
            }
        }
        //12.作家扩展项新增
        if (zjkzqkList != null && !zjkzqkList.isEmpty()) {
            for (Map<String, Object> map : zjkzqkList) {
                map.put("declaration_id", declaration_id);
                this.exdao.insertZjkzbb(map);
            }
        }
        //14.主编学术专著新增
        if (monographList != null && !monographList.isEmpty()) {
            for (Map<String, Object> map : monographList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerMonograph(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerMonograph(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertMonograph(map);
            }
        }
        //19.人卫社教材编写新增
        if (pmphList != null && !pmphList.isEmpty()) {
            for (Map<String, Object> map : pmphList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerRwsjc(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerRwsjc(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertRwsjc(map);
            }
        }

        //文章发表情况新增
        if (wzfbqkList != null && !wzfbqkList.isEmpty()) {
            for (Map<String, Object> map : wzfbqkList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePreWzfbqk(map);
                    }else{
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPreWzfbqk(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertWzfbqk(map);
            }
        }

        //本专业获奖情况新增
        if (bzyhjqkList != null && !bzyhjqkList.isEmpty()) {
            for (Map<String, Object> map : bzyhjqkList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePreBzyhjqk(map);
                    }else{
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPreBzyhjqk(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertBzyhjqk(map);
            }
        }
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("msg","OK");
        returnMap.put("declaration_id",declaration_id);
        return returnMap;
    }

    @Override
    public Map<String,Object> updateJcsbxx(Map<String, Object> perMap,
                                           List<Map<String, Object>> stuList,
                                           List<Map<String, Object>> workList,
                                           List<Map<String, Object>> zjxsList,
                                           List<Map<String, Object>> zjkzqkList,
                                           List<Map<String, Object>> monographList,
                                           List<Map<String, Object>> pmphList,
                                           List<Map<String,Object>> subjectList,
                                           List<Map<String,Object>> contentList,
                                           List<Map<String,Object>> sbzyList,
                                           List<Map<String,Object>> editorList,
                                           List<Map<String, Object>> wzfbqkList,
                                           List<Map<String, Object>> bzyhjqkList,
                                           String declaration_id) {
        //修改申报信息
        perMap.put("declaration_id", declaration_id);
        this.exdao.updatePerson(perMap);
        //获取userid
        String user_id = perMap.get("user_id").toString();
        if (perMap.get("type").equals("1")) { //提交
            this.madd.updateWriter(perMap);

        }
        //删除暂存内容
        Map<String, Object> glMap = new HashMap<String, Object>();
        glMap.put("declaration_id", declaration_id);
        this.exdao.DelStu(glMap);    //学习经历
        this.exdao.DelWork(glMap);   //工作经历
        this.exdao.DelZjxs(glMap);   //作家学术
        this.exdao.DelGjghjc(glMap); //作家主编国家级规划教材情况
        this.exdao.DelMonograph(glMap); ////主编学术专著情况
        this.exdao.delZjkzbb(glMap);   //扩展信息
        this.exdao.DelRwsjc(glMap);  //人卫社教材
        this.exdao.delEditor(glMap);  //主编或参编图书情况
        this.exdao.DelWzfbqk(glMap);//删除文章发表情况
        this.exdao.DelBzyhjqk(glMap);//删除本专业获奖情况
        this.exdao.delContent(declaration_id);
        this.exdao.delSubject(declaration_id);
        this.exdao.delSbzy(declaration_id);


        //学科分类
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Map<String, Object> map : subjectList) {
                map.put("expertation_id",declaration_id);
                this.exdao.insertSubject(map);
            }
        }
        //内容分类
        if (contentList != null && !contentList.isEmpty()) {
            for (Map<String, Object> map : contentList) {
                map.put("expertation_id",declaration_id);
                this.exdao.insertContent(map);
            }
        }
        //申报专业
        if (sbzyList != null && !sbzyList.isEmpty()) {
            for (Map<String, Object> map : sbzyList) {
                map.put("expertation_id",declaration_id);
                this.exdao.insertSbzy(map);
            }
        }
        //3.作家学习经历新增
        if (stuList != null && !stuList.isEmpty()) {
            for (Map<String, Object> map : stuList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerStu(map);
                    }else{
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerStu(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertStu(map);
            }
        }

        //文章发表情况新增
        if (wzfbqkList != null && !wzfbqkList.isEmpty()) {
            for (Map<String, Object> map : wzfbqkList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePreWzfbqk(map);
                    }else{
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPreWzfbqk(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertWzfbqk(map);
            }
        }

        //本专业获奖情况新增
        if (bzyhjqkList != null && !bzyhjqkList.isEmpty()) {
            for (Map<String, Object> map : bzyhjqkList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePreBzyhjqk(map);
                    }else{
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPreBzyhjqk(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertBzyhjqk(map);
            }
        }

        //4.作家工作经历新增
        if (workList != null && !workList.isEmpty()) {
            for (Map<String, Object> map : workList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerWork(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerWork(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertWork(map);
            }
        }
        //6.作家兼职学术新增
        if (zjxsList != null && !zjxsList.isEmpty()) {
            for (Map<String, Object> map : zjxsList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerZjxs(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerZjxs(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertZjxs(map);
            }
        }
        //7.主编或参编图书情况
        if (editorList != null && !editorList.isEmpty()) {
            for (Map<String, Object> map : editorList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    /*if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerZjxs(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerZjxs(map);
                    }*/
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertEditor(map);
            }
        }
        //12.作家扩展项新增
        if (zjkzqkList != null && !zjkzqkList.isEmpty()) {
            for (Map<String, Object> map : zjkzqkList) {
                map.put("declaration_id", declaration_id);
                this.exdao.insertZjkzbb(map);
            }
        }
        //14.主编学术专著新增
        if (monographList != null && !monographList.isEmpty()) {
            for (Map<String, Object> map : monographList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerMonograph(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerMonograph(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertMonograph(map);
            }
        }
        //19.人卫社教材编写新增
        if (pmphList != null && !pmphList.isEmpty()) {
            for (Map<String, Object> map : pmphList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerRwsjc(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerRwsjc(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.exdao.insertRwsjc(map);
            }
        }
        List<Map<String, Object>> perList = this.exdao.queryPerson(perMap);
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("msg","OK");
        returnMap.put("declaration_id",declaration_id);
        return returnMap;
    }

    @Override
    public PageResult<Map<String, Object>> selectSubjectList(
            PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());

        List<Map<String, Object>> list = exdao.querySubjectList(pageParameter);
        int count = exdao.querySubjectCount(pageParameter);

        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public PageResult<Map<String, Object>> selectContentList(
            PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());

        List<Map<String, Object>> list = exdao.queryContentList(pageParameter);
        int count = exdao.queryContentCount(pageParameter);

        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public PageResult<Map<String, Object>> querySbzyList(
            PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());

        List<Map<String, Object>> list = exdao.querySbzyList(pageParameter);
        int count = exdao.querySbzyCount(pageParameter);

        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public List<Map<String, Object>> queryExpertation(String user_id) {
        List<Map<String, Object>> list=exdao.queryExpertation(user_id);
        /*for (Map<String, Object> map: list) {
            if(map.get("type_name") !=null) {
                String psttype_name = map.get("type_name").toString();
                String[] psttype = psttype_name.split("_,_");
                map.put("type_name", psttype);
            }

            if(map.get("name_path") !=null) {
                String pdname_path = map.get("name_path").toString();
                String[] pdname = pdname_path.split("_,_");
                map.put("name_path", pdname);
            }
        }*/
        return list;
    }

    @Override
    public Map<String, Object> queryExpertationDetail(String user_id, String expert_type) {
        return exdao.queryExpertationDetail(user_id,expert_type);
    }

    @Override
    public List<Map<String, Object>> queryStu(Map<String, Object> map) {
        return this.exdao.queryStu(map);
    }

    @Override
    public List<Map<String, Object>> queryWork(Map<String, Object> map) {
        return this.exdao.queryWork(map);
    }

    @Override
    public List<Map<String, Object>> rwsjcList(Map<String, Object> map) {
        return this.exdao.queryRwsjc(map);
    }

    @Override
    public List<Map<String, Object>> queryZjxs(Map<String, Object> map) {
        return this.exdao.queryZjxs(map);
    }

    @Override
    public List<Map<String, Object>> queryGjghjc(Map<String, Object> map) {
        return this.exdao.queryGjghjc(map);
    }

    @Override
    public List<Map<String, Object>> queryZjkzbb(Map<String, Object> map) {
        return this.exdao.queryZjkzbb(map);
    }

    @Override
    public List<Map<String, Object>> queryMonograph(Map<String, Object> map) {
        return this.exdao.queryMonograph(map);
    }

    @Override
    public List<Map<String, Object>> queryEditor(Map<String, Object> map) {
        return this.exdao.queryEditor(map);
    }

    @Override
    public List<Map<String, Object>> queryWzfbqk(Map<String, Object> map) {
        return this.exdao.queryWzfbqk(map);
    }

    @Override
    public List<Map<String, Object>> queryBzyhjqk(Map<String, Object> map) {
        return this.exdao.queryBzyhjqk(map);
    }


    @Override
    public List<Map<String, Object>> queryZjkzxxById(String material_id) {
        return this.exdao.queryZjkzxxById(material_id);
    }

    @Override
    public Map<String, Object> queryProduct(String expert_type) {
        return this.exdao.queryProduct(expert_type);
    }

	@Override
	public int updateExpertationPass(Map<String, Object> paramMap) {
		
		int count = exdao.updateExpertationAudit(paramMap);

		return count;
	}

	@Override
	public Map<String, Object> queryOrgById(String id) {
		Map<String, Object> map = new HashMap();
		if("0".equals(id)){
			map.put(id, "0");
			map.put("org_name", "人民卫生出版社");
		}else{
			map = exdao.queryOrgById(id);
		}
		return map;
	}
	
	@Override
    public PageResult<Map<String, Object>> selectOrgList(
            PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());

        List<Map<String, Object>> list = exdao.queryOrgList(pageParameter);
        int count = exdao.queryOrgCount(pageParameter);

        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

}
