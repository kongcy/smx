package com.xtxk.system.service;

import com.github.pagehelper.Page;
import com.xtxk.core.Constant;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.encrypt.Encrypt;
import com.xtxk.core.exception.Assert;
import com.xtxk.core.util.*;
import com.xtxk.core.vo.PageList;
import com.xtxk.core.vo.Permission;
import com.xtxk.core.vo.SysMenu;
import com.xtxk.system.api.DTO.*;
import com.xtxk.system.api.model.*;
import com.xtxk.system.api.model.enums.Source;
import com.xtxk.system.api.model.enums.State;
import com.xtxk.system.api.service.SystemService;
import com.xtxk.system.repository.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import static com.xtxk.core.Constant.ERROR_CODE_401;
import static com.xtxk.core.vo.PageList.defaultPageList;
import static com.xtxk.core.vo.PageList.toPage;

@RestController
public class SystemServiceImpl implements SystemService {
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private SysRoleMapper roleMapper;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private SysResourceMapper resourceMapper;
    @Autowired
    private SysRoleResMapper roleResMapper;
    @Autowired
    private SysOrganizationMapper organizationMapper;
    @Autowired
    private SysUserOrgMapper userOrgMapper;
    @Autowired
    private SysLoginRecordMapper loginRecordMapper;
    @Autowired
    private SysUserFaceInfoMapper faceInfoMapper;

    @Override
    public SysUser login(String loginName, String password) {
        Assert.checkNull(loginName, "请输入登录账号！");
        Assert.checkNull(password, "请输入登录密码！");
        SysUser user = findUserByLoginName(loginName);
        Assert.checkNull(user, "登录账号错误！");
        Assert.checkIsTrue(user.getState() == Constant.LoginSate.FAIL.getCode(), "用户账号已经停用,请联系管理员！");
        unLockUser(user.getState(), loginName);
        Assert.checkFalseWithAuthError(user.getPassword().equals(Encrypt.toMd5(password)), ERROR_CODE_401 + ":密码输入错误！");
        List<SysRoleDTO> role = roleMapper.findRoleByUserId(user.getId());
        List<SysOrgDTO> organization = organizationMapper.findOrgByUserId(user.getId());
        user.setOrganizations(organization);
        user.setRoles(role);
        user.setPassword(null);
        return user;
    }

    /**
     * 锁定用户
     *
     * @param loginName 登录用户
     * @param state     状态
     */
    private void unLockUser(int state, String loginName) {
        if (state == Constant.LoginSate.LOCK.getCode()) {
            Date nowTime = new Date();
            SysLoginRecord record = findLockLoginRecord(loginName);
            if (U.isNotBlank(record)) {
                Date endTime = record.getEndTime();
                if (null != endTime && nowTime.before(endTime)) {
                    Assert.checkIsTrue(true, "您已经连续输入密码错误超过3次，账号将锁定10分钟，请稍后再尝试！");
                } else {
                    unLockUser(loginName, record.getLoginId());
                }
            } else {
                unLockUser(loginName, null);
            }
        }
    }

    @Override
    public List<SysLoginRecord> findLateLoginRecord(String userId) {
        if (U.isBlank(userId)) {
            return A.lists();
        }
        SysLoginRecordExample se = new SysLoginRecordExample();
        SysLoginRecordExample.Criteria criteria = se.createCriteria();
        criteria.andUserIdEqualTo(userId);
        Date beginTime = new Date();
        Date endTime = DateUtil.addMinute(beginTime, -2);
        criteria.andBeginTimeBetween(endTime, beginTime);
        se.setOrderByClause("BEGIN_TIME DESC");
        List<SysLoginRecord> records = loginRecordMapper.selectByExample(se);
        if (A.isEmpty(records)) {
            return A.lists();
        }
        boolean isLoginFail = true;
        for (SysLoginRecord record : records) {
            if (record.isSuccess()) {
                isLoginFail = false;
                break;
            }
        }
        if (isLoginFail) {
            return records;
        }
        return A.lists();
    }

    @Override
    public SysLoginRecord findLockLoginRecord(String loginName) {
        if (U.isBlank(loginName)) {
            return null;
        }
        SysLoginRecordExample example = new SysLoginRecordExample();
        SysLoginRecordExample.Criteria criteria = example.or();
        criteria.andUserIdEqualTo(loginName);
        criteria.andStatusEqualTo(Constant.LoginSate.LOCK.getCode());
        //    criteria.andEndTimeGreaterThanOrEqualTo(new Date());
        example.setOrderByClause("BEGIN_TIME DESC");
        List<SysLoginRecord> recordList = loginRecordMapper.selectByExample(example);
        return A.first(recordList);
    }

    @Override
    public boolean VerifyPermission(String requestUrl, @RequestBody List<SysMenu> menus) {
        if (U.isBlank(requestUrl)) {
            return false;
        }
        if (A.isEmpty(menus)) {
            return false;
        }
        for (SysMenu menu : menus) {
            if (U.delSuffix(requestUrl).equalsIgnoreCase(U.delSuffix(menu.getUrl()))) {
                String rightId = menu.getRightId();
                if (U.isNotBlank(rightId) && !rightId.contains(String.valueOf(Permission.NO_PERMISSION.getCode()))) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public SysUser findUserByLoginName(String loginName) {
       List<SysUser> users = userMapper.findUserByLoginName(loginName);
        return A.first(users);
    }

    /**
     * 查询所有用户列表
     */
    @Override
    public List<SysUser> findUsers() {
        return userMapper.findUsers();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean lockUser(String loginName, int state) {
        if (U.isBlank(loginName)) {
            LogUtil.ROOT_LOG.warn("======================用户登录账号【loginName】为空，锁定账号失败！");
            return false;
        }
        SysUser user = A.first(userMapper.findUserByLoginName(loginName));
        if (user == null) {
            LogUtil.ROOT_LOG.warn("======================系统不能检测到账号：【{}】，锁定账号失败！", loginName);
            return false;
        }
        List<SysLoginRecord> records = findLateLoginRecord(loginName);
        if (A.isEmpty(records) || records.size() < 2) {
            LogUtil.ROOT_LOG.warn("======================账号：{}已经密码错误输入 {}", loginName, A.isEmpty(records) ? 0 : records.size());
            return false;
        }
        user.setState(state);
        SysLoginRecord record = A.first(records);
        if (userMapper.updateByPrimaryKeySelective(user) > 0) {
            Date endTime = DateUtil.addMinute(record.getBeginTime(), 10);
            record.setEndTime(endTime);
            record.setStatus(state);
            loginRecordMapper.updateByPrimaryKeySelective(record);
            return true;
        }
        return false;
    }

    /**
     * 解锁账号
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean unLockUser(String loginName, String loginId) {
        if (U.isBlank(loginName)) {
            LogUtil.ROOT_LOG.warn("======================用户登录账号【loginName】为空，解锁失败！");
            return false;
        }
        SysUser user = A.first(userMapper.findUserByLoginName(loginName));
        if (null == user) {
            LogUtil.ROOT_LOG.warn("======================系统不能检测到账号：【{}】，解锁失败！", loginName);
            return false;
        }
        user.setState(Constant.LoginSate.SUCCESS.getCode());
        if (userMapper.updateByPrimaryKeySelective(user) > 0) {
            if (U.isNotBlank(loginId)) {
                SysLoginRecord record = loginRecordMapper.selectByPrimaryKey(loginId);
                record.setEndTime(null);
                record.setStatus(Constant.LoginSate.FAIL.getCode());
                loginRecordMapper.updateByPrimaryKey(record);
                return true;
            }
        }
        return false;
    }

    @Override
    public PageList findPageUserByCondition(String condition) {
        Page<SysUser> page = (Page<SysUser>) userMapper.findPageUserByCondition(condition);
        return filter(page);
    }

    /**
     * 分页查询组织下用户列表
     *
     * @param orgId
     */
    @Override
    public PageList findUserListByOrgId(String orgId) {
        Assert.checkNull(orgId, "组织机构不能为空!");
        Page<SysUser> page = (Page<SysUser>) userMapper.findUserByOrgId(orgId);
        return filter(page);
    }

    private PageList filter(Page<SysUser> page) {
        if (page == null || A.isEmpty(page.getResult())) {
            return defaultPageList();
        }
        List<SysUser> users = page.getResult();
        for (SysUser u : users) {
            List<SysRoleDTO> roleDTOS = roleMapper.findRoleByUserId(u.getId());
            u.setPassword("");
            u.setRoles(roleDTOS);
            List<SysOrgDTO> organizations = organizationMapper.findOrgByUserId(u.getId());
            u.setOrganizations(organizations);
        }
        return toPage(page.getTotal(), page.getPageNum(), users);
    }

    /**
     * 用户绑定机构
     *
     * @param userOrg
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bindUserOrg(@RequestBody SysUserOrg userOrg) {
        Assert.checkNull(userOrg, "请求参数不能为空！");
        Assert.checkNull(userOrg.getUserId(), "请至少选择一个用户！");
        Assert.checkNull(userOrg.getOrgId(), "请至少选择一个部门！");
        SysUser user = userMapper.selectByPrimaryKey(userOrg.getUserId());
        Assert.checkNull(user, "ID为:{" + userOrg.getUserId() + "}用户在系统中不存在!");
        Assert.checkNull(organizationMapper.selectByPrimaryKey(userOrg.getOrgId()), "ID为:{" + userOrg.getOrgId() + "}机构在系统中不存在!");
        int count = userOrgMapper.insert(userOrg);
        if (count > 0) {
            return true;
        }
        return false;
    }

    @Override
    public SysUser findUserById(String userId) {
        SysUser user = userMapper.selectByPrimaryKey(userId);
        if (user != null) {
            List<SysRoleDTO> roleDTOS = roleMapper.findRoleByUserId(userId);
            user.setRoles(roleDTOS);
        }
        return user;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveUser(@RequestBody SysUserDTO userDTO) {
        Assert.checkNull(userDTO, "请求参数不能为空!");
        Assert.checkNull(userDTO.getLoginName(), "登录账号不能为空！");
        Assert.checkNull(userDTO.getUserName(), "用户姓名不能为空！");
        Assert.checkNull(userDTO.getIdCard(),"身份证号不能为空！");
        Assert.checkIsFalse(IDCardUtil.isValidCard(userDTO.getIdCard()),"请填写正确的身份证号码！");
        List<SysUser> users = userMapper.findUserByLoginName(userDTO.getLoginName());
        Assert.checkIsTrue(A.isNotEmpty(users), "登录账号已存在，请勿重复添加！");
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);
        user.setId(U.uuid());
        user.setCreateTime(new Date());
        if(U.isBlank(userDTO.getPassword())){
            String password = userDTO.getIdCard().substring(userDTO.getIdCard().length()-6);
            user.setPassword(Encrypt.toMd5(password));
        }else{
            user.setPassword(Encrypt.toMd5(userDTO.getPassword()));
        }
        user.setSource(Source.Platform.name());
        user.setState(Constant.LoginSate.SUCCESS.getCode());
        if (userMapper.insertSelective(user) > 0) {
            //插入数据到平台用户表和资源表
            addUserToPlatform(user);
            //用户绑定角色
            List<String> roleIds = userDTO.getRoleIds();
            if (A.isNotEmpty(roleIds)) {
                bindUserRole(new SysUserRoleDTO(user.getId(), roleIds));
            }
            //用户绑定部门
            if (U.isNotBlank(user.getOrgId())) {
                String[] orgIds = user.getOrgId().split(",");
                for (String orgId : orgIds) {
                    bindUserOrg(new SysUserOrg(U.uuid(),user.getId(), orgId));
                }
            }
            return true;
        }
        return true;
    }

    /**
     * 添加组织到
     *
     * @param organization
     */
    private void addDirectoryToPlatform(SysOrganization organization) {

    }

    /**
     * 添加用户到平台
     *
     * @param user user
     */
    private void addUserToPlatform(SysUser user) {
    }

    /**
     * 更新用户信息
     *
     * @param userDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateUser(@RequestBody SysUserDTO userDTO) {
        Assert.checkNull(userDTO.getId(), "用户id不能为空!");
        Assert.checkNull(userMapper.selectByPrimaryKey(userDTO.getId()), "用户{ID: " + userDTO.getId() + "}在系统中查不到!");
        SysUser user = new SysUser();
        BeanUtils.copyProperties(userDTO, user);
        if (U.isNotBlank(user.getPassword())) {
            user.setPassword(Encrypt.toMd5(userDTO.getPassword()));
        }
        if (userMapper.updateByPrimaryKeySelective(user) > 0) {
            List<String> roleIds = user.getRoleIds();
            if (A.isNotEmpty(roleIds)) {
                bindUserRole(new SysUserRoleDTO(user.getId(), roleIds));
            }
            if (U.isNotBlank(user.getOrgId())) {
                userOrgMapper.deleteByUserId(user.getId());
                String[] orgIds = user.getOrgId().split(",");
                for (String orgId : orgIds) {
                    bindUserOrg(new SysUserOrg(U.uuid(),user.getId(), orgId));
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 查找用户下所有的角色
     *
     * @param userId
     */
    @Override
    public List<SysRoleDTO> findRoleByUserId(String userId) {
        Assert.checkNull(userId, "当前用户信息不能为空!");
        SysUser user = userMapper.selectByPrimaryKey(userId);
        Assert.checkNull(user, "该用户在系统不存在!");
        List<String> roleIds = userRoleMapper.findRoleByUserId(userId);
        if (A.isEmpty(roleIds)) {
            return A.lists();
        }
        List<SysRoleDTO> roles = roleMapper.findRoleIn(roleIds);
        if (A.isEmpty(roles)) {
            return A.lists();
        }
        return roles;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean bindUserRole(@RequestBody SysUserRoleDTO userRoleDTO) {
        Assert.checkNull(userRoleDTO, "请求参数不能为空!");
        String userId = userRoleDTO.getUserId();
        Assert.checkNull(userId, "用户ID不能为空!");
        SysUser user = userMapper.selectByPrimaryKey(userId);
        Assert.checkNull(user, "用户{ID: " + userId + "}在系统中不存在!");
        userRoleMapper.deleteRoleByUserId(userId);
        List<String> roleIds = userRoleDTO.getRoleIds();
        if (A.isNotEmpty(roleIds)) {
            for (String id : roleIds) {
                SysUserRole ur = new SysUserRole();
                ur.setId(U.uuid());
                ur.setUserId(userId);
                ur.setRoleId(id);
                userRoleMapper.insertSelective(ur);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkUserByLoginName(String loginName) {
        SysUserExample example = new SysUserExample();
        SysUserExample.Criteria criteria = example.createCriteria();
        criteria.andLoginNameEqualTo(loginName);
        List<SysUser> users = userMapper.selectByExample(example);
        return A.isEmpty(users);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteUserById(String userId) {
        Assert.checkNull(userId, "用户ID不能为空!");
        SysUser user = userMapper.selectByPrimaryKey(userId);
        Assert.checkNull(user, "用户[ID=" + userId + "]在系统中不存在!");
        Assert.checkIsTrue(SessionUtils.isSuper(user.getLoginName()), "超级管理员账号【admin】无法被删除！");
        if (userMapper.deleteByPrimaryKey(userId) > 0) {
            userRoleMapper.deleteRoleByUserId(userId);
            userOrgMapper.deleteByUserId(userId);
            faceInfoMapper.deleteUserFaceByUserId(userId);
            return true;
        }
        return false;
    }

    /**
     * 批量删除用户
     *
     * @param userIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean batchDeleteUser(@RequestBody String[] userIds) {
        for (String userId : userIds) {
            SysUser user = userMapper.selectByPrimaryKey(userId);
            if (user == null) continue;
            Assert.checkIsTrue(SessionUtils.isSuper(user.getLoginName()), "超级管理员账号【admin】无法被删除！");
            if (userMapper.deleteByPrimaryKey(userId) > 0) {
                userRoleMapper.deleteRoleByUserId(userId);
                userOrgMapper.deleteByUserId(userId);
                faceInfoMapper.deleteUserFaceByUserId(userId);
            }
        }
        return true;
    }

    @Override
    public List<SysRole> findAll() {
        return roleMapper.selectByExample(null);
    }

    @Override
    public List<SysRole> findRoleByUser(String userId) {
        List<String> rIds = userRoleMapper.findRoleByUserId(userId);
        if (A.isEmpty(rIds))
            return A.lists();
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(rIds);
        return roleMapper.selectByExample(example);
    }

    @Override
    public PageList finsPageRoleByCondition(@RequestBody SysRole role) {
        SysRoleExample example = null;
        if (role != null) {
            example = new SysRoleExample();
            SysRoleExample.Criteria criteria = example.or();
            if (U.isNotBlank(role.getName())) {
                criteria.andNameLike(U.like(role.getName()));
            }
            if (U.isNotBlank(role.getCode())) {
                criteria.andCodeLike(U.like(role.getCode()));
            }
        }
        Page<SysRole> page = (Page<SysRole>) roleMapper.selectByExample(example);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    @Override
    public PageList findPageUserByRoleId(String roleId) {
        Assert.checkNull(roleId, "请至少选择一个角色！");
        List<String> userIds = roleMapper.findUserIdsByRoleId(roleId);
        if (A.isEmpty(userIds)) {
            return defaultPageList();
        }
        SysUserExample example = new SysUserExample();
        example.createCriteria().andIdIn(userIds);
        Page<SysUser> page = (Page<SysUser>) userMapper.selectByExample(example);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    /**
     * 查询角色下用户列表
     *
     * @param roleId
     */
    @Override
    public PageList findUserByRoleId(String roleId) {
        Assert.checkNull(roleId, "角色ID不能为空!");
        SysRole sysRole = roleMapper.selectByPrimaryKey(roleId);
        Assert.checkNull(sysRole, "角色ID :[" + roleId + "]在系统中不存在!");
        List<String> userIds = roleMapper.findUserIdsByRoleId(roleId);
        if (A.isEmpty(userIds)) {
            return defaultPageList();
        }
        Page<SysUser> page = (Page<SysUser>) userMapper.findUserInIds(userIds);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    /**
     * 查询所有菜单的授权状态
     *
     * @param userId
     */
    @Override
    public List<SysMenu> findMenuList(String userId) {
        Assert.checkNull(userId, "用户ID不能为空!");
        SysUser user = userMapper.selectByPrimaryKey(userId);
        Assert.checkNull(user, "用户ID :[" + userId + "]在系统中不存在!");
        List<SysMenu> menus = resourceMapper.findMenus();
        if (A.isEmpty(menus)) {
            return A.lists();
        }
        for (SysMenu r : menus) {
            List<SysRoleRes> roleRes = findRightByBizIdAndResId(userId, r.getId(), true);
            r.setRightId(toRight(roleRes));
        }
        return menus;
    }

    @Override
    public List<SysResourceDTO> findResourceTree(String userId) {
        Assert.checkNull(userId, "用户ID不能为空!");
        SysUser user = userMapper.selectByPrimaryKey(userId);
        Assert.checkNull(user, "用户ID :[" + userId + "]在系统中不存在!");
        List<SysResourceDTO> supRes = resourceMapper.findSupRes();
        if (A.isEmpty(supRes)) {
            return A.lists();
        }
        return recursionGetRes(supRes, supRes, userId, true);
    }

    /**
     * 查询用户授权菜单
     *
     * @param userId 当前登陆用户ID
     */
    @Override
    public List<SysMenu> findTreeMenu(String userId) {
        Assert.checkNull(userId, "用户ID不能为空!");
        SysUser user = userMapper.selectByPrimaryKey(userId);
        Assert.checkNull(user, "用户ID :[" + userId + "]在系统中不存在!");
        List<SysMenu> sysMenus = resourceMapper.findRightMenuByUserId(userId);
        if (A.isEmpty(sysMenus)) {
            return A.lists();
        }
        return recursionGetMenu(sysMenus, sysMenus, userId, true, true);
    }

    /**
     * 获取用户下所有的资源菜单
     *
     * @param userId 用户id
     */
    @Override
    public List<SysResourceDTO> findResourceByUserId(String userId) {
        return null;
    }

    /**
     * 验证用户下是否有操作权限
     *
     * @param bizId 用户、角色id
     * @param resId 菜单id
     */
    @Override
    public List<SysRoleRes> findRightByBizIdAndResId(String bizId, String resId, boolean isUserId) {
        List<SysRoleRes> roleRes;
        if (isUserId) {
            roleRes = roleResMapper.findRightByUserIdAndResId(bizId, resId);
        } else {
            roleRes = roleResMapper.findRightByRoleIdAndResId(bizId, resId);
        }
        return roleRes;
    }

    /**
     * 递归i调用
     */
    private List<SysResourceDTO> recursionGetRes(List<SysResourceDTO> supRes, List<SysResourceDTO> childRes, String userId, boolean isUserId) {
        if (A.isNotEmpty(childRes)) {
            for (SysResourceDTO res : childRes) {
                List<SysRoleRes> roleRes = findRightByBizIdAndResId(userId, res.getId(), isUserId);
                res.setRightId(toRight(roleRes));
                List<SysResourceDTO> child = resourceMapper.findResBySupId(res.getId());
                if (A.isNotEmpty(child)) {
                    res.setChildrenRes(child);
                    supRes = recursionGetRes(supRes, child, userId, isUserId);
                }
            }
        }
        return supRes;
    }

    private String toRight(List<SysRoleRes> roleRes) {
        String defaultValue = String.valueOf(Permission.NO_PERMISSION.getCode());
        if (A.isEmpty(roleRes)) {
            return defaultValue;
        }
        StringBuilder builder = new StringBuilder();
        for (SysRoleRes res : roleRes) {
            int rightId = res.getRightId();
            if (rightId == Permission.OPERATION.getCode()) {
                builder.append(rightId);
                return builder.toString();
            }
        }
        if (U.isBlank(builder.toString())) {
            builder.append(defaultValue);
        }
        return builder.toString();
    }

    /**
     * 新增角色
     *
     * @param roleDetail
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRole(@RequestBody SysRoleDetailDTO roleDetail) {
        Assert.checkNull(roleDetail.getName(), "角色名称不能为空!");
        Assert.checkIsTrue(U.isNotBlank(findRoleByName(roleDetail.getName())), "角色名称已经存在!");
        SysRole role = SysRole.builder()
                .id(U.uuid())
                .code(roleDetail.getCode())
                .name(roleDetail.getName())
                .createTime(new Date())
                .description(roleDetail.getDescription())
                .state(State.Enable.getCode()).build();
        int count = roleMapper.insertSelective(role);
        String[] resIds = role.getResIds();
        if (count > 0) {
            if (U.isBlank(resIds)) {
                return true;
            } else {
                return saveRoleAndResource(role.getId(), resIds);
            }
        }
        return false;
    }


    /**
     * 更新角色
     *
     * @param detail
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateRole(@RequestBody SysRoleDetailDTO detail) {
        Assert.checkNull(detail.getId(), "角色ID不能为空!");
        Assert.checkNull(detail.getName(), "角色名称不能为空!");
        SysRole sysRole = roleMapper.selectByPrimaryKey(detail.getId());
        Assert.checkNull(sysRole, "角色在系统中不存在！");
        SysRole.SysRoleBuilder builder = SysRole.builder()
                .id(detail.getId())
                .name(detail.getName())
                .description(detail.getDescription())
                .state(detail.getState())
                .resIds(detail.getResIds());
        SysRole role = builder.build();
        int count = roleMapper.updateByPrimaryKeySelective(role);
        String[] resIds = role.getResIds();
        if (count > 0 && A.isNotEmpty(resIds)) {
            roleResMapper.deleteRoleResByRoleId(role.getId());
            if (U.isBlank(resIds)) {
                return true;
            } else {
                return saveRoleAndResource(role.getId(), resIds);
            }
        }
        return false;
    }

    /**
     * 通过名称查询角色
     *
     * @param name
     */
    private SysRole findRoleByName(String name) {
        if (U.isBlank(name)) {
            return null;
        }
        SysRoleExample example = new SysRoleExample();
        SysRoleExample.Criteria criteria = example.or();
        criteria.andNameEqualTo(name);
        return A.first(roleMapper.selectByExample(example));
    }

    /**
     * 保存角色资源
     *
     * @param roleId 角色id
     * @param resIds 资源集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveRoleAndResource(String roleId, String[] resIds) {
        if (A.isEmpty(resIds)) {
            return false;
        }
        for (String resId : resIds) {
            SysRoleRes rs = new SysRoleRes();
            rs.setRoleId(roleId);
            rs.setResId(resId);
            roleResMapper.insertSelective(rs);
        }
        return true;
    }

    @Override
    public SysRole findRoleById(String roleId) {
        SysRole role = roleMapper.selectByPrimaryKey(roleId);
        if (role != null) {
            List<SysResourceDTO> res = findResByRoleId(roleId);
            if (A.isNotEmpty(res)) {
                role.setRes(res);
            }
        }
        return role;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteRoleById(String roleId) {
        Assert.checkNull(roleId, "请至少选择一个角色！");
        SysRole role = roleMapper.selectByPrimaryKey(roleId);
        Assert.checkNull(role, "角色ID :[" + roleId + "]在系统中不存在！");
        Assert.checkIsTrue(SessionUtils.isSuper(role.getCode()), "管理员角色【admin】无法删除！");
        deleteRole(roleId);
    }

    /**
     * 批量角色
     *
     * @param roleIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchDeleteRole(@RequestBody String[] roleIds) {
        Assert.checkIsTrue(A.isEmpty(roleIds), "请至少选择一个角色！");
        for (String roleId : roleIds) {
            SysRole role = roleMapper.selectByPrimaryKey(roleId);
            if (role == null) continue;
            Assert.checkIsTrue(SessionUtils.isSuper(role.getCode()), "管理员角色【admin】无法删除！");
            deleteRole(roleId);
        }
    }

    private void deleteRole(String roleId) {
        if (roleMapper.deleteByPrimaryKey(roleId) > 0) {
            //删除角色资源关系
            SysRoleResExample se = new SysRoleResExample();
            se.or().andRoleIdEqualTo(roleId);
            roleResMapper.deleteByExample(se);
            //删除用户角色关系
            SysUserRoleExample ur = new SysUserRoleExample();
            ur.or().andRoleIdEqualTo(roleId);
            userRoleMapper.deleteByExample(ur);
        }
    }

    @Override
    public List<SysResourceDTO> findResByRoleId(String roleId) {
        List<String> res = roleResMapper.findRoleResByRoleId(roleId);
        if (A.isEmpty(res))
            return A.lists();
        List<SysResourceDTO> supRes = resourceMapper.findSupRes();
        if (A.isEmpty(supRes)) {
            return A.lists();
        }
        List<SysResourceDTO> resourceDTOS = recursionGetRes(supRes, supRes, roleId, false);
        return resourceDTOS;
    }

    /**
     * 获取菜单资源ID集合
     *
     * @param resIds       菜单ID集合
     * @param resourceDTOS 菜单资源
     * @return set
     */
    private Set<String> getIdsByResources(Set<String> resIds, List<SysResourceDTO> resourceDTOS) {
        resourceDTOS.stream().forEach(resourceDTO -> {
            resIds.add(resourceDTO.getId());
            if (A.isNotEmpty(resourceDTO.getChildrenRes())) {
                getIdsByResources(resIds, resourceDTO.getChildrenRes());
            }
        });
        return resIds;
    }

    @Override
    public List<String> findResIdsByRoleId(String roleId) {
//        List<String> res = roleResMapper.findRoleResByRoleId(roleId);
//        if (A.isEmpty(res))
//            return A.lists();
//        List<SysResourceDTO> supRes = resourceMapper.findSupRes();
//        if (A.isEmpty(supRes)) {
//            return A.sets();
//        }
//        List<SysResourceDTO> resourceDTOS = recursionGetRes(supRes, supRes, roleId, false);
//        Set<String> set = new HashSet<>();
//        getIdsByResources(set, resourceDTOS);
        return roleResMapper.findRoleResByRoleId(roleId);
    }


    @Override
    public List<SysMenu> GetResourceTreeHasSelect(@RequestParam String roleId) {
        List<String> res = roleResMapper.findRoleResByRoleId(roleId);
        if (A.isEmpty(res))
            return A.lists();
        List<SysMenu> supMenus = resourceMapper.findSupMenu();
        if (A.isEmpty(supMenus)) {
            return A.lists();
        }
        return recursionGetMenu(supMenus, supMenus, roleId, false, false);
    }

    /**
     * 角色授权
     *
     * @param detailDTO
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean authorize(@RequestBody SysAuthorDetailDTO detailDTO) {
        Assert.checkNull(detailDTO, "请至少选择一个菜单资源！");
        Assert.checkNull(detailDTO.getRoleId(), "角色不能为空！");
        SysRole role = roleMapper.selectByPrimaryKey(detailDTO.getRoleId());
        Assert.checkNull(role, "系统中不存在该角色！");
        List<SysAuthorDetailDTO.SysResDetail> details = detailDTO.getDetails();
        if (A.isNotEmpty(details)) {
            roleResMapper.deleteRoleResByRoleId(detailDTO.getRoleId());
            int fail = 0;
            int total = details.size();
            for (SysAuthorDetailDTO.SysResDetail d : details) {
                SysResource sysMenu = resourceMapper.selectByPrimaryKey(d.getResId());
                if (sysMenu == null) {
                    LogUtil.LOG.warn("=================================【角色授权】业务：菜单resId:{" + d.getResId() + "}在系统中不存在!");
                    fail++;
                    continue;
                }
                SysRoleRes roleRes = new SysRoleRes();
                roleRes.setRoleId(detailDTO.getRoleId());
                roleRes.setResId(d.getResId());
                roleRes.setRightId(U.less0(d.getRightId()) ? Permission.OPERATION.getCode() : d.getRightId());
                roleResMapper.insertSelective(roleRes);
            }
            Assert.checkIsTrue(fail == total, "菜单不存在,角色授权失败!");
            return false;
        }
        return true;
    }

    /**
     * 递归i调用
     *
     * @param supMenu   一级菜单集合
     * @param childMenu 子菜单集合
     * @param bizId     用户/角色ID
     * @param isUserId  是否为用户ID
     * @param isRight   查询菜单是否需要权限code
     */
    private List<SysMenu> recursionGetMenu(List<SysMenu> supMenu, List<SysMenu> childMenu, String bizId, boolean isUserId, boolean isRight) {
        if (A.isNotEmpty(childMenu)) {
            for (SysMenu menu : childMenu) {
                List<SysRoleRes> roleRes = findRightByBizIdAndResId(bizId, menu.getId(), isUserId);
                menu.setRightId(toRight(roleRes));
                List<SysMenu> child = null;
                if (isRight) {
                    child = resourceMapper.findRightMenuByUserIdAndSupId(bizId, menu.getId());
                } else {
                    child = resourceMapper.findMenuBySupId(menu.getId());
                }
                if (A.isNotEmpty(child)) {
                    menu.setChildrenRes(child);
                    supMenu = recursionGetMenu(supMenu, child, bizId, isUserId, isRight);
                }
            }
        }
        return supMenu;
    }

    @Override
    public SysResource findResById(String resId) {
        SysResource res = resourceMapper.selectByPrimaryKey(resId);
        if (res != null && U.isNotBlank(res.getMethod())) {
            String[] ms = res.getMethod().split(",");
            res.setMethods(Arrays.asList(ms));
        }
        return res;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveResource(@RequestBody SysResource res) {
        Assert.checkNull(res, "请求资源不能为空！");
//        Assert.checkIsTrue(A.isNotEmpty(resourceMapper.findResByName(res.getName())), "系统已经存在菜单名称,请勿重复添加!");
        Assert.checkIsTrue(A.isNotEmpty(resourceMapper.findResByCode(res.getCode())), "系统已经存在菜单业务编码,请勿重复添加!");
        res.setId(U.uuid());
        res.setCreateTime(new Date());
        if (U.isNotBlank(res.getSupId())) {
            SysResource supZr = resourceMapper.selectByPrimaryKey(res.getSupId());
            Assert.checkNull(supZr, "找不到{ ID: " + res.getSupId() + " }父节点菜单");
            res.setPath(supZr.getPath() + res.getId() + ".");
            res.setDepth(supZr.getDepth() + 1);
        } else {
            res.setPath(res.getId() + ".");
            res.setDepth(1);
        }
        if (resourceMapper.insertSelective(res) > 0)
            return true;
        return false;
    }

    /**
     * 更新资源
     *
     * @param res
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateResource(@RequestBody SysResource res) {
        Assert.checkNull(res, "请求资源不能为空！");
        res.setUpdateTime(new Date());
        if (U.isNotBlank(res.getSupId())) {
            SysResource supRes = resourceMapper.selectByPrimaryKey(res.getSupId());
            Assert.checkNull(supRes, "找不到{ ID: " + res.getSupId() + " }父节点菜单");
            res.setPath(supRes.getPath() + res.getId() + ".");
            res.setDepth(supRes.getDepth() + 1);
        } else {
            res.setPath(res.getId() + ".");
            res.setDepth(1);
        }
        if (resourceMapper.updateByPrimaryKeySelective(res) > 0)
            return true;
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteResource(String resId) {
        SysResource resource = resourceMapper.selectByPrimaryKey(resId);
        SysResourceExample example;
        List<String> ids;
        if (resource != null) {
            ids = A.lists(resId);
            example = new SysResourceExample();
            if (U.isBlank(resource.getSupId())) {
                example.or().andPathLike(U.rightLike(resource.getId()));
            } else {
                example.or().andSupIdEqualTo(resId);
            }
            List<SysResource> res = resourceMapper.selectByExample(example);
            for (SysResource r : res) {
                ids.add(r.getId());
            }
            example.clear();
            example.or().andIdIn(ids);
            if (resourceMapper.deleteByExample(example) > 0) {
                SysRoleResExample se = new SysRoleResExample();
                se.or().andResIdIn(ids);
                roleResMapper.deleteByExample(se);
                return true;
            }
        }
        return false;
    }

    /**
     * 批量删除资源，级联删除子资源
     *
     * @param resIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean batchDeleteResource(@RequestBody String[] resIds) {
        Assert.checkIsTrue(A.isEmpty(resIds), "请至少选择一个资源菜单！");
        for (String resId : resIds) {
            SysResource resource = resourceMapper.selectByPrimaryKey(resId);
            SysResourceExample example;
            List<String> ids;
            if (resource != null) {
                ids = A.lists(resId);
                example = new SysResourceExample();
                if (U.isBlank(resource.getSupId())) {
                    example.or().andPathLike(U.rightLike(resource.getId()));
                } else {
                    example.or().andSupIdEqualTo(resId);
                }
                List<SysResource> res = resourceMapper.selectByExample(example);
                for (SysResource r : res) {
                    ids.add(r.getId());
                }
                example.clear();
                example.or().andIdIn(ids);
                if (resourceMapper.deleteByExample(example) > 0) {
                    SysRoleResExample se = new SysRoleResExample();
                    se.or().andResIdIn(ids);
                    roleResMapper.deleteByExample(se);
                }
            }
        }
        return true;
    }

    @Override
    public List<Resource> caseResource(String userId) {
        if (U.isBlank(userId)) {
            return A.lists();
        }
        return null;
    }

    @Override
    public PageList findPageResByCondition(@RequestBody SysResource res) {
        List<String> resId = null;
        if (U.isNotBlank(res.getId())) {
            resId = roleResMapper.findRoleResByRoleId(res.getId());
        }
        if (A.isEmpty(resId))
            return defaultPageList();
        SysResourceExample example = new SysResourceExample();
        SysResourceExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(resId);
        Page<SysResource> page = (Page<SysResource>) resourceMapper.selectByExample(example);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    /**
     * 机构
     * =================================================================================================================
     * 分页，条件查询机构组织
     *
     * @param organization
     */
    @Override
    public PageList findPageOrgByCondition(@RequestBody SysOrganization organization) {
        SysOrganizationExample example = null;
        if (organization != null) {
            example = new SysOrganizationExample();
            SysOrganizationExample.Criteria criteria = example.or();
            if (U.isNotBlank(organization.getName())) {
                criteria.andNameLike(U.like(organization.getName()));
            }
            if (U.isNotBlank(organization.getCode())) {
                criteria.andCodeLike(U.like(organization.getCode()));
            }
            if (U.isNotBlank(organization.getState())) {
                criteria.andStateEqualTo(organization.getState());
            }
        }
        Page<SysOrganization> page = (Page<SysOrganization>) organizationMapper.selectByExample(example);
        return toPage(page.getTotal(), page.getPageNum(), page.getResult());
    }

    /**
     * 树型查询机构
     * 递归优化
     */
    @Override
    public List<SysOrganization> findOrgTreeList() {
        List<SysOrganization> supOrg = organizationMapper.selectSupOrg();
        if (A.isEmpty(supOrg)) {
            return A.lists();
        }
        return recursionGetOrg(supOrg, supOrg);
    }

    /**
     * 递归调用机构
     */
    private List<SysOrganization> recursionGetOrg(List<SysOrganization> supOrg, List<SysOrganization> childrenOrg) {
        if (A.isNotEmpty(childrenOrg)) {
            for (SysOrganization organization : childrenOrg) {
                List<SysOrganization> child = organizationMapper.selectChildrenOrgByParentId(organization.getOrgId());
                if (A.isNotEmpty(child)) {
                    organization.setChildOrgs(child);
                    supOrg = recursionGetOrg(supOrg, child);
                }
            }
        }
        return supOrg;
    }

    /**
     * 保存机构
     *
     * @param organization
     */
    @Override
    public boolean saveOrganization(@RequestBody SysOrganization organization) {
        Assert.checkNull(organization, "请输入参数!");
        Assert.checkNull(organization.getName(), "机构名称不能为空!");
        Assert.checkNoNull(A.first(organizationMapper.selectByOrgName(organization.getName())), "该机构名称已经存在!");
        Assert.checkNoNull(A.first(organizationMapper.selectByOrgCode(organization.getCode())), "该机构编码已经存在!");
        organization.setOrgId(U.uuid());
        organization.setCreateDate(new Date());
        if (organizationMapper.insertSelective(organization) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 更新机构
     *
     * @param organization
     */
    @Override
    public boolean updateOrganization(@RequestBody SysOrganization organization) {
        Assert.checkNull(organization.getOrgId(), "请输入机构ID!");
        SysOrganization org = organizationMapper.selectByPrimaryKey(organization.getOrgId());
        Assert.checkNull(org, "该机构信息不存在!");
        organization.setUpdateDate(new Date());
        if (organizationMapper.updateByPrimaryKey(organization) > 0) {
            return true;
        }
        return false;
    }

    /**
     * 删除机构
     *
     * @param orgId
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteOrganization(String orgId) {
        Assert.checkNull(orgId, "请至少选择一个机构！");
        SysOrganization organization = organizationMapper.selectByPrimaryKey(orgId);
        Assert.checkNull(organization, "该组织机构在系统中不存在!");
        if (organizationMapper.deleteByPrimaryKey(orgId) > 0) {
            userOrgMapper.deleteByOrgId(orgId);
            return true;
        }
        return false;
    }

    /**
     * 批量删除机构
     *
     * @param orgIds
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean batchDeleteOrganization(@RequestBody String[] orgIds) {
        Assert.checkIsTrue(A.isEmpty(orgIds), "请至少选择一个机构！");
        for (String orgId : orgIds) {
            organizationMapper.deleteByPrimaryKey(orgId);
            userOrgMapper.deleteByOrgId(orgId);
        }
        return true;
    }

}
