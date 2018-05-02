package com.bc.pmpheep.utils;

import java.util.UUID;

/**
 * 生成32位唯一主键识别码
 */
public class UUIDTool {
    public UUIDTool() {
    }
    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        /*UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        // 去掉"-"符号
        String temp = str.substring(0, 8) + str.substring(9, 13)
                + str.substring(14, 18) + str.substring(19, 23)
                + str.substring(24);
        return temp;*/

        return UUID.randomUUID().toString().replace("-", "");
    }


    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println("ss[" + i + "]=====" + getUUID());
        }
    }
}
