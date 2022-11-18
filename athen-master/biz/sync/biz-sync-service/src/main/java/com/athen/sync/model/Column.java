package com.athen.sync.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenying
 * @date 2022/11/7 1:23 PM
 * @time 1:23 PM
 * @since 1.0.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Column implements Serializable {
    private String columnName;// 列名
    private String columnType;// 类型
    private Integer dataLength; // 列的数据类型的字节长度
    private Integer dataPrecision;//数字类型的实际长度
    private Integer dataScale; // 小数位
    private String nullAble;//是否可以为空，Y或N
    private String comments;// 列注释
    private String primaryKey;//主键列
}
