package com.xin.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/*
@data 2021/8/8 23:32
@PACKAGE_NAME com.xin.entity
*/

@TableName("user")//与数据库表名一一对应
@Data
public class User {
    @TableId(type = IdType.AUTO)//表示id是自增；
    private Integer id;
    private String username;
    private String password;
    private String nickName;
    private Integer age;
    private String sex;
    private String address;
}
