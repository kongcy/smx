package com.xtxk.core.util;

import java.util.List;

public abstract class AnnotationUtils {


    public Class getClass(String className){
        if(U.isBlank(className)){
            return null;
        }
        try {
            Class clazz = Class.forName(className);
        } catch (ClassNotFoundException e) {
            LogUtil.ROOT_LOG.error("无法找到className： "+className);
        }
        return null;
    }

}
