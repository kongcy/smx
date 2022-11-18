package com.xtxk.system.api.model;

import com.google.common.collect.Lists;

import com.xtxk.core.util.A;
import com.xtxk.core.util.U;
import lombok.Data;
import java.util.List;
/**
 * Created  on 2016/8/23.
 * 菜单树型类
 */
@Data
public class Resource extends SysResource {

    private static final long serialVersionUID = 0L;

    public List<Resource> children= Lists.newArrayList();

    /** 检查指定的 url 和方法是否可以请求 */
    public boolean canRequest(String url, String method) {
        if (check(url, method)) {
            return true;
        }
        // 检查完自身, 再检查子节点
        // return checkChild(url, method);
        return checkChild(children, url, method);
    }

    /** 递归检查子菜单:「深度优先」 */
    private boolean checkChild(List<Resource> children, String url, String method) {
        if (A.isNotEmpty(children)) {
            for (Resource child : children) {
                if (child.check(url, method)) {
                    return true;
                }
                if (checkChild(child.getChildren(), url, method)) {
                    return true;
                }
            }
        }
        return false;
    }
    /** 检查子菜单:「广度优先」 */
    private boolean checkChild(String url, String method) {
        List<Resource> list = Lists.newLinkedList(children);
        while (A.isNotEmpty(list)) {
            Resource remove = list.remove(0);
            if (remove.check(url, method)) {
                return true;
            }
            List<Resource> resourceList = remove.getChildren();
            if (A.isNotEmpty(resourceList)) {
                list.addAll(resourceList);
            }
        }
        return false;
    }

    /** 真正检查逻辑的地方 */
    private boolean check(String url, String method) {
        if (U.isBlank(url) || U.isBlank(method)) return false;

        url = url.trim();
        String curl = super.getUrl().trim();

        method = method.trim();
        String checkMethod = super.getMethod().trim();

        // 如果配置的 url 是 /user/*, 传进来的是 /user/info 也可以通过, 通配 或 全字
        boolean urlCheck = (curl.endsWith("/*") && url.startsWith(curl.replace("*", ""))) || url.equals(curl);
        // 如果配置的 method 是 *, 传进来的是 GET 也可以通过, 通配 或 全字
        boolean methodCheck = (("*").equals(checkMethod) || checkMethod.contains(method));

        // url 和 method 都通过才表示有访问权限
        return urlCheck && methodCheck;
    }
}
