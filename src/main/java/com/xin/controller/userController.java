package com.xin.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xin.common.Result;
import com.xin.entity.User;
import com.xin.mapper.UserMappr;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.PushbackReader;

/*
@data 2021/8/8 23:30
@PACKAGE_NAME com.xin.controller
*/
/*
ResponseBody,将前台传过来的json转化成java对象；
 */
@RestController
@RequestMapping("/user")
public class userController {

    @Resource
    UserMappr userMappr;

    @PostMapping
    public Result<?> save(@RequestBody User user){
        if(user.getPassword()==null){
            user.setPassword("123");
        }
        userMappr.insert(user);
        return Result.success();
    }


    //更新
    @PutMapping
    public Result<?> update(@RequestBody User user){
        userMappr.updateById(user);
        return Result.success();
    }

    /*
    pageNum,当前页；
    pageSize，每页多少条；
     */
    @GetMapping//这里用分页查询
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String search){
        LambdaQueryWrapper<User> wapper = Wrappers.<User>lambdaQuery();
        //search不为空时，执行like方法，因为如果search是空，like ,无法查询到NickName是空的数据；
        if(StrUtil.isNotBlank(search)){
            wapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMappr.selectPage(new Page<>(pageNum, pageSize),wapper);
        return Result.success(userPage);
    }

    @DeleteMapping("/{id}")
    public Result<?> delete(@PathVariable Long id){
        userMappr.deleteById(id);
        return Result.success();
    }

    @PostMapping("/login")
    public Result<?> login(@RequestBody User user){

        User res = userMappr
                .selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword()));
        if (res ==null){
            return Result.error("-1","用户名或密码错误");
        }
        return Result.success();
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody User user){

        User res = userMappr
                .selectOne(Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()));
        if (res !=null){
            return Result.error("-1","用户名已存在");
        }
        userMappr.insert(user);
        return Result.success();
    }

}
