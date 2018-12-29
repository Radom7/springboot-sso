package com.haiyu.controller;

import com.haiyu.common.respose.ResultData;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Title: LoginController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/12/28 16:52
 */
@RestController
@RequestMapping("sso")
public class LoginController {

    /**
     *
     * 功能描述: 登入系统
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:46
     */
    @PostMapping("login")
    public ResultData login(String loginName, String password){
        Map<String,Object> data = new HashMap<>();
        if(StringUtils.isEmpty(loginName)){
            return ResultData.fail("001","登录名为空！");
        }
        if(StringUtils.isEmpty(password)){
            return ResultData.fail("001","登录密码为空！");
        }
        if(loginName.equals("admin") && password.equals("123456")){
            data.put("loginName",loginName);
            data.put("token","123");
            List<String> urls = new ArrayList<>();
            urls.add("http://a.com:9091/addCookie");
            urls.add("http://b.com:9092/addCookie");
            data.put("urls",urls);
            return ResultData.success("1","登入成功！").with(data);
        }
        return ResultData.fail("001","登录名或登录密码错误！");
    }

    /**
     *
     * 功能描述: 通过token检验是否登入
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:46
     */
    @GetMapping("checkToken")
    public ResultData checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        Map<String,Object> data = new HashMap<>();
        if(StringUtils.isEmpty(token)){
            return ResultData.fail("003","token为空！");
        }
        if(token.equals("123")){
            data.put("loginName","admin");
            return ResultData.success("1","登入成功！").with(data);
        }
        return ResultData.fail("003","token失效！");
    }

    /**
     *
     * 功能描述: 退出系统
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:45
     */
    @GetMapping("logout")
    public ResultData logout(){
        Map<String,Object> data = new HashMap<>();
        List<String> urls = new ArrayList<>();
        //清除子系统的cookie
        urls.add("http://a.com:9091/clearCookie");
        urls.add("http://b.com:9092/clearCookie");
        data.put("urls",urls);
        return ResultData.success("1","退出成功！").with(data);
    }

}
