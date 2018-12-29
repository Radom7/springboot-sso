package com.haiyu.controller;

import com.alibaba.fastjson.JSON;
import com.haiyu.common.respose.ResultData;
import com.haiyu.common.util.HttpUtil;
import com.haiyu.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: IndexController
 * @Description:
 * @author: youqing
 * @version: 1.0
 * @date: 2018/12/29 9:09
 */
@Controller
@Slf4j
public class IndexController {

    /**
     *
     * 功能描述: 进入首页
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:54
     */
    @GetMapping("/index")
    public String index(){
        return "index";
    }


    /**
     *
     * 功能描述: 进入登录页
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:54
     */
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    /**
     *
     * 功能描述: 进行登入
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:55
     */
    @PostMapping("toLogin")
    @ResponseBody
    public ResultData toLogin(@RequestBody User user){
        ResultData resultData = new ResultData();
        Map<String, String> param = new HashMap<>();
        String result = null;
        param.put("loginName",user.getLoginName());
        param.put("password",user.getPassword());
        result = HttpUtil.doPost("http://sso-server.com:9090/sso/login",param);
        resultData =  JSON.parseObject(result,ResultData.class);
        return resultData;
    }


    /**
     *
     * 功能描述: 添加cookie
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:55
     */
    @GetMapping("/addCookie")
    public void addCookie (String token, HttpServletResponse response) {
        log.info("添加cookie");
        Cookie cookie = new Cookie("token",token);
        cookie.setPath("/");
        cookie.setMaxAge(3600);
        response.addCookie(cookie);
    }


    /**
     *
     * 功能描述: 清除cookie
     *
     * @param:
     * @return:
     * @auther: youqing
     * @date: 2018/12/29 14:55
     */
    @GetMapping("/clearCookie")
    public void clearCookie (HttpServletRequest request, HttpServletResponse response) {
        log.info("清除cookie");
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

}
