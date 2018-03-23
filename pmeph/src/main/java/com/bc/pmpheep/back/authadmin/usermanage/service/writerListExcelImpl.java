package com.bc.pmpheep.back.authadmin.usermanage.service;

import com.bc.pmpheep.back.authadmin.usermanage.bean.OrgUser;
import com.bc.pmpheep.back.authadmin.usermanage.bean.WriterUser;
import com.bc.pmpheep.back.authadmin.usermanage.dao.WriterUserDao;
import com.bc.pmpheep.back.plugin.PageParameter;
import com.bc.pmpheep.back.util.Const;
import com.bc.pmpheep.general.service.ExcelDownloadService;
import jxl.format.Colour;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cyx  on 2018/3/19
 */
@Service("writerListExcelImpl")
public class writerListExcelImpl implements ExcelDownloadService {

    @Autowired
    private WriterUserDao writerUserDao;
    @Override
    public String getTitle(Map<String, Object> param) {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        Map<String, Object> userInfo= (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_ORGUSER);
        String exportName = MapUtils.getString(userInfo, "org_name");
        return exportName+"作家用户名单";
    }

    @Override
    public String[][] getColTitle() {
        return new String[][]{{"用户编码","username"},{"昵称","nickname"},{"真实姓名","realname"},{"职务","position"},{"职称","title"},{"手机","handphone"}};
    }

    @Override
    public Colour getTitleColour() {
        return null;
    }

    @Override
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception {

        PageParameter<WriterUser> pageParameter = new PageParameter<>(null, null);
        OrgUser orgUser = new OrgUser();
        WriterUser writerUser = new WriterUser();
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        List<Map<String, Object>>  returnData = new ArrayList<Map<String, Object>>();
        Map<String, Object> userInfo= (Map<String, Object>) session.getAttribute(Const.SESSION_USER_CONST_ORGUSER);
        orgUser.setOrgId(Long.parseLong(userInfo.get("org_id").toString()));
        writerUser.setOrgId(orgUser.getOrgId());
         String username = request.getParameter("search");
        String name="";
        if (username != null) {
            name = java.net.URLDecoder.decode(username, "UTF-8");
        }
        writerUser.setName(name);
        pageParameter.setParameter(writerUser);
        pageParameter.setStart(null);
        List<WriterUser>  exportWriterUser = writerUserDao.getOrg(pageParameter);
        for(WriterUser writer:exportWriterUser){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("username",writer.getUsername());
            map.put("nickname",writer.getNickname());
            map.put("realname",writer.getRealname());
            map.put("position",writer.getPosition());
            map.put("title",writer.getTitle());
            map.put("handphone",writer.getHandphone());
            returnData.add(map);
        }
        return returnData;
    }
}
