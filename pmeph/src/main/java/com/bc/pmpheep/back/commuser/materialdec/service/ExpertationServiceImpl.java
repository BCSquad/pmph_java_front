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

    @Override
    public List<Map<String, Object>> queryPerson(Map<String, Object> map) {
        return this.exdao.queryPerson(map);
    }

    @Override
    public Map<String,Object> insertJcsbxx(Map<String, Object> perMap,
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
                               Map<String, Object> intentionlMap,
                                           List<Map<String, Object>> subjectList,
                                           List<Map<String, Object>> contentList) {
        //1.新增申报表
        this.exdao.insertPerson(perMap);
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

        //2.
        if (perMap.get("type").equals("1")) { //提交
           /* if (perMap.get("org_id").equals("0")) {
                messageService.sendNewMsgWriterToPublisher(MapUtils.getLong(perMap, "material_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"),user_id);
            } else {
                messageService.sendNewMsgWriterToOrg(MapUtils.getLong(perMap, "org_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"),user_id);
            }*/
            //若申报材料提交 则根据填写的专家信息对应更新个人资料，
            //若申报单位在未进行教师认证之前需要更新个人的所属机构（选择人卫出版社不能更新个人所属机构）
           /* if (perMap.get("org_id").equals("0") || perMap.get("is_teacher").toString().equals("true")) { //表示人卫出版社或者已经进行教师认证  则不更新个人所属机构
                perMap.put("org_id", null);
            }
            if (!perMap.get("idtype").equals("0")) { //证件类型不为身份证
                perMap.put("idcard", "");
            }*/
            this.madd.updateWriter(perMap);
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
                this.madd.insertStu(map);
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
                this.madd.insertWork(map);
            }
        }
        //5.作家教学经历新增
        if (steaList != null && !steaList.isEmpty()) {
            for (Map<String, Object> map : steaList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerStea(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerStea(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertStea(map);
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
                this.madd.insertZjxs(map);
            }
        }
        //7.上套教材参编新增
        if (jcbjList != null && !jcbjList.isEmpty()) {
            for (Map<String, Object> map : jcbjList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerJcbj(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerJcbj(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbj(map);
            }
        }
        //8.精品课程建设新增
        if (gjkcjsList != null && !gjkcjsList.isEmpty()) {
            for (Map<String, Object> map : gjkcjsList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerGjkcjs(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerGjkcjs(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertGjkcjs(map);
            }
        }
        //9.主编国家级规划教材新增
        if (gjghjcList != null && !gjghjcList.isEmpty()) {
            for (Map<String, Object> map : gjghjcList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerGjghjc(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerGjghjc(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertGjghjc(map);
            }
        }
        //10.作家教材编写新增
        if (jcbxList != null && !jcbxList.isEmpty()) {
            for (Map<String, Object> map : jcbxList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerJcbx(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerJcbx(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbx(map);
            }
        }
        //11.作家科研情况新增
        if (zjkyList != null && !zjkyList.isEmpty()) {
            for (Map<String, Object> map : zjkyList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerZjkyqk(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerZjkyqk(map);
                    }
                }
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
            String per_id = utool.getUUID();
            if(!achievementMap.get("grcj_id").equals("")){
                this.peradd.updatePerAchievement(achievementMap);
            }else{
                achievementMap.put("user_id", user_id);
                achievementMap.put("per_id", per_id);
                this.peradd.insertPerAchievement(achievementMap);
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
                this.madd.insertMonograph(map);
            }
        }
        //15.出版行业获奖情况新增
        if (publishList != null && !publishList.isEmpty()) {
            for (Map<String, Object> map : publishList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerPublish(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerPublish(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertPublish(map);
            }
        }
        //16.SCI论文投稿及影响因子新增
        if (sciList != null && !sciList.isEmpty()) {
            for (Map<String, Object> map : sciList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerSci(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerSci(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertSci(map);
            }
        }
        //17.临床医学获奖情况新增
        if (clinicalList != null && !clinicalList.isEmpty()) {
            for (Map<String, Object> map : clinicalList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerClinicalreward(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerClinicalreward(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertClinicalreward(map);
            }
        }
        //18.作家学术荣誉新增
        if (acadeList != null && !acadeList.isEmpty()) {
            for (Map<String, Object> map : acadeList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerAcadereward(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerAcadereward(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertAcadereward(map);
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
                               Map<String, Object> intentionlMap,
                                           List<Map<String, Object>> subjectList,
                                           List<Map<String, Object>> contentList) {
        //修改申报信息
        perMap.put("declaration_id", declaration_id);
        this.madd.updatePerson(perMap);
        //获取userid
        String user_id = perMap.get("user_id").toString();
        if (perMap.get("type").equals("1")) { //提交
            /*if (perMap.get("org_id").equals("0")) {
                messageService.sendNewMsgWriterToPublisher(MapUtils.getLong(perMap, "material_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"),user_id);
            } else {
                messageService.sendNewMsgWriterToOrg(MapUtils.getLong(perMap, "org_id"), MapUtils.getString(perMap, "realname"), MapUtils.getString(perMap, "materialName"),user_id);
            }
            //若申报材料提交 则根据填写的专家信息对应更新个人资料，
            //若申报单位在未进行教师认证之前需要更新个人的所属机构（选择人卫出版社不能更新个人所属机构）
            if (perMap.get("org_id").equals("0")||perMap.get("is_teacher").toString().equals("true")) { //表示人卫出版社或者已经进行教师认证  则不更新个人所属机构
                perMap.put("org_id", null);
            }
            if(!perMap.get("idtype").equals("0")){ //证件类型不为身份证
                perMap.put("idcard",null);
            }*/
            this.madd.updateWriter(perMap);

        }
        //删除暂存内容
        Map<String, Object> glMap = new HashMap<String, Object>();
        glMap.put("declaration_id", declaration_id);
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
        this.exdao.delContent(declaration_id);
        this.exdao.delSubject(declaration_id);

        //学科分类
        if (subjectList != null && !subjectList.isEmpty()) {
            for (Map<String, Object> map : subjectList) {
                map.put("expertation_id","declaration_id");
                this.exdao.insertSubject(map);
            }
        }
        //内容分类
        if (contentList != null && !contentList.isEmpty()) {
            for (Map<String, Object> map : contentList) {
                map.put("expertation_id","declaration_id");
                this.exdao.insertContent(map);
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
                this.madd.insertStu(map);
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
                this.madd.insertWork(map);
            }
        }
        //5.作家教学经历新增
        if (steaList != null && !steaList.isEmpty()) {
            for (Map<String, Object> map : steaList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerStea(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerStea(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertStea(map);
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
                this.madd.insertZjxs(map);
            }
        }
        //7.上套教材参编新增
        if (jcbjList != null && !jcbjList.isEmpty()) {
            for (Map<String, Object> map : jcbjList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerJcbj(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerJcbj(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbj(map);
            }
        }
        //8.精品课程建设新增
        if (gjkcjsList != null && !gjkcjsList.isEmpty()) {
            for (Map<String, Object> map : gjkcjsList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerGjkcjs(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerGjkcjs(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertGjkcjs(map);
            }
        }
        //9.主编国家级规划教材新增
        if (gjghjcList != null && !gjghjcList.isEmpty()) {
            for (Map<String, Object> map : gjghjcList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerGjghjc(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerGjghjc(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertGjghjc(map);
            }
        }
        //10.作家教材编写新增
        if (jcbxList != null && !jcbxList.isEmpty()) {
            for (Map<String, Object> map : jcbxList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerJcbx(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerJcbx(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertJcbx(map);
            }
        }
        //11.作家科研情况新增
        if (zjkyList != null && !zjkyList.isEmpty()) {
            for (Map<String, Object> map : zjkyList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerZjkyqk(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerZjkyqk(map);
                    }
                }
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
                this.madd.insertMonograph(map);
            }
        }
        //15.出版行业获奖情况新增
        if (publishList != null && !publishList.isEmpty()) {
            for (Map<String, Object> map : publishList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerPublish(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerPublish(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertPublish(map);
            }
        }
        //16.SCI论文投稿及影响因子新增
        if (sciList != null && !sciList.isEmpty()) {
            for (Map<String, Object> map : sciList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerSci(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerSci(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertSci(map);
            }
        }
        //17.临床医学获奖情况新增
        if (clinicalList != null && !clinicalList.isEmpty()) {
            for (Map<String, Object> map : clinicalList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerClinicalreward(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerClinicalreward(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertClinicalreward(map);
            }
        }
        //18.作家学术荣誉新增
        if (acadeList != null && !acadeList.isEmpty()) {
            for (Map<String, Object> map : acadeList) {
                if(perMap.get("type").equals("1")){ //提交
                    String per_id = utool.getUUID();
                    if(!map.get("per_id").equals("")) {
                        this.peradd.updatePerAcadereward(map);
                    }else {
                        map.put("user_id", user_id);
                        map.put("per_id", per_id);
                        this.peradd.insertPerAcadereward(map);
                    }
                }
                map.put("declaration_id", declaration_id);
                this.madd.insertAcadereward(map);
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
    public List<Map<String, Object>> queryExpertation(String user_id) {
        List<Map<String, Object>> list=exdao.queryExpertation(user_id);
        for (Map<String, Object> map: list) {
            String namePath=map.get("name_path").toString();
            String[] names = namePath.split("_,_");
            map.put("names",names);
        }
        return list;
    }

    @Override
    public Map<String, Object> queryExpertationDetail(String user_id, String expert_type) {
        return exdao.queryExpertationDetail(user_id,expert_type);
    }
}
