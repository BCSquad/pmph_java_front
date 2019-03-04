package com.bc.pmpheep.general.service;

import jxl.format.Colour;

import java.util.List;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/13.
 */
public interface ExcelDownloadService {
    /**
     * 导出文件标题
     *
     * @return
     */
    public String getTitle(Map<String, Object> param);


    /**
     * 导出列头名和数据字段映射关系如：{{"线索来源1","xsly1"},{"线索来源2","xsly2"}}
     *
     * @return
     */
    public String[][] getColTitle(Map<String, Object> param);


    /**
     * 表头颜色,默认：Colour.ICE_BLUE
     *
     * @return
     */
    public Colour getTitleColour();


    /**
     * 获取导出数据
     *
     * @return
     */
    public List<Map<String, Object>> getData(Map<String, Object> param) throws Exception;
}
