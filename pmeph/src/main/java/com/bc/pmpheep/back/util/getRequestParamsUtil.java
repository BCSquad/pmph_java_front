package com.bc.pmpheep.back.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by cyx  on 2017/11/26
 */
public abstract class getRequestParamsUtil {

    /**
     * 对请求数据进行过滤处理
     * @param request
     * @return
     */
    public Map<String,Object> getParamMapFromResult(HttpServletRequest request){
        Map<String,Object> result = new HashMap<String,Object>();
        Map<String,String[]> params  = request.getParameterMap();
        Iterator<Map.Entry<String, String[]>> iter = params.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String[]> entry = iter.next();
            if(entry.getValue().length>0){
                String value = "".equals(entry.getValue()[0])?null:(entry.getValue()[0]=="undefined"?null:entry.getValue()[0]);
                result.put(entry.getKey(), value);
            }

        }
      /*  String page_size = request.getParameter("pageSize")==null?"1":(request.getParameter("pageSize")=="undefined"?"1":request.getParameter("pageSize"));
        String cur_Page = request.getParameter("curPage")==null?"1":(request.getParameter("curPage")=="undefined"?"1":request.getParameter("curPage"));
        int pageSize = Integer.parseInt(page_size);
        int curPage = Integer.parseInt(cur_Page);

        result.put("startrows", ((curPage-1)*pageSize+1));
        result.put("endrows", curPage*pageSize);*/
        return result;
    }
}
