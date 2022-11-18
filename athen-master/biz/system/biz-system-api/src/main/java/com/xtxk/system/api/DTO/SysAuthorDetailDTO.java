package com.xtxk.system.api.DTO;

import com.xtxk.core.util.A;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/3/2
 */
@ApiModel(value = "授权菜单")
@Data
@Builder
public class SysAuthorDetailDTO implements Serializable {
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "权限资源集合")
    private List<SysResDetail> details = A.lists();

    @Data
    @ApiModel(value = "权限资源")
    public static class SysResDetail implements Serializable {
        @ApiModelProperty(value = "菜单id")
        private String resId;
        @ApiModelProperty(value = "权限code()")
        private int rightId;
    }
}

