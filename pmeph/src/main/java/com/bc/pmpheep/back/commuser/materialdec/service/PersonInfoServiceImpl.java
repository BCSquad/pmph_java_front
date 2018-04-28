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
    public int insertPerStu(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerWork(Map<String, Object> map) {
        return this.peradd.queryPerWork(map);
    }

    @Override
    public int insertPerWork(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerZjxs(Map<String, Object> map) {
        return this.peradd.queryPerZjxs(map);
    }

    @Override
    public int insertPerZjxs(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerStea(Map<String, Object> map) {
        return this.peradd.queryPerStea(map);
    }

    @Override
    public int insertPerStea(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerJcbj(Map<String, Object> map) {
        return this.peradd.queryPerJcbj(map);
    }

    @Override
    public int insertPerJcbj(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerGjkcjs(Map<String, Object> map) {
        return this.peradd.queryPerGjkcjs(map);
    }

    @Override
    public int insertPerGjkcjs(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerGjghjc(Map<String, Object> map) {
        return this.peradd.queryPerGjghjc(map);
    }

    @Override
    public int insertPerGjghjc(Map<String, Object> map) {
        return 0;
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
    public int insertPerJcbx(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerZjkyqk(Map<String, Object> map) {
        return this.peradd.queryPerZjkyqk(map);
    }

    @Override
    public int insertPerZjkyqk(Map<String, Object> map) {
        return 0;
    }

    @Override
    public Map<String, Object> queryAchievement(Map<String, Object> map) {
        return this.peradd.queryPerAchievement(map);
    }

    @Override
    public int insertAchievement(Map<String, Object> map) {
        return 0;
    }

    @Override
    public int updateAchievement(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerMonograph(Map<String, Object> map) {
        return this.peradd.queryPerMonograph(map);
    }

    @Override
    public int insertPerMonograph(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerPublish(Map<String, Object> map) {
        return this.peradd.queryPerPublish(map);
    }

    @Override
    public int insertPerPublish(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerSci(Map<String, Object> map) {
        return this.peradd.queryPerSci(map);
    }

    @Override
    public int insertPerSci(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerClinicalreward(Map<String, Object> map) {
        return this.peradd.queryPerClinicalreward(map);
    }

    @Override
    public int insertPerClinicalreward(Map<String, Object> map) {
        return 0;
    }

    @Override
    public List<Map<String, Object>> queryPerAcadereward(Map<String, Object> map) {
        return this.peradd.queryPerAcadereward(map);
    }

    @Override
    public int insertPerAcadereward(Map<String, Object> map) {
        return 0;
    }

    @Override
    public Map<String, Object> queryPerMoocdigital(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<String, Object> queryPerIntention(Map<String, Object> map) {
        return null;
    }
}
