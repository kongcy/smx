package com.xtxk.system.repository;

import com.xtxk.core.annotation.Page;
import com.xtxk.core.vo.SysMenu;
import com.xtxk.system.api.DTO.SysResourceDTO;
import com.xtxk.system.api.model.SysResource;
import com.xtxk.system.api.model.SysResourceExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysResourceMapper {
    long countByExample(SysResourceExample example);

    int deleteByExample(SysResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysResource record);

    int insertSelective(SysResource record);
    @Page
    List<SysResource> selectByExample(SysResourceExample example);

    SysResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByExample(@Param("record") SysResource record, @Param("example") SysResourceExample example);

    int updateByPrimaryKeySelective(SysResource record);

    int updateByPrimaryKey(SysResource record);

    List<SysResource> findResByInUserId(String userId);

    /**
     * 通过名称查询菜单
     */
    List<SysResource> findResByName(String name);

    /**
     * 通过编码查询
     */
    List<SysResource> findResByCode(String code);

    /**
     * 查找一级节点
     */
    List<SysResourceDTO> findResBySupId(String supId);

    /**
     * 查找一级节点
     */
    List<SysResourceDTO> findSupRes();

    /**
     * 查找子菜单
     */
    List<SysMenu> findMenuBySupId(String supId);

    /**
     * 查找一级节点
     */
    List<SysMenu> findSupMenu();

    /**
     * 查找用户有权限的一级菜单
     */
    List<SysMenu> findRightMenuByUserId(String userId);

    /**
     * 查找有权限的子菜单
     */
    List<SysMenu> findRightMenuByUserIdAndSupId(@Param("userId") String userId, @Param("supId") String supId);

    /**
     * 查询所有菜单列表
     */
    List<SysMenu> findMenus();

}