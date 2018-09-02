package com.bc.pmpheep.back.commuser.materialdec.service;

import com.bc.pmpheep.back.commuser.materialdec.dao.PersonInfoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("com.bc.pmpheep.back.commuser.materialdec.service.PersonInfoServiceImpl")
public class PersonInfoServiceImpl implements PersonInfoService {

    @Autowired
    private PersonInfoDao peradd;

    @Override
    public List<Map<String, Object>> queryPerStu(Map<String, Object> map) {
        return this.peradd.queryPerStu(map);
    }


    @Override
    public List<Map<String, Object>> queryPerWork(Map<String, Object> map) {
        return this.peradd.queryPerWork(map);
    }


    @Override
    public List<Map<String, Object>> queryPerZjxs(Map<String, Object> map) {
        return this.peradd.queryPerZjxs(map);
    }


    @Override
    public List<Map<String, Object>> queryPerStea(Map<String, Object> map) {
        return this.peradd.queryPerStea(map);
    }


    @Override
    public List<Map<String, Object>> queryPerJcbj(Map<String, Object> map) {
        return this.peradd.queryPerJcbj(map);
    }


    @Override
    public List<Map<String, Object>> queryPerGjkcjs(Map<String, Object> map) {
        return this.peradd.queryPerGjkcjs(map);
    }


    @Override
    public List<Map<String, Object>> queryPerGjghjc(Map<String, Object> map) {
        return this.peradd.queryPerGjghjc(map);
    }


    @Override
    public List<Map<String, Object>> rwsjcPerList(Map<String, Object> map) {
        return this.peradd.queryPerRwsjc(map);
    }

    @Override
    public List<Map<String, Object>> queryqtPerJcbx(Map<String, Object> map) {
        return this.peradd.queryPerJcbx(map);
    }


    @Override
    public List<Map<String, Object>> queryPerZjkyqk(Map<String, Object> map) {
        return this.peradd.queryPerZjkyqk(map);
    }


    @Override
    public Map<String, Object> queryAchievement(Map<String, Object> map) {
        return this.peradd.queryPerAchievement(map);
    }



    @Override
    public List<Map<String, Object>> queryPerMonograph(Map<String, Object> map) {
        return this.peradd.queryPerMonograph(map);
    }


    @Override
    public List<Map<String, Object>> queryPerPublish(Map<String, Object> map) {
        return this.peradd.queryPerPublish(map);
    }


    @Override
    public List<Map<String, Object>> queryPerSci(Map<String, Object> map) {
        return this.peradd.queryPerSci(map);
    }


    @Override
    public List<Map<String, Object>> queryPerClinicalreward(Map<String, Object> map) {
        return this.peradd.queryPerClinicalreward(map);
    }


    @Override
    public List<Map<String, Object>> queryPerAcadereward(Map<String, Object> map) {
        return this.peradd.queryPerAcadereward(map);
    }


    @Override
    public Map<String, Object> queryPerMoocdigital(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> queryPerIntention(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryPerEditor(Map<String, Object> map) {
        return this.peradd.queryPerEditor(map);
    }

    @Override
    public int insertJcsbxx(Map<String, Object> perMap,
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
                            Map<String, Object> intentionlMap,
                            List<Map<String,Object>> editorList) {
        //获取userid
        String user_id = perMap.get("user_id").toString();

        //3.作家学习经历新增
        if (stuList != null && !stuList.isEmpty()) {
            for (Map<String, Object> map : stuList) {
                map.put("user_id", user_id);
                this.peradd.insertPerStu(map);
            }
        }
        //4.作家工作经历新增
        if (workList != null && !workList.isEmpty()) {
            for (Map<String, Object> map : workList) {
                map.put("user_id", user_id);
                this.peradd.insertPerWork(map);
            }
        }
        //5.作家教学经历新增
        if (steaList != null && !steaList.isEmpty()) {
            for (Map<String, Object> map : steaList) {
                map.put("user_id", user_id);
                this.peradd.insertPerStea(map);
            }
        }
        //6.作家兼职学术新增
        if (zjxsList != null && !zjxsList.isEmpty()) {
            for (Map<String, Object> map : zjxsList) {
                map.put("user_id", user_id);
                this.peradd.insertPerZjxs(map);
            }
        }
        //7.上套教材参编新增
        if (jcbjList != null && !jcbjList.isEmpty()) {
            for (Map<String, Object> map : jcbjList) {
                map.put("user_id", user_id);
                this.peradd.insertPerJcbj(map);
            }
        }
        //8.精品课程建设新增
        if (gjkcjsList != null && !gjkcjsList.isEmpty()) {
            for (Map<String, Object> map : gjkcjsList) {
                map.put("user_id", user_id);
                this.peradd.insertPerGjkcjs(map);
            }
        }
        //9.主编国家级规划教材新增
        if (gjghjcList != null && !gjghjcList.isEmpty()) {
            for (Map<String, Object> map : gjghjcList) {
                map.put("user_id", user_id);
                this.peradd.insertPerGjghjc(map);
            }
        }
        //10.其他社教材编写新增
        if (jcbxList != null && !jcbxList.isEmpty()) {
            for (Map<String, Object> map : jcbxList) {
                map.put("user_id", user_id);
                this.peradd.insertPerJcbx(map);
            }
        }
        //11.作家科研情况新增
        if (zjkyList != null && !zjkyList.isEmpty()) {
            for (Map<String, Object> map : zjkyList) {
                map.put("user_id", user_id);
                this.peradd.insertPerZjkyqk(map);
            }
        }
        //12.作家扩展项新增
        if (zjkzqkList != null && !zjkzqkList.isEmpty()) {
            for (Map<String, Object> map : zjkzqkList) {
                map.put("user_id", user_id);
             //   this.peradd.insertPerZjkzbb(map);
            }
        }
        //13.个人成就新增
        if (achievementMap != null && !achievementMap.isEmpty()) {
            achievementMap.put("user_id", user_id);
            this.peradd.insertPerAchievement(achievementMap);
        }
        //14.主编学术专著新增
        if (monographList != null && !monographList.isEmpty()) {
            for (Map<String, Object> map : monographList) {
                map.put("user_id", user_id);
                this.peradd.insertPerMonograph(map);
            }
        }
        //15.出版行业获奖情况新增
        if (publishList != null && !publishList.isEmpty()) {
            for (Map<String, Object> map : publishList) {
                map.put("user_id", user_id);
                this.peradd.insertPerPublish(map);
            }
        }
        //16.SCI论文投稿及影响因子新增
        if (sciList != null && !sciList.isEmpty()) {
            for (Map<String, Object> map : sciList) {
                map.put("user_id", user_id);
                this.peradd.insertPerSci(map);
            }
        }
        //17.临床医学获奖情况新增
        if (clinicalList != null && !clinicalList.isEmpty()) {
            for (Map<String, Object> map : clinicalList) {
                map.put("user_id", user_id);
                this.peradd.insertPerClinicalreward(map);
            }
        }
        //18.作家学术荣誉新增
        if (acadeList != null && !acadeList.isEmpty()) {
            for (Map<String, Object> map : acadeList) {
                map.put("user_id", user_id);
                this.peradd.insertPerAcadereward(map);
            }
        }
        //19.人卫社教材编写新增
        if (pmphList != null && !pmphList.isEmpty()) {
            for (Map<String, Object> map : pmphList) {
                map.put("user_id", user_id);
                this.peradd.insertPerRwsjc(map);
            }
        }
        //19.主编或参编图书情况
        if (editorList != null && !editorList.isEmpty()) {
            for (Map<String, Object> map : editorList) {
                map.put("user_id", user_id);
                this.peradd.insertPerEditor(map);
            }
        }
        return 1;
    }
}
