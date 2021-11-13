//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.madao.plugin.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author GuoGuang
 * @公众号 码道人生
 * @gitHub https://github.com/GuoGuang
 * @website https://madaoo.com
 * @created 2021-11-13 02:37
 */
public class MyStringUtils {
    public MyStringUtils() {
    }

    public static String firstLetterToLower(String str) {
        return StringUtils.isBlank(str) ? "" : str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toLowerCase());
    }
}
