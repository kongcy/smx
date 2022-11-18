package com.xtxk.system.api.model;

import lombok.Data;

import java.util.Date;

/**
 * @author kuangcy
 * @version 1.0
 * @ClassName InfoResource
 * @create: 2022-03-07 15:37
 */
@Data
public class InfoResource {
    //资源ID
    private String resourceId;

    //资源sipId
    private String resourceSipId;

    //资源名称
    private String resourceName;

    //资源类型
    private String resourceType;

    //环境信息
    private String factoryInfo;

    //组织ID
    private String directoryId;

    //节点ID
    private String nodeId;

    //创建时间

    private Date createTime;

}
