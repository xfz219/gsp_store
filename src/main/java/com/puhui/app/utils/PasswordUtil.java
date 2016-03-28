/**
 * Copyright(c) 2013-2014 by Puhuifinance Inc.
 * All Rights Reserved
 */
package com.puhui.app.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 有关密码的工具类
 * 
 * @author xiaobowen
 */
public class PasswordUtil {

    /** 密码盐 */
    public static final String SALT = "wAteRmE10n";
    /** 散列的 弹性攻击次数 与配置文件 必须保持一致 */
    public static final int HASHITERATIONS = 5;
    /** 加密方式 */
    public static final String ALGORITHM_NAME = "md5";

    /**
     * 计算密码强度
     * 
     * @param pwd
     * @return
     */
    public static int countPwdRank(String pwd) {
        int rank = 1;
        Pattern p = Pattern.compile(".*\\W.*");
        Matcher m = p.matcher(pwd);
        if (m.matches()) {
            rank++;
        }
        if (pwd.matches(".*[A-Z].*") && diffLetter(pwd) >= 2) {
            rank++;
        }
        return rank;
    }

    /**
     * 计算一个字符串 中有几个不同的字母
     * 
     * @param pwd
     * @return
     */
    public static int diffLetter(String pwd) {
        char[] charArray = pwd.toCharArray();
        char c;
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < charArray.length; i++) {
            c = charArray[i];
            if ('a' <= c && c <= 'z' || 'A' <= c && c <= 'Z') {
                set.add(c);
            }
        }
        return set.size();
    }
}
