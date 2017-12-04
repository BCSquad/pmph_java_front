package com.bc.pmpheep.back.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * 
 * <pre>
 * 功能描述：字符串工具类
 * 使用示范：
 * 
 * 
 * @author (作者) nyz
 * 
 * @since (该版本支持的JDK版本) ：JDK 1.6或以上
 * @version (版本) 1.0
 * @date (开发日期) 2017-10-19
 * @modify (最后修改时间) 
 * @修改人 ：nyz 
 * @审核人 ：
 * </pre>
 */
public final class StringUtil {

    /**
     * 判断字符串是否为空
     */
    public static boolean isEmpty(String str) {
        if (str != null) {
            str = str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    /**
     * 判断字符串是否非空
     */
    public static boolean notEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为数字
     */
    public static boolean isNumeric(String str) {
        return NumberUtils.isDigits(str);
    }

    /**
     * 正向查找指定字符串
     */
    public static int indexOf(String str, String searchStr, boolean ignoreCase) {
        if (ignoreCase) {
            return StringUtils.indexOfIgnoreCase(str, searchStr);
        } else {
            return str.indexOf(searchStr);
        }
    }

    /**
     * 反向查找指定字符串
     */
    public static int lastIndexOf(String str, String searchStr, boolean ignoreCase) {
        if (ignoreCase) {
            return StringUtils.lastIndexOfIgnoreCase(str, searchStr);
        } else {
            return str.lastIndexOf(searchStr);
        }
    }

    /**
     * 用默认的分隔符(,)将字符串转换为字符串数组
     * 
     * @param str 字符串
     * @return
     */
    public static String[] str2StrArray(String str) {
        return str2StrArray(str, ",\\s*");
    }

    /**
     * 字符串转换为字符串数组
     * 
     * @param str 字符串
     * @param splitRegex 分隔符
     * @return
     */
    public static String[] str2StrArray(String str, String splitRegex) {
        if (isEmpty(str)) {
            return null;
        }
        return str.split(splitRegex);
    }

    /**
     * 将以逗号分隔的字符串转换成字符串数组
     * 
     * @param valStr
     * @return String[]
     */
    public static String[] StrList(String valStr) {
        int i = 0;
        String TempStr = valStr;
        String[] returnStr = new String[valStr.length() + 1 - TempStr.replace(",", "").length()];
        valStr = valStr + ",";
        while (valStr.indexOf(',') > 0) {
            returnStr[i] = valStr.substring(0, valStr.indexOf(','));
            valStr = valStr.substring(valStr.indexOf(',') + 1, valStr.length());

            i++;
        }
        return returnStr;
    }

    /**
     * 
     * <pre>
     * 功能描述：获取字符串长度
     * 使用示范：
     *
     * @param str
     * @return
     * </pre>
     */
    public static int strLength(String str) {
        if (isEmpty(str)) {
            return 0;
        }
        return str.length();
    }

    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 
     * <pre>
     * 功能描述：得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     * 使用示范：
     *
     * @param s 需要得到长度的字符串
     * @return int 得到的字符串长度
     * </pre>
     */
    public static int length(String s) {
        if (isEmpty(s)) {
            return 0;
        }
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 
     * <pre>
     * 功能描述：得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为1,英文字符长度为0.5
     * 使用示范：
     *
     * @param s 需要得到长度的字符串
     * @return 得到的字符串长度
     * </pre>
     */
    public static double getLength(String s) {
        double valueLength = 0;
        String chinese = "[\u4e00-\u9fa5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < s.length(); i++) {
            // 获取一个字符
            String temp = s.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为1
                valueLength += 1;
            } else {
                // 其他字符长度为0.5
                valueLength += 0.5;
            }
        }
        // 进位取整
        return Math.ceil(valueLength);
    }

    public static void main(String[] args) {
        System.out.println(length("fdsa魂牵梦萦土城"));
    }
}
