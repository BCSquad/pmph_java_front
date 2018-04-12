package com.bc.pmpheep.back.commuser.materialdec.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bc.pmpheep.back.commuser.mymessage.service.MyMessageService;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.bc.pmpheep.back.commuser.materialdec.dao.MaterialDetailDao;
import com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.plugin.PageResult;

@Service("com.bc.pmpheep.back.commuser.materialdec.service.MaterialDetailServiceImpl")
public class MaterialDetailServiceImpl implements MaterialDetailService {

    @Autowired
    private MaterialDetailDao madd;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.mymessage.service.MyMessageServiceImpl")
    MyMessageService messageService;

    @Autowired
    @Qualifier("com.bc.pmpheep.back.commuser.personalcenter.service.PersonalService")
    private PersonalService personalService;

    //通过教材ID查出教材
    @Override
    public Map<String, Object> queryMaterialbyId(String material_id) {
        return this.madd.queryMaterialbyId(material_id);
    }

    //通过教材ID查出所有教材下的书籍
    @Override
    public List<Map<String, Object>> queryBookById(String material_id) {
        return this.madd.queryBookById(material_id);
    }

    @Override
    public List<Map<String, Object>> queryZjkzxxById(String material_id) {
        return this.madd.queryZjkzxxById(material_id);
    }

    /**
     * 图书申报职位暂存
     */
    @Override
    public List<Map<String, Object>> queryTssbZc(Map<String, Object> map) {
        return this.madd.queryTssbZc(map);
    }

    @Override
    public int insertTssbZc(Map<String, Object> map) {
        return this.madd.insertTssbZc(map);
    }

    @Override
    public List<Map<String, Object>> queryPerson(Map<String, Object> map) {
        return this.madd.queryPerson(map);
    }

    @Override
    public int insertPerson(Map<String, Object> map) {
        // TODO 动态生成
        return this.madd.insertPerson(map);
    }

    @Override
    public List<Map<String, Object>> queryStu(Map<String, Object> map) {
        return this.madd.queryStu(map);
    }

    @Override
    public List<Map<String, Object>> queryWork(Map<String, Object> map) {
        return this.madd.queryWork(map);
    }

    @Override
    public List<Map<String, Object>> queryStea(Map<String, Object> map) {
        return this.madd.queryStea(map);
    }

    @Override
    public List<Map<String, Object>> queryJcbj(Map<String, Object> map) {
        return this.madd.queryJcbj(map);
    }

    @Override
    public List<Map<String, Object>> queryGjkcjs(Map<String, Object> map) {
        return this.madd.queryGjkcjs(map);
    }

    @Override
    public List<Map<String, Object>> rwsjcList(Map<String, Object> map) {
        return this.madd.queryRwsjc(map);
    }

    @Override
    public List<Map<String, Object>> queryqtJcbx(Map<String, Object> map) {
        map.put("rank", "0");
        return this.madd.queryJcbx(map);
    }

    @Override
    public List<Map<String, Object>> queryTsxz(Map<String, Object> map) {
        return this.madd.queryTsxz(map);
    }

    @Override
    public int insertTsxz(Map<String, Object> map) {
        int result = this.madd.insertTsxz(map);
        /*WriterUserTrendst wut = new WriterUserTrendst((Long)map.get("author_id"), 8, (Long)map.get("table_trendst_id"));
        personalService.saveUserTrendst(wut);*/
        return result;
    }

    @Override
    public int insertStu(Map<String, Object> map) {
        return this.madd.insertStu(map);
    }

    @Override
    public int insertWork(Map<String, Object> map) {
        return this.madd.insertWork(map);
    }

    @Override
    public List<Map<String, Object>> queryZjxs(Map<String, Object> map) {
        return this.madd.queryZjxs(map);
    }

    @Override
    public int insertZjxs(Map<String, Object> map) {
        return this.madd.insertZjxs(map);
    }

    @Override
    public int insertStea(Map<String, Object> map) {
        return this.madd.insertStea(map);
    }

    @Override
    public int insertJcbj(Map<String, Object> map) {
        return this.madd.insertJcbj(map);
    }

    @Override
    public int insertGjkcjs(Map<String, Object> map) {
        return this.madd.insertGjkcjs(map);
    }

    @Override
    public int insertJcbx(Map<String, Object> map) {
        return this.madd.insertJcbx(map);
    }

    @Override
    public List<Map<String, Object>> queryZjkyqk(Map<String, Object> map) {
        return this.madd.queryZjkyqk(map);
    }

    @Override
    public int insertZjkyqk(Map<String, Object> map) {
        return this.madd.insertZjkyqk(map);
    }

    @Override
    public List<Map<String, Object>> queryGjghjc(Map<String, Object> map) {
        return this.madd.queryGjghjc(map);
    }

    @Override
    public int insertGjghjc(Map<String, Object> map) {
        return this.madd.insertGjghjc(map);
    }

    @Override
    public List<Map<String, Object>> queryOrgById(String material_id) {
        return this.madd.queryOrgById(material_id);
    }

    @Override
    public List<Map<String, Object>> queryZjkzbb(Map<String, Object> map) {
        return this.madd.queryZjkzbb(map);
    }

    @Override
    public int insertZjZjkzbb(Map<String, Object> map) {
        return this.madd.insertZjkzbb(map);
    }

    @Override
    public int delGlxx(Map<String, Object> map) {

        this.madd.DelGjghjc(map); //作家主编国家级规划教材情况
        this.madd.DelGjkcjs(map); //精品课程建设
        this.madd.DelJcbj(map);  //上版教材编辑
        this.madd.DelJcbx(map);  //教材编写情况
        this.madd.DelTssbZc(map); //图书申报职位暂存
        this.madd.DelStea(map);   //教学经历
        this.madd.DelZjkyqk(map); //作家科研情况表
        this.madd.DelZjxs(map);   //作家学术
        this.madd.DelStu(map);    //学习经历
        this.madd.DelWork(map);   //工作经历
        this.madd.DelAcadereward(map); //学术荣誉授予情况
        this.madd.DelMonograph(map); ////主编学术专著情况
        this.madd.DelClinicalreward(map); //临床医学获奖情况
        this.madd.DelPublish(map);  //出版行业获奖情况
        this.madd.DelSci(map);   //SCI论文投稿及影响因子
        this.madd.delZjkzbb(map);   //扩展信息
        return 1;
    }

    @Override
    public int updateDeclaration(Map<String, Object> map) {
        return this.madd.updateDeclaration(map);
    }

    @Override
    public int updatePerson(Map<String, Object> map) {
        // TODO 动态生成
        return this.madd.updatePerson(map);
    }

    @Override
    public PageResult<Map<String, Object>> selectOrgList(
            PageParameter<Map<String, Object>> pageParameter) {
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setPageNumber(pageParameter.getPageNumber());
        pageResult.setPageSize(pageParameter.getPageSize());

        List<Map<String, Object>> list = madd.queryOrgList(pageParameter);
        int count = madd.queryOrgCount(pageParameter);

        pageResult.setRows(list);
        pageResult.setTotal(count);
        return pageResult;
    }

    @Override
    public Map<String, Object> queryAchievement(Map<String, Object> map) {
        return this.madd.queryAchievement(map);
    }

    @Override
    public int insertAchievement(Map<String, Object> map) {
        return this.madd.insertAchievement(map);
    }

    @Override
    public int updateAchievement(Map<String, Object> map) {
        return this.madd.updateAchievement(map);
    }

    @Override
    public List<Map<String, Object>> queryMonograph(Map<String, Object> map) {
        return this.madd.queryMonograph(map);
    }

    @Override
    public int insertMonograph(Map<String, Object> map) {
        return this.madd.insertMonograph(map);
    }

    @Override
    public List<Map<String, Object>> queryPublish(Map<String, Object> map) {
        return this.madd.queryPublish(map);
    }

    @Override
    public int insertPublish(Map<String, Object> map) {
        return this.madd.insertPublish(map);
    }

    @Override
    public List<Map<String, Object>> querySci(Map<String, Object> map) {
        return this.madd.querySci(map);
    }

    @Override
    public int insertSci(Map<String, Object> map) {
        return this.madd.insertSci(map);
    }

    @Override
    public List<Map<String, Object>> queryClinicalreward(Map<String, Object> map) {
        return this.madd.queryClinicalreward(map);
    }

    @Override
    public int insertClinicalreward(Map<String, Object> map) {
        return this.madd.insertClinicalreward(map);
    }

    @Override
    public List<Map<String, Object>> queryAcadereward(Map<String, Object> map) {
        return this.madd.queryAcadereward(map);
    }

    @Override
    public int insertAcadereward(Map<String, Object> map) {
        return this.madd.insertAcadereward(map);
    }

    @Override
    public Map<String,Object> insertJcsbxx(Map<String, Object> perMap,
                               List<Map<String, Object>> tssbList,
                               List<Map<String, Object>> stuList,
                               List<Map<String, Object>> workList,
                               List<Map<String, Object>> steaList,
                               List<Map<String, Object>> zjxsList,
                               List<Map<String, Object>> jcbjList,
                               List<Map<String, Object>> gjkcjsList,
                               List<Map<String, Object>> gjghjcList,
                               List<Map<String, Object>> jcbxList,
                               List<Map<String, Object>> zjkyList,
                               List<Map<String, Object>> zjkzqkList,
                               Map<String, Object> achievementMap,
                               List<Map<String, Object>> monographList,
                               List<Map<String, Object>> publishList,
                               List<Map<String, Object>> sciList,
                               List<Map<String, Object>> clinicalList,
                               List<Map<String, Object>> acadeList,
                               List<Map<String, Object>> pmphList,
                               Map<String, Object> digitalMap,
                               Map<String, Object> intentionlMap) {
        //1.新增申报表
        this.madd.insertPerson(perMap);
        //查询上面新增的申报表ID
        List<Map<String, Object>> perList = this.madd.queryPerson(perMap);
        Object declaration_id = perList.get(0).get("id");

        //2.职位新增
        if (tssbList != null && !tssbList.isEmpty()) {
            for (Map<String, Object> map : tssbList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertTsxz(map);
            }
        }
        if (perMap.get("type").equals("1")) { //提交
            if (perMap.get("org_id").equals("0")) {
                messageService.sendNewMsgWriterToPublisher(MapUtils.getLong(perMap, "material_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"));
            } else {
                messageService.sendNewMsgWriterToOrg(MapUtils.getLong(perMap, "org_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"));
            }
            //若申报材料提交 则根据填写的专家信息对应更新个人资料，
            //若申报单位在未进行教师认证之前需要更新个人的所属机构（选择人卫出版社不能更新个人所属机构）
            if (perMap.get("org_id").equals("0") || perMap.get("is_teacher").toString().equals("true")) { //表示人卫出版社或者已经进行教师认证  则不更新个人所属机构
                perMap.put("org_id", null);
            }
            if (!perMap.get("idtype").equals("0")) { //证件类型不为身份证
                perMap.put("idcard", "");
            }
            this.madd.updateWriter(perMap);
        }
       /* else { //暂存
            if (tssbList != null && !tssbList.isEmpty()) {
                for (Map<String, Object> map : tssbList) {
                    map.put("declaration_id", declaration_id);
                    this.madd.insertTssbZc(map);
                }
            }
        }*/
        //3.作家学习经历新增
        if (stuList != null && !stuList.isEmpty()) {
            for (Map<String, Object> map : stuList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertStu(map);
            }
        }
        //4.作家工作经历新增
        if (workList != null && !workList.isEmpty()) {
            for (Map<String, Object> map : workList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertWork(map);
            }
        }
        //5.作家教学经历新增
        if (steaList != null && !steaList.isEmpty()) {
            for (Map<String, Object> map : steaList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertStea(map);
            }
        }
        //6.作家兼职学术新增
        if (zjxsList != null && !zjxsList.isEmpty()) {
            for (Map<String, Object> map : zjxsList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertZjxs(map);
            }
        }
        //7.上套教材参编新增
        if (jcbjList != null && !jcbjList.isEmpty()) {
            for (Map<String, Object> map : jcbjList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbj(map);
            }
        }
        //8.精品课程建设新增
        if (gjkcjsList != null && !gjkcjsList.isEmpty()) {
            for (Map<String, Object> map : gjkcjsList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertGjkcjs(map);
            }
        }
        //9.主编国家级规划教材新增
        if (gjghjcList != null && !gjghjcList.isEmpty()) {
            for (Map<String, Object> map : gjghjcList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertGjghjc(map);
            }
        }
        //10.作家教材编写新增
        if (jcbxList != null && !jcbxList.isEmpty()) {
            for (Map<String, Object> map : jcbxList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbx(map);
            }
        }
        //11.作家科研情况新增
        if (zjkyList != null && !zjkyList.isEmpty()) {
            for (Map<String, Object> map : zjkyList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertZjkyqk(map);
            }
        }
        //12.作家扩展项新增
        if (zjkzqkList != null && !zjkzqkList.isEmpty()) {
            for (Map<String, Object> map : zjkzqkList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertZjkzbb(map);
            }
        }
        //13.个人成就新增
        if (achievementMap != null && !achievementMap.isEmpty()) {
            achievementMap.put("declaration_id", declaration_id);
            this.madd.insertAchievement(achievementMap);
        }
        //14.主编学术专著新增
        if (monographList != null && !monographList.isEmpty()) {
            for (Map<String, Object> map : monographList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertMonograph(map);
            }
        }
        //15.出版行业获奖情况新增
        if (publishList != null && !publishList.isEmpty()) {
            for (Map<String, Object> map : publishList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertPublish(map);
            }
        }
        //16.SCI论文投稿及影响因子新增
        if (sciList != null && !sciList.isEmpty()) {
            for (Map<String, Object> map : sciList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertSci(map);
            }
        }
        //17.临床医学获奖情况新增
        if (clinicalList != null && !clinicalList.isEmpty()) {
            for (Map<String, Object> map : clinicalList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertClinicalreward(map);
            }
        }
        //18.作家学术荣誉新增
        if (acadeList != null && !acadeList.isEmpty()) {
            for (Map<String, Object> map : acadeList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertAcadereward(map);
            }
        }
        //19.人卫社教材编写新增
        if (pmphList != null && !pmphList.isEmpty()) {
            for (Map<String, Object> map : pmphList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertRwsjc(map);
            }
        }
        //20.参加人卫慕课、数字教材编写情况
        if (digitalMap != null && !digitalMap.isEmpty()) {
            digitalMap.put("declaration_id", declaration_id);
            this.madd.insertMoocdigital(digitalMap);
        }
        //21.编写内容意向表
        if (intentionlMap != null && !intentionlMap.isEmpty()) {
            intentionlMap.put("declaration_id", declaration_id);
            this.madd.insertIntention(intentionlMap);
        }
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("msg","OK");
        returnMap.put("org_name", perList.get(0).get("dwmc"));
        returnMap.put("declaration_id",declaration_id);
        return returnMap;
    }

    @Override
    public Map<String,Object> updateJcsbxx(Map<String, Object> perMap,
                               List<Map<String, Object>> tssbList,
                               List<Map<String, Object>> stuList,
                               List<Map<String, Object>> workList, String declaration_id,
                               List<Map<String, Object>> steaList,
                               List<Map<String, Object>> zjxsList,
                               List<Map<String, Object>> jcbjList,
                               List<Map<String, Object>> gjkcjsList,
                               List<Map<String, Object>> gjghjcList,
                               List<Map<String, Object>> jcbxList,
                               List<Map<String, Object>> zjkyList,
                               List<Map<String, Object>> zjkzqkList,
                               Map<String, Object> achievementMap,
                               List<Map<String, Object>> monographList,
                               List<Map<String, Object>> publishList,
                               List<Map<String, Object>> sciList,
                               List<Map<String, Object>> clinicalList,
                               List<Map<String, Object>> acadeList,
                               List<Map<String, Object>> pmphList,
                               Map<String, Object> digitalMap,
                               Map<String, Object> intentionlMap) {
        //修改申报信息
        perMap.put("declaration_id", declaration_id);
        this.madd.updatePerson(perMap);
        if (perMap.get("type").equals("1")) { //提交
            if (perMap.get("org_id").equals("0")) {
                messageService.sendNewMsgWriterToPublisher(MapUtils.getLong(perMap, "material_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"));
            } else {
                messageService.sendNewMsgWriterToOrg(MapUtils.getLong(perMap, "org_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"));
            }
            //若申报材料提交 则根据填写的专家信息对应更新个人资料，
            //若申报单位在未进行教师认证之前需要更新个人的所属机构（选择人卫出版社不能更新个人所属机构）
            if (perMap.get("org_id").equals("0")||perMap.get("is_teacher").toString().equals("true")) { //表示人卫出版社或者已经进行教师认证  则不更新个人所属机构
                perMap.put("org_id", null);
            }
            if(!perMap.get("idtype").equals("0")){ //证件类型不为身份证
                perMap.put("idcard",null);
            }
            this.madd.updateWriter(perMap);

        }
        //删除暂存内容
        Map<String, Object> glMap = new HashMap<String, Object>();
        glMap.put("declaration_id", declaration_id);
        this.madd.DelTsxz(glMap); //图书申报
        this.madd.DelTssbZc(glMap); //图书申报职位暂存
        this.madd.DelStu(glMap);    //学习经历
        this.madd.DelWork(glMap);   //工作经历
        this.madd.DelStea(glMap);   //教学经历
        this.madd.DelZjxs(glMap);   //作家学术
        this.madd.DelJcbj(glMap);  //上版教材编辑
        this.madd.DelGjghjc(glMap); //作家主编国家级规划教材情况
        this.madd.DelJcbx(glMap);  //教材编写情况
        this.madd.DelGjkcjs(glMap); //精品课程建设
        this.madd.DelZjkyqk(glMap); //作家科研情况表
        this.madd.DelAcadereward(glMap); //学术荣誉授予情况
        this.madd.DelMonograph(glMap); ////主编学术专著情况
        this.madd.DelClinicalreward(glMap); //临床医学获奖情况
        this.madd.DelPublish(glMap);  //出版行业获奖情况
        this.madd.DelSci(glMap);   //SCI论文投稿及影响因子
        this.madd.delZjkzbb(glMap);   //扩展信息
        this.madd.DelAchievement(glMap);   //个人成就
        this.madd.DelRwsjc(glMap);  //人卫社教材
        //2.职位新增
        if (tssbList != null && !tssbList.isEmpty()) {
            for (Map<String, Object> map : tssbList) {
                map.put("declaration_id", declaration_id);
           //     if (perMap.get("online_progress").equals("0")) { //暂存
          //         this.madd.insertTssbZc(map);
          //      } else { //提交，退回
                    this.madd.insertTsxz(map);
          //      }
            }
        }
        //3.作家学习经历新增
        if (stuList != null && !stuList.isEmpty()) {
            for (Map<String, Object> map : stuList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertStu(map);
            }
        }
        //4.作家工作经历新增
        if (workList != null && !workList.isEmpty()) {
            for (Map<String, Object> map : workList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertWork(map);
            }
        }
        //5.作家教学经历新增
        if (steaList != null && !steaList.isEmpty()) {
            for (Map<String, Object> map : steaList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertStea(map);
            }
        }
        //6.作家兼职学术新增
        if (zjxsList != null && !zjxsList.isEmpty()) {
            for (Map<String, Object> map : zjxsList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertZjxs(map);
            }
        }
        //7.上套教材参编新增
        if (jcbjList != null && !jcbjList.isEmpty()) {
            for (Map<String, Object> map : jcbjList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbj(map);
            }
        }
        //8.精品课程建设新增
        if (gjkcjsList != null && !gjkcjsList.isEmpty()) {
            for (Map<String, Object> map : gjkcjsList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertGjkcjs(map);
            }
        }
        //9.主编国家级规划教材新增
        if (gjghjcList != null && !gjghjcList.isEmpty()) {
            for (Map<String, Object> map : gjghjcList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertGjghjc(map);
            }
        }
        //10.其他社教材编写新增
        if (jcbxList != null && !jcbxList.isEmpty()) {
            for (Map<String, Object> map : jcbxList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbx(map);
            }
        }
        //11.作家科研情况新增
        if (zjkyList != null && !zjkyList.isEmpty()) {
            for (Map<String, Object> map : zjkyList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertZjkyqk(map);
            }
        }
        //12.作家扩展项新增
        if (zjkzqkList != null && !zjkzqkList.isEmpty()) {
            for (Map<String, Object> map : zjkzqkList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertZjkzbb(map);
            }
        }
        //13.个人成就新增
        if (achievementMap != null && !achievementMap.isEmpty()) {
            achievementMap.put("declaration_id", declaration_id);
            this.madd.insertAchievement(achievementMap);
        }
        //14.主编学术专著新增
        if (monographList != null && !monographList.isEmpty()) {
            for (Map<String, Object> map : monographList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertMonograph(map);
            }
        }
        //15.出版行业获奖情况新增
        if (publishList != null && !publishList.isEmpty()) {
            for (Map<String, Object> map : publishList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertPublish(map);
            }
        }
        //16.SCI论文投稿及影响因子新增
        if (sciList != null && !sciList.isEmpty()) {
            for (Map<String, Object> map : sciList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertSci(map);
            }
        }
        //17.临床医学获奖情况新增
        if (clinicalList != null && !clinicalList.isEmpty()) {
            for (Map<String, Object> map : clinicalList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertClinicalreward(map);
            }
        }
        //18.作家学术荣誉新增
        if (acadeList != null && !acadeList.isEmpty()) {
            for (Map<String, Object> map : acadeList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertAcadereward(map);
            }
        }
        //19.人卫社教材编写新增
        if (pmphList != null && !pmphList.isEmpty()) {
            for (Map<String, Object> map : pmphList) {
                map.put("declaration_id", declaration_id);
                this.madd.insertRwsjc(map);
            }
        }
        //20.参加人卫慕课、数字教材编写情况
        if (digitalMap != null && !digitalMap.isEmpty()) {
            digitalMap.put("declaration_id", declaration_id);
            this.madd.updateMoocdigital(digitalMap);
        }
        //21.编写内容意向表
        if (intentionlMap != null && !intentionlMap.isEmpty()) {
            intentionlMap.put("declaration_id", declaration_id);
            this.madd.updateIntention(intentionlMap);
        }
        List<Map<String, Object>> perList = this.madd.queryPerson(perMap);
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("msg","OK");
        returnMap.put("org_name", perList.get(0).get("dwmc"));
        returnMap.put("declaration_id",declaration_id);
        return returnMap;
    }

    @Override
    public Map<String, Object> queryMoocdigital(Map<String, Object> map) {
        return this.madd.queryMoocdigital(map);
    }

    @Override
    public Map<String, Object> queryIntention(Map<String, Object> map) {
        return this.madd.queryIntention(map);
    }

	@Override
	public Map<String, Object> queryDeclarationByUserIdAndMaterialIdOrDeclarationId(String user_id, String material_id, String declaration_id) {
		Map<String, Object> map = madd.queryDeclarationByUserIdAndMaterialIdOrDeclarationId(user_id,material_id,declaration_id);
		return map;
	}
	@Override
	public Map<String, Object> queryUserInfo(String user_id) {
		Map<String, Object> map = this.madd.queryUserInfo(user_id);
		return map;
	}


}
