package com.xtxk.core.util;

import com.xtxk.core.json.JsonUtil;
import com.xtxk.core.vo.SysMenu;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

import static com.xtxk.core.util.LogUtil.ROOT_LOG;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/19
 */
public class SessionUtils {
    private static final Long DEFAULT_ID = 0l;
    private static final String DEFAULT_NAME = "未登录用户";
    /**
     * 放在 session 里的图片验证码 key
     */
    private static final String CODE = SessionUtils.class.getName() + "-CODE";
    /**
     * 放在 session 里的用户
     */
    public static final String USER = SessionUtils.class.getName() + "-USER";
    /**
     * 用户权限
     */
    public static final String RESOURCE = SessionUtils.class.getName() + "-RESOURCE";

    /**
     * 超级管理员账号
     */
    private static final List<String> SUPER_USER = A.lists("root", "admin","ADMIN");

    private static final String ID = "id";
    private static final String LOGIN_NAME = "loginName";
    private static final String USER_NAME = "userName";

    /**
     * 验证图片验证码
     */
    public static boolean checkImageCode(String code) {
        if (U.isBlank(code)) return false;
        Object securityCode = RequestUtils.getSession().getAttribute(CODE);
        return securityCode != null && code.equalsIgnoreCase(securityCode.toString());
    }

    /**
     * 将图片验证码的值放入 session
     */
    public static void putImageCode(String code) {
        RequestUtils.getSession().setAttribute(CODE, code);
        if (ROOT_LOG.isDebugEnabled()) {
            ROOT_LOG.debug("put image code ({}) in session ({})", code, RequestUtils.getSession().getId());
        }
    }


    /**
     * 注册登录后, 把 用户信息 写入 session
     */
//    public static void signIn(SysUser user, List<SysMenu> menus) {
//        user.setMenus(menus);
//        user.setSessionId(RequestUtils.getSession().getId());
//        RequestUtils.getSession().setAttribute(USER, user);
//        saveRes(menus);
//    }
    public static void signIn(Object userInfo) {
        RequestUtils.getSession().setAttribute(USER, userInfo);
    }

    /**
     * 操作资源的时候直接更新次方法
     */
//    public static void saveRes(List<SysMenu> menus) {
//        RequestUtils.getSession().setAttribute(RESOURCE, menus);
//    }

    /**
     * 退出登录.
     */
    public static void signOut() {
        RequestUtils.getSession().invalidate();
    }

    public static Object getUser() {
        Object user = RequestUtils.getSession().getAttribute(USER);
        return user == null ? null : user;
    }

    @SuppressWarnings("unchecked")
    public static List<SysMenu> getResource() {
        Object menus = RequestUtils.getSession().getAttribute(RESOURCE);
        return menus == null ? null : (List<SysMenu>) menus;
    }

    private static Map<String, Object> getUserInfo() {
        Object user = getUser();
        Map<String, Object> userInfo = null;
        if (user != null) {
            userInfo = JsonUtil.fromJson(JsonUtil.toJson(user), Map.class);
        }
        return userInfo;
    }

    /**
     * 获取 session 中的 userId
     */
    public static String getUserId() {
        Map<String, Object> userInfo = getUserInfo();
        return (userInfo == null) ? U.EMPTY : userInfo.get(ID) + U.EMPTY;
    }

    /**
     * 获取 session 中的 userName
     */
    public static String getUserName() {
        Map<String, Object> userInfo = getUserInfo();
        return (userInfo == null) ? DEFAULT_NAME : userInfo.get(USER_NAME) + U.EMPTY;
    }


    /**
     * 是否是超级管理员, 是则返回 true
     */
    public static boolean isSuper() {
        Map<String, Object> userInfo = getUserInfo();
        return userInfo != null && SUPER_USER.contains(userInfo.get(LOGIN_NAME));
    }

    public static boolean isSuper(String name){
        return U.isNotBlank(name)&&SUPER_USER.contains(name);
    }


    /**
     * 是否是超级管理员, 不是则返回 true
     */
    public static boolean isNotSuper() {
        return !isSuper();
    }

    /**
     * 验证用户是否有登录, 有登录则返回 true
     */
    public static boolean isLogin() {
        // 从 session 中获取的 用户id 和 用户名 都不是默认值就表示登录了
        return !DEFAULT_ID.equals(getUserId()) && !DEFAULT_NAME.equals(getUserName());
    }

    /**
     * 验证用户是否有登录, 没有登录则返回 true
     */
    public static boolean isNotLogin() {
        return !isLogin();
    }

//    /**
//     * 验证登录, 未登录则抛出异常
//     */
////    public static void checkLogin() {
////        if (isNotLogin()) {
////            throw new NotLoginException(RequestUtils.getRequest().getRequestURI());
////        }
////    }

//    /**
//     * 是否有访问权限, 有则返回 true
//     */
//    public static boolean isPermission() {
//        // 没有登录当然也就表示没有权限了
////        checkLogin();
//        // 管理员直接放过权限检查
//        if (isSuper()) return true;
//
//        String url = RequestUtils.getRequest().getRequestURI();
//        if (U.isBlank(url)) return false;
//
//        String method = RequestUtils.getRequest().getMethod();
//        if (U.isBlank(method)) return false;
//
//        List<SysMenu> menus = getResource();
//        if (A.isNotEmpty(menus)) {
//            for (SysMenu menu : menus) {
//                if (delSuffix(url).equalsIgnoreCase(delSuffix(menu.getUrl()))) {
//                    String rightId = menu.getRightId();
//                    if (U.isNotBlank(rightId) && !rightId.contains(String.valueOf(Permission.NO_PERMISSION.getCode()))) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }


//    /**
//     * 检查权限, 无权限则抛出异常
//     */
//    public static void checkPermission() {
//        if (!isPermission()) {
//            LogUtil.LOG.error("=======================================您没有当前 url(" + RequestUtils.getRequest().getRequestURL() + ") 的访问权限========================================");
//            throw new ForbiddenException("您没有接口访问权限！");
//        }
//    }

    /**
     * 获取数据源key
     **/
    public static String getDataSourceKey(HttpServletRequest request) {
        if (request == null) {
            request = RequestUtils.getRequest();
        }
        String dataSourceKey = request.getHeader(U.DATA_SOURCE_KEY);
        if (U.isBlank(dataSourceKey)) {
            dataSourceKey = request.getSession().getAttribute(U.DATA_SOURCE_KEY) + "";
        }
        if (U.isBlank(dataSourceKey)) {
            dataSourceKey = request.getParameter(U.DATA_SOURCE_KEY);
            if (U.isBlank(dataSourceKey)) {
                dataSourceKey = request.getAttribute(U.DATA_SOURCE_KEY) + "";
            }
        }
        return dataSourceKey;
    }


    public static String getUrl() {
        return RequestUtils.getRequest().getRequestURI();
    }

    public static String getPath() {
        return RequestUtils.getRequest().getServletPath();
    }

    public static String getReferer() {
        return RequestUtils.getRequest().getHeader("referer");
    }
}
