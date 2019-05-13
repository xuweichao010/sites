package com.xwc.commons.utils;

import java.util.HashSet;
import java.util.List;

/**
 * 创建人：徐卫超
 * 时间: 2018/5/12
 * 功能： 对String字符串的增强工具类
 * 描述： 借鉴于apche的commons的StringUtils工具类，对这个包下的常用方法进行了抽取和补充
 */

@SuppressWarnings("all")
public class StringUtils {

    /**
     * 判断一个字符不为空
     *
     * @param str 判断源
     * @return 不为空返回 true
     */
    public static boolean isNotBlank(final String str) {
        return !isBlank(str);
    }

    /**
     * 判断一个字符为空
     *
     * @param str 判断源
     * @return 为空返回 true
     */
    public static boolean isBlank(String str) {
        return null == str || str.trim().isEmpty();
    }

    /**
     * 判断所有字符串中一个为空
     *
     * @param strs 判断源列表
     * @return 有一个为空返回 true
     */
    public static boolean isAnyBlank(final String... strs) {
        if (0 == strs.length) {
            return false;
        }
        for (final String str : strs) {
            if (isBlank(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断所有字符串不为空
     *
     * @param strs 判断源列表
     * @return 全部不为空返回ture
     */
    public static boolean isNoneBlank(final String... strs) {
        return !isAnyBlank(strs);
    }

    /**
     * 判断两个字符串是否相等
     *
     * @param src src
     * @param tar tar
     * @return 相等返回true;
     */
    public static boolean equals(String src, String tar) {
        if (null == src || null == tar) {
            return src == tar;
        } else {
            return src.equals(tar);
        }
    }

    /**
     * 忽略大小写判断两个字符串相等
     *
     * @param src src
     * @param tar tar
     * @return 相等返回true;
     */
    public static boolean equalsIgnoreCase(String src, String tar) {
        if (null == src || null == tar) {
            return src == tar;
        } else {
            return src.equalsIgnoreCase(tar);
        }
    }

    /**
     * 判断str是否以prefix开头
     *
     * @param src    src
     * @param prefix prefix
     * @return 是以prefix开头为true
     */
    public static boolean startsWith(String src, String prefix) {
        return src != null && src.startsWith(prefix);
    }

    /**
     * 拼接字符串
     *
     * @param list 字符串列表
     * @param cat  连接符
     * @return
     */
    public static String join(List<Object> list, String cat) {
        cat = cat == null ? "" : cat;
        StringBuilder sb = new StringBuilder();
        for (Object item : list) {
            sb.append(cat).append(item);
        }
        return sb.substring(cat.length());
    }

    /**
     * 数组中是否包含该对象
     *
     * @param str    对比的字符串
     * @param soucre 集合
     * @return
     */
    public static Boolean contains(String str, List<Object> soucre) {
        HashSet<Object> set = new HashSet<>(soucre);
        return set.contains(str);
    }

}
