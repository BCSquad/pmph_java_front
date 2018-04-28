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
                            Map<String, Object> intentionlMap) {
        return 0;
    }
}
