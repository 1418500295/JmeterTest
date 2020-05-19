package com.course.controller;

import com.course.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("v1")
@Api(value = "v1")
@Slf4j
public class DaineApi {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @ApiOperation(value = "登录接口",httpMethod = "POST")
    @RequestMapping(value = "/v2/login",method = RequestMethod.POST)
    public Boolean getUserCount(HttpServletResponse response,@RequestBody User user){

        int i = sqlSessionTemplate.selectOne("login",user);
        Cookie cookie = new Cookie("login","true");
        response.addCookie(cookie);
        if (i == 1){
            log.info("查询到的结果是"+i);
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户接口",httpMethod = "POST")
    @RequestMapping(value = "/v2/addUser",method = RequestMethod.POST)
    public boolean addUser(HttpServletRequest request,@RequestBody User user){
        Boolean x = verfiyCookie(request);
        int result =0;
        if (x != null){
            result =  sqlSessionTemplate.insert("addUser",user);
        }
        if (result > 0){
            log.info("添加的用户数是"+result);
            return true;
        }
        return false;


    }
    @ApiOperation(value ="获取用户列表",httpMethod = "POST")
    @RequestMapping(value = "/getUserList",method = RequestMethod.POST)
    public List<User> getUserList(HttpServletRequest request,@RequestBody User user){
        Boolean x = verfiyCookie(request);
        if (x == true){
            List<User> users = sqlSessionTemplate.selectList("getUserList",user);
            log.info("查询到的用户数是"+users.size());
            return users;

        }else {
            return null;
        }

    }



    private Boolean verfiyCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            log.info("cookies为空");
            return false;

        }
        for (Cookie cookie :cookies){
            if (cookie.getName().equals("login") &&
            cookie.getValue().equals("true")){
                log.info("cookies验证通过");
                return true;
            }

        }
        return false;

    }


}
