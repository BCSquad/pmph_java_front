package com.bc.pmpheep.general.controller;

import com.bc.pmpheep.general.service.ExcelDownloadService;
import jxl.Workbook;
import jxl.format.*;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.collections4.MapUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lihuan on 2017/12/13.
 */
@Controller
@RequestMapping("excel")
public class ExcelController {

    @RequestMapping("download")
    public void downloadExcelData(String service, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map param = request.getParameterMap();
        ApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext());

        ExcelDownloadService excleService = (ExcelDownloadService) applicationContext.getBean(service);

        WritableWorkbook book = Workbook.createWorkbook(response.getOutputStream());

        WritableSheet sheet = book.createSheet(" 第一页 ", 0);

        sheet.mergeCells(0, 0, excleService.getColTitle().length, 0);


        //设置标题样式
        WritableFont wf_title = new WritableFont(WritableFont.ARIAL, 20,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_title = new WritableCellFormat(wf_title);
        wcf_title.setAlignment(Alignment.CENTRE);


        //设置表头样式
        WritableFont wf_col_title = new WritableFont(WritableFont.ARIAL, 14,
                WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_col_title = new WritableCellFormat(wf_col_title);
        wcf_col_title.setAlignment(Alignment.CENTRE);
        if (excleService.getTitleColour() == null) {
            wcf_col_title.setBackground(Colour.ICE_BLUE);
        } else {
            wcf_col_title.setBackground(excleService.getTitleColour());
        }

        wcf_col_title.setBorder(Border.ALL, BorderLineStyle.THIN);


        //设置内容样式
        WritableFont wf_content = new WritableFont(WritableFont.ARIAL, 10,
                WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE,
                Colour.BLACK);

        WritableCellFormat wcf_content = new WritableCellFormat(wf_content);
        wcf_content.setAlignment(Alignment.CENTRE);
        wcf_content.setBorder(Border.ALL, BorderLineStyle.THIN);


        String excelTitle = excleService.getTitle(arrayMapToStringMap(param));
        //设置标题
        jxl.write.Label title = new jxl.write.Label(0, 0, excelTitle, wcf_title);
        sheet.addCell(title);

        /**
         * 设置表头
         */
        sheet.setColumnView(0, 10);
        sheet.addCell(new jxl.write.Label(0, 1, "序号", wcf_col_title));
        for (int i = 1; i <= excleService.getColTitle().length; i++) {
            sheet.setColumnView(i, 30);
            jxl.write.Label col_title = new jxl.write.Label(i, 1, excleService.getColTitle()[i - 1][0], wcf_col_title);
            sheet.addCell(col_title);
        }

        //填充网格数据


        List<Map<String, Object>> data = excleService.getData(arrayMapToStringMap(param));

        for (int i = 0; i < data.size(); i++) {
            Map<String, Object> rowData = data.get(i);

            jxl.write.Label col_content = new jxl.write.Label(0, i + 2, (i + 1) + "", wcf_content);
            sheet.addCell(col_content);

            for (int j = 0; j < excleService.getColTitle().length; j++) {
                String[] colkv = excleService.getColTitle()[j];

                col_content = new jxl.write.Label(j + 1, i + 2, MapUtils.getString(rowData, colkv[1], ""), wcf_content);
                sheet.addCell(col_content);
            }
        }

        response.setHeader("Content-Disposition", "attachment;filename=\"" + new String((excelTitle + ".xls").getBytes("gbk"), "ISO-8859-1") + "\"");
        //response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(excleService.getTitle() + ".xls", "UTF-8"));
        book.write();
        book.close();
    }

    private static Map<String, Object> arrayMapToStringMap(Map<String, String[]> map) {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String value = null;
            if (entry.getValue() instanceof String[]) {
                String[] strs = (String[]) entry.getValue();
                try {
                    value = URLDecoder.decode(strs[0], "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    value = URLDecoder.decode(entry.getValue().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            returnMap.put(entry.getKey(), value);
        }
        return returnMap;
    }

}
