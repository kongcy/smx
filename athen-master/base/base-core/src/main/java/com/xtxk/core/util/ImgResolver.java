package com.xtxk.core.util;

/** 商品图片地址工具类 */
public final class ImgResolver {

    /** 图片比例 */
    private static final String ADD_SUFFIX = "_lite";

    public static String getLiteImage(String imageName) {
        String suffix = U.getSuffix(imageName);
        return imageName.replace(suffix, (ADD_SUFFIX + suffix));
    }

}
