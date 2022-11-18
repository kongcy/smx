package com.xtxk.system.repository;

import com.xtxk.system.api.DTO.SysUserFaceDTO;
import com.xtxk.system.api.DTO.SysUserFaceImageDTO;
import com.xtxk.system.api.model.SysUserFaceInfo;
import com.xtxk.system.api.model.SysUserFaceInfoExample;
import com.xtxk.system.api.model.SysUserFaceInfoWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysUserFaceInfoMapper {
    long countByExample(SysUserFaceInfoExample example);

    int deleteByExample(SysUserFaceInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(SysUserFaceInfoWithBLOBs record);

    int insertSelective(SysUserFaceInfoWithBLOBs record);

    List<SysUserFaceInfoWithBLOBs> selectByExampleWithBLOBs(SysUserFaceInfoExample example);

    List<SysUserFaceInfo> selectByExample(SysUserFaceInfoExample example);

    SysUserFaceInfoWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") SysUserFaceInfoWithBLOBs record, @Param("example") SysUserFaceInfoExample example);

    int updateByExampleWithBLOBs(@Param("record") SysUserFaceInfoWithBLOBs record, @Param("example") SysUserFaceInfoExample example);

    int updateByExample(@Param("record") SysUserFaceInfo record, @Param("example") SysUserFaceInfoExample example);

    int updateByPrimaryKeySelective(SysUserFaceInfoWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SysUserFaceInfoWithBLOBs record);

    int updateByPrimaryKey(SysUserFaceInfo record);

    List<SysUserFaceDTO> findFaceInfo();

    List<SysUserFaceDTO> findFaceInfoByLoginName(@Param("loginName")String loginName);

    List<SysUserFaceImageDTO> findFaceFeature();

    int deleteUserFaceByUserId(@Param("userId")String userId);
}