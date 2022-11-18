package com.xtxk.system.api.service;

import java.util.List;
import com.xtxk.core.annotation.NoToken;
import com.xtxk.core.annotation.NoTokenVerify;
import com.xtxk.core.annotation.Page;
import com.xtxk.core.vo.PageList;
import com.xtxk.core.vo.SysMenu;
import com.xtxk.system.api.DTO.*;
import com.xtxk.system.api.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 系统权限服务类
 *
 * @author cheny
 */
@NoTokenVerify
@FeignClient(name = "biz-system-service")
public interface SystemService {

    /**
     * 用户接口
     * =================================================================================================================
     * 用户登陆
     *
     * @param loginName
     * @param password
     * @return SysUser
     **/
    @NoToken
    @RequestMapping(value = "/system/login", method = RequestMethod.POST)
    SysUser login(@RequestParam String loginName, @RequestParam String password);

    /**
     * 查找最近5分钟内的登录失败的记录
     * @param loginName 用户ID
     */
    @RequestMapping(value = "/system/findLateLoginRecord", method = RequestMethod.GET)
    List<SysLoginRecord> findLateLoginRecord(@RequestParam String loginName);

    /**
     * 查找是否有锁定的登录记录
     * @param loginName 用户账号
     */
    @RequestMapping(value = "/system/findLockLoginRecord",method = RequestMethod.GET)
    SysLoginRecord findLockLoginRecord(@RequestParam String loginName);

    /**
     * @param requestUrl 请求URL
     * @param menus      菜单数组
     */
    @RequestMapping(value = "/system/VerifyPermission", method = RequestMethod.POST)
    boolean VerifyPermission(@RequestParam String requestUrl, @RequestBody List<SysMenu> menus);

    /**
     * 获取用户信息通过用户名
     *
     * @param loginName
     * @return SysUser
     */
    @RequestMapping(value = "/system/findUserByLoginName", method = RequestMethod.GET)
    SysUser findUserByLoginName(@RequestParam String loginName);

    /**
     * 查询所有用户列表
     */
    @RequestMapping(value = "/system/findUsers", method = RequestMethod.GET)
    List<SysUser> findUsers();

    /**
     * 锁定账号
     *
     * @param loginName 登录账号
     * @param state     状态
     */
    @RequestMapping(value = "/system/lockUser", method = RequestMethod.POST)
    boolean lockUser(@RequestParam String loginName, @RequestParam int state);
    /**
     * 解锁账号
     *
     */
    @RequestMapping(value = "/system/unLockUser",method = RequestMethod.POST)
    boolean unLockUser(@RequestParam String loginName,@RequestParam String loginId);

    /**
     * 根据查询条件获取用户列表
     */
    @Page
    @RequestMapping(value = "/system/findPageUserByCondition", method = RequestMethod.POST)
    PageList findPageUserByCondition(@RequestParam(required = false) String condition);

    /**
     * 分页查询组织下用户列表
     *
     * @param orgId
     */
    @RequestMapping(value = "/system/findUserListByOrgId", method = RequestMethod.GET)
    PageList findUserListByOrgId(@RequestParam String orgId);

    /**
     * 用户绑定机构
     *
     * @param userOrg
     */
    @RequestMapping(value = "/system/bindUserOrg", method = RequestMethod.POST)
    boolean bindUserOrg(@RequestBody SysUserOrg userOrg);

    /**
     * 查找用户通过ID
     *
     * @param userId
     */
    @RequestMapping(value = "/system/findUserById", method = RequestMethod.GET)
    SysUser findUserById(@RequestParam String userId);

    /**
     * 保存用户
     */
    @RequestMapping(value = "/system/saveUser", method = RequestMethod.POST)
    boolean saveUser(@RequestBody SysUserDTO userDTO);

    /**
     * 更新用户信息
     */
    @RequestMapping(value = "/system/updateUser", method = RequestMethod.POST)
    boolean updateUser(@RequestBody SysUserDTO userDTO);

    /**
     * 查找用户下所有的角色
     *
     * @param userId
     */
    @RequestMapping(value = "/system/findRoleByUserId", method = RequestMethod.GET)
    List<SysRoleDTO> findRoleByUserId(@RequestParam String userId);


    /**
     * 保存用户角色
     */
    @RequestMapping(value = "/system/bindUserRole", method = RequestMethod.POST)
    boolean bindUserRole(@RequestBody SysUserRoleDTO userRoleDTO);

    /**
     * 检查登陆名是否已存在
     */
    @RequestMapping(value = "/system/checkUserByLoginName", method = RequestMethod.GET)
    boolean checkUserByLoginName(@RequestParam String loginName);

    /**
     * 删除用户
     */
    @RequestMapping(value = "/system/deleteUserById", method = RequestMethod.POST)
    boolean deleteUserById(@RequestParam String userId);

    /**
     * 批量删除用户
     */
    @RequestMapping(value = "/system/batchDeleteUser", method = RequestMethod.POST)
    boolean batchDeleteUser(@RequestBody String[] userIds);

    /**
     * =================================================================================================================
     * 获取所有角色
     */
    @RequestMapping(value = "/system/findAll", method = RequestMethod.GET)
    List<SysRole> findAll();

    /**
     * 获取用户的角色
     **/
    @RequestMapping(value = "/system/findRoleByUser", method = RequestMethod.GET)
    List<SysRole> findRoleByUser(@RequestParam String userId);

    /**
     * 根据查询条件获取角色列表
     **/
    @Page
    @RequestMapping(value = "/system/finsPageRoleByCondition", method = RequestMethod.POST)
    PageList finsPageRoleByCondition(@RequestBody SysRole role);

    /**
     * 分页查询角色下用户
     */
    @RequestMapping(value = "/system/findPageUserByRoleId", method = RequestMethod.GET)
    PageList findPageUserByRoleId(@RequestParam String roleId);

    /**
     * 查询角色下用户列表
     */
    @RequestMapping(value = "/system/findUserByRoleId", method = RequestMethod.GET)
    PageList findUserByRoleId(@RequestParam String roleId);

    /**
     * 获取角色下所有菜单
     *
     * @param roleId 角色ID
     */
    @RequestMapping(value = "/system/findResByRoleId", method = RequestMethod.GET)
    List<SysResourceDTO> findResByRoleId(@RequestParam String roleId);

    /**
     * 获取角色下所有菜单ID
     *
     * @param roleId 角色ID
     */
    @RequestMapping(value = "/system/findResIdsByRoleId", method = RequestMethod.GET)
    List<String> findResIdsByRoleId(@RequestParam String roleId);

    /**
     * 获取角色下所有的资源
     */
    @RequestMapping(value = "/system/GetResourceTreeHasSelect", method = RequestMethod.GET)
    List<SysMenu> GetResourceTreeHasSelect(@RequestParam String roleId);

    /**
     * 角色授权
     */
    @RequestMapping(value = "/system/authorize", method = RequestMethod.POST)
    boolean authorize(@RequestBody SysAuthorDetailDTO detailDTO);

    /**
     * 新增角色
     *
     * @param roleDetail
     */
    @RequestMapping(value = "/system/saveRole", method = RequestMethod.POST)
    boolean saveRole(@RequestBody SysRoleDetailDTO roleDetail);

    /**
     * 更新角色
     *
     * @param detail
     */
    @RequestMapping(value = "/system/updateRole", method = RequestMethod.POST)
    boolean updateRole(@RequestBody SysRoleDetailDTO detail);

    /**
     * 角色绑定资源
     *
     * @param resIds
     * @param roleId
     */
    @RequestMapping(value = "/system/saveRoleAndResource", method = RequestMethod.POST)
    boolean saveRoleAndResource(@RequestParam String roleId, @RequestBody String[] resIds);

    /**
     * 获取角色信息
     */
    @RequestMapping(value = "/system/findRoleById", method = RequestMethod.GET)
    SysRole findRoleById(@RequestParam String roleId);

    /**
     * 删除角色
     */
    @RequestMapping(value = "/system/deleteRoleById", method = RequestMethod.POST)
    void deleteRoleById(@RequestParam String roleId);

    /**
     * 删除角色
     */
    @RequestMapping(value = "/system/batchDeleteRole", method = RequestMethod.POST)
    void batchDeleteRole(@RequestBody String[] roleIds);

    /**
     * 查询所有菜单的授权状态
     *
     * @param userId
     */
    @NoToken
    @RequestMapping(value = "/system/findMenuList", method = RequestMethod.GET)
    List<SysMenu> findMenuList(@RequestParam String userId);

    /**
     * 获取所有资源列表
     */
    @RequestMapping(value = "/system/findResourceTree", method = RequestMethod.GET)
    List<SysResourceDTO> findResourceTree(@RequestParam String userId);

    /**
     * 查询用户授权菜单
     *
     * @param userId 当前登陆用户ID
     */
    @NoToken
    @RequestMapping(value = "/system/findTreeMenu", method = RequestMethod.GET)
    List<SysMenu> findTreeMenu(@RequestParam String userId);

    /**
     * 获取用户下所有的资源菜单
     *
     * @param userId 用户id
     */
    @RequestMapping(value = "/system/findResourceByUserId", method = RequestMethod.GET)
    List<SysResourceDTO> findResourceByUserId(@RequestParam String userId);

    /**
     * 查找用户权限
     *
     * @param bizId 用户id
     * @param resId 菜单id
     */
    @RequestMapping(value = "/system/checkAuthorityByUserIdAndResId", method = RequestMethod.GET)
    List<SysRoleRes> findRightByBizIdAndResId(@RequestParam String bizId, @RequestParam String resId, @RequestParam boolean isUserId);


    /**
     * 获取菜单资源
     */
    @RequestMapping(value = "/system/findResById", method = RequestMethod.GET)
    SysResource findResById(@RequestParam String resId);

    /**
     * 保存资源
     */
    @RequestMapping(value = "/system/saveResource", method = RequestMethod.POST)
    boolean saveResource(@RequestBody SysResource res);

    /**
     * 更新资源
     */
    @RequestMapping(value = "/system/updateResource", method = RequestMethod.POST)
    boolean updateResource(@RequestBody SysResource resource);

    /**
     * 删除资源，级联删除子资源
     */
    @RequestMapping(value = "/system/deleteResource", method = RequestMethod.POST)
    boolean deleteResource(@RequestParam String resId);

    /**
     * 批量删除资源，级联删除子资源
     */
    @RequestMapping(value = "/system/batchDeleteResource", method = RequestMethod.POST)
    boolean batchDeleteResource(@RequestBody String[] resIds);

    /**
     * 获取用户资源
     */
    @RequestMapping(value = "/system/caseResource", method = RequestMethod.GET)
    List<Resource> caseResource(@RequestParam String userId);

    /**
     * 根据查询条件获取资源列表
     */
    @RequestMapping(value = "/system/findPageResByCondition", method = RequestMethod.POST)
    PageList findPageResByCondition(@RequestBody SysResource res);

    /**
     * 机构
     * =================================================================================================================
     * 分页，条件查询机构组织
     */
    @RequestMapping(value = "/system/findPageOrgByCondition", method = RequestMethod.POST)
    PageList findPageOrgByCondition(@RequestBody SysOrganization organization);

    /**
     * 树型查询机构
     */
    @RequestMapping(value = "/system/findOrgTreeList", method = RequestMethod.GET)
    List<SysOrganization> findOrgTreeList();

    /**
     * 保存机构
     */
    @RequestMapping(value = "/system/saveOrganization", method = RequestMethod.POST)
    boolean saveOrganization(@RequestBody SysOrganization organization);

    /**
     * 更新机构
     */
    @RequestMapping(value = "/system/updateOrganization", method = RequestMethod.POST)
    boolean updateOrganization(@RequestBody SysOrganization organization);

    /**
     * 删除机构
     */
    @RequestMapping(value = "/system/deleteOrganization", method = RequestMethod.POST)
    boolean deleteOrganization(@RequestParam String orgId);

    /**
     * 批量删除机构
     */
    @RequestMapping(value = "/system/batchDeleteOrganization", method = RequestMethod.POST)
    boolean batchDeleteOrganization(@RequestBody String[] orgIds);

}
