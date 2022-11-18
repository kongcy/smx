package com.xtxk.dataSource.processor;

import org.aopalliance.intercept.MethodInvocation;

/**方法参数中来匹配
 * User: chenying
 * Date: 2019-07-12
 * Time: 17:03
 * since: 1.0.0
 */
public class DsSpelExpressionProcessor implements DsProcessor {
    @Override
    public boolean matches(String key) {
        return false;
    }

    @Override
    public String determineDatasource(MethodInvocation invocation, String key) {
        return null;
    }
}
