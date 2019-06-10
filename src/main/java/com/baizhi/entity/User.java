package com.baizhi.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User implements Serializable {
    @Excel(name = "编号")
    private String id;
    @Excel(name = "电话号")
    private String phoneNum;
    @Excel(name = "密码")
    private String password;
    @ExcelIgnore
    private String salt;
    @Excel(name = "头像，type=2,width=40,height=20")
    private String headPic;
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "法号")
    private String dharma;
    @Excel(name = "性别")
    private String sex;
    @Excel(name = "省份")
    private String province;
    @ExcelIgnore
    private String city;
    @Excel(name = "签名")
    private String sign;
    @Excel(name = "状态")
    private String status;

    @JSONField(format = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建时间")
    private Date createDate;
    @ExcelIgnore
    private String guruId;


}