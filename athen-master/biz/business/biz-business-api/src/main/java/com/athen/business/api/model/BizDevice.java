package com.athen.business.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xtxk.core.date.DateUtil;
import com.xtxk.core.util.U;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel(value = "工具设备")
public class BizDevice implements Serializable {
    /**
     * 机构id
     */
    @ApiModelProperty(value = "主键id",hidden = true)
    private String id;
    /**
     * rfid标签号
     */
    @ApiModelProperty(value = "rfid标签号")
    private String rfid;
    /**
     * 工具编号
     */
    @ApiModelProperty(value = "工具编号")
    private String deviceCode;
    /**
     * 物料号
     */
    @ApiModelProperty(value = "物料号")
    private String wlh;
    /**
     * 工具名称
     */
    @ApiModelProperty(value = "工具名称")
    private String deviceName;
    /**
     * 设备类型
     */
    @ApiModelProperty(value = "设备类型")
    private String type;
    /**
     * 检定周期
     */
    @ApiModelProperty(value = "检定周期")
    private Integer checkTime=0;
    /**
     * 序号管理
     */
    @ApiModelProperty(value = "序号管理")
    private String seq;
    /**
     * 库位号
     */
    @ApiModelProperty(value = "库位号",hidden = true)
    private String ckNo="";
    /**
     * 工具类别
     */
    @ApiModelProperty(value = "工具类别")
    private String deviceType;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date createTime;
    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人",hidden = true)
    private String createUser;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间",hidden = true)
    private Date updateTime;
    /**
     * 更新人
     */
    @ApiModelProperty(value = "更新人",hidden = true)
    private String updateUser;
    /**
     * 总计数量
     */
    @ApiModelProperty(value = "总计数量")
    private Integer total;
    /**
     * 可用数量
     */
    @ApiModelProperty(value = "可用数量")
    private Integer userCount;
    /**
     * 借出数量
     */
    @ApiModelProperty(value = "借出数量")
    private Integer lendCount;
    /**
     * 禁用数量
     */
    @ApiModelProperty(value = "禁用数量")
    private Integer disableCount;
    /**
     * 限用数量
     */
    @ApiModelProperty(value = "限用数量")
    private Integer limitCount;
    /**
     * 自主超市
     */
    @ApiModelProperty(value = "自主超市")
    private String zhcs;
    /**
     * 智能柜
     */
    @ApiModelProperty(value = "智能柜")
    private String zng;
    /**
     * 厂房
     */
    @ApiModelProperty(value = "厂房")
    private String cf;
    /**
     * 房间
     */
    @ApiModelProperty(value = "房间")
    private String fj;
    /**
     * 货架
     */
    @ApiModelProperty(value = "货架")
    private String hj;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String xh;
    /**
     * 层数
     */
    @ApiModelProperty(value = "层数")
    private String cs;
    /**
     * 检查开始时间
     * */
    @ApiModelProperty(value = "检查开始时间",hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkBeginTime;
    /**
     * 检查结束时间
     * */
    @ApiModelProperty(value = "检查结束时间")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date checkEndTime;
    /**
     * 借用人
     * */
    @ApiModelProperty(value = "借用人",hidden = true)
    private String userName="";
    /**
     * 借用时间
     * */
    @ApiModelProperty(value = "借用时间",hidden = true)
    private String lendTime;
    /**
     * 有效天数
     * */
    @ApiModelProperty(value = "有效天数",hidden = true)
    private int validDay;
    private static final long serialVersionUID = 1L;

    public String getLendTime() {
        return U.isNotBlank(this.createTime)? DateUtil.formatFull(this.createTime):U.EMPTY;
    }

    public int getValidDay() {
        if (U.isNotBlank(this.checkEndTime)) {
            if (new Date().before(this.checkEndTime)) {
                return DateUtil.daysBetween(new Date(), this.checkEndTime);
            }
        }
        return validDay;
    }
}