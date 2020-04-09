package com.hcb.newBilly.controller;

import com.hcb.newBilly.common.Constants;
import com.hcb.newBilly.common.JwtUtils;
import com.hcb.newBilly.common.Result;
import com.hcb.newBilly.dto.ForgetPasswordDTO;
import com.hcb.newBilly.dto.LoginDTO;
import com.hcb.newBilly.dto.SaveUserDTO;
import com.hcb.newBilly.dto.UpdatePasswordDTO;
import com.hcb.newBilly.po.UserPO;
import com.hcb.newBilly.service.UserService;
import com.hcb.newBilly.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@Api("用户管理")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO login(@RequestBody LoginDTO loginDTO) {

        UserPO userPO = userService.getUserPOByUserAccount(loginDTO.getUserAccount());
        if (userPO == null) {
            return Result.err("用户不存在");
        }
        SimpleHash md5 = new SimpleHash("md5", loginDTO.getPassword());
        String hex = md5.toHex();
        if (!userPO.getPassword().equals(hex)) {
            return Result.err("密码错误");
        }
        String token = JwtUtils.getJwtToken(userPO.getUserAccount(), userPO.getUserId());
        Map<String, Object> map = new HashMap<>(2);
        map.put(Constants.AUTHORIZATION, token);
        map.put("user", userPO);
        return Result.ok(map);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ResultVO logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return Result.ok();
    }

    @ApiOperation("保存用户")
    @ResponseBody
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public ResultVO saveUser(@RequestBody SaveUserDTO saveUserDTO) {
        try {
            userService.saveUser(saveUserDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("更新密码")
    @ResponseBody
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public ResultVO updatePassword(@RequestBody UpdatePasswordDTO updatePasswordDTO) {
        try {
            userService.updatePassword(updatePasswordDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }

    @ApiOperation("忘记密码")
    @ResponseBody
    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    public ResultVO forgetPassword(@RequestBody ForgetPasswordDTO forgetPasswordDTO) {
        try {
            userService.forgetPasswordDTO(forgetPasswordDTO);
            return Result.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(e.getMessage());
        }
    }


}
