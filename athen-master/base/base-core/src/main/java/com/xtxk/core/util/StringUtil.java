package com.xtxk.core.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by Administrator on 2020/12/18.
 */
public final class StringUtil {

    private StringUtil() {}

    public static String encodeBase64(String toEncode){
        if (StringUtils.isBlank(toEncode)) {
            return toEncode;
        }
        return Base64.getEncoder().encodeToString(toEncode.getBytes(Charset.forName("UTF-8")));
    }

    public static String decodeBase64(String toDecode){
        if (StringUtils.isBlank(toDecode)) {
            return toDecode;
        }
        return new String(Base64.getDecoder().decode(toDecode.getBytes(Charset.forName("UTF-8"))),
                Charset.forName("UTF-8"));
    }

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static void main(String[] args) {
        String i = encodeBase64("123456");
        System.out.println(i);
        System.out.println(decodeBase64(i));
        System.out.println("dmtjhpt.tlm".substring(0, "dmtjhpt.tlm".indexOf(".")));
    }
}
