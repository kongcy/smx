package com.xtxk.system.api.DTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenying
 * @date 2022/11/3 3:22 PM
 * @time 3:22 PM
 * @since 1.0.0
 **/
@Data
@ApiModel(value = "工具柜上传用户信息")
public class UserInfo implements Serializable {
    @ApiModelProperty(value = "柜子编号，唯一值，默认设备mac地址")
    private String netcode;
    @ApiModelProperty(value = "来源")
    private String source;
    @ApiModelProperty(value = "当前发送请求时间的时间戳")
    private String timestamp;
    @ApiModelProperty(value = "所有参数按照字典大小排序后加上key然后md5取得的签名。")
    private String signstr;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "电话")
    private String phone;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "身份：0管理员 1普通用户")
    private String role;
    @ApiModelProperty(value = "人脸图片")
    private String base64_photo;
    @ApiModelProperty(value = "上传图片")
    private String file_photo;
    @ApiModelProperty(value = "索引【可不填】不填则自动生成（36位带-的UUI）")
    private String uuid;
    @ApiModelProperty(value = "工号")
    private String ucid;
    @ApiModelProperty(value = "用户操作柜子权限1,2,3,4")
    private String cabin_limits;
    @ApiModelProperty(value = "用户登录权限禁用开始时间")
    private String disstart_datatime;
    @ApiModelProperty(value = "用户登录权限禁用结束时间")
    private String disendt_datatime;
    @ApiModelProperty(value = "公司")
    private String company;
}
