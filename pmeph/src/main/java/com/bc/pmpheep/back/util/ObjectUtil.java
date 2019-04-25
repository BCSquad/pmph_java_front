package com.bc.pmpheep.back.util;

/**
 * 
 * <pre>
 * 功能描述：Object工具类
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
public final class ObjectUtil {
    private ObjectUtil() {
    }

    /**
     * 检测对象是否不为空
     * 
     * @param s
     * @return 不为空则返回true，否则返回false
     */
    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 检测对象是否为空
     * 
     * @param s
     * @return 为空则返回true，否则返回false
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    public static boolean isNumber(String str) {
        boolean re = true;
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                re = false;
                break;
            }
        }
        return re;

    }
}
