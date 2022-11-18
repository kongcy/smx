package com.xtxk.system.api.convert;

import com.xtxk.core.date.DateUtil;
import com.xtxk.system.api.model.*;


/**
 * @author kuangcy
 * @version 1.0
 * @ClassName ConvertSysUserToInfoUser
 * @create: 2022-03-07 15:46
 */
public class PlatformConvertUtil {

    //资源类型
    private final static String RESOURCE_TYPE = "User";


    //sip 格式
    private final static String SIP_NUM_FORMAT = "%07d";


    /**
     * 创建人员组织关联信息
     *
     * @param sysUser sysUser
     * @return InfoResource
     */
    public static InfoResource createInfoResource(SysUser sysUser) {
        InfoResource infoResource = new InfoResource();
        infoResource.setResourceName(sysUser.getUserName());//资源名称
        infoResource.setResourceType(RESOURCE_TYPE);
        infoResource.setCreateTime(DateUtil.now());
        return infoResource;
    }


    /**
     * 生成resourceSipId
     * 生成规则 node_num + 7位数（1~9999999） 一共11位
     *
     * @param num 当前最大编号
     * @return resourceSipId
     * @nodeNum 节点编码
     */
    public static String createResourceSipId(String nodeNum, int num) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(nodeNum);
        String sipId = String.format(SIP_NUM_FORMAT, num);
        stringBuffer.append(sipId);
        return stringBuffer.toString();
    }

}
