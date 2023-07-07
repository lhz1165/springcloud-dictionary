package com.whq.springtest_client.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lhz.entity.Result;
import com.lhz.util.ResultUtil;
import com.whq.springtest_client.entity.User;
import com.whq.springtest_client.mapper.UserMapper;
import com.whq.springtest_client.util.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        String userName = user.getUserName();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userName);
        User user1 = userMapper.selectOne(wrapper);
        if (user1 == null || !user1.getPassWord().equals(user.getPassWord())) {
            return ResultUtil.error(500,"用户名或者密码错误");
        }
        String tonken = JwtUtils.generateToken("111", user1.getId());
        return ResultUtil.success(tonken);
    }

    @PostMapping("/getUser")
    public Result getUser(HttpServletRequest request) {
        String token = request.getHeader("token"); // 从 http 请求头中取出 token
        Claims claims = JwtUtils.verifyJwt(token);
        if (claims == null) {
            ResultUtil.error(400, "未登录");
        }
        String subject = claims.getSubject();
        Integer userId = (Integer)claims.get("userId");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        User user1 = userMapper.selectById(userId);
        return ResultUtil.success(user1);

    }

    @PostMapping("/logout")
    public Result logout(HttpServletRequest request) {
        return ResultUtil.success();
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        String userName = user.getUserName();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",userName);
        User user1 = userMapper.selectOne(wrapper);
        if (user1 != null) {
            return ResultUtil.error(500,"用户名已注册");
        }
        User res = new User();
        res.setUserName(user.getUserName());
        res.setPassWord(user.getPassWord());
        userMapper.insert(res);
        return ResultUtil.success();
    }


}
