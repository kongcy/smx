package com.xtxk.core.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.xtxk.core.util.A;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description token信息
 * @Author cheny
 * @Version V3.0
 * @Since 1.0
 * @Date 2022/5/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo implements Serializable {
    private static final long serialVersionUID = -4528125970644580411L;
    /**
     * 验证token
     */
    private String token;
    /**
     * 登录名称
     */
    private String loginName;
    /**
     * 登录ip
     */
    private String loginIp;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 菜单集合
     */
    private List<SysMenu> menus= A.lists();

}
