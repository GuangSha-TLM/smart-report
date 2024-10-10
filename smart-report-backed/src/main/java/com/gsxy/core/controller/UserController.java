package com.gsxy.core.controller;

import com.alibaba.fastjson2.JSONArray;
import com.gsxy.core.pojo.bo.UserLoginBo;
import com.gsxy.core.pojo.bo.UserRegBo;
import com.gsxy.core.pojo.vo.ResponseVo;
import com.gsxy.core.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.gsxy.core.pojo.enums.CodeValues.PARAMETER_ERROR;
import static com.gsxy.core.pojo.enums.MessageValues.PARAMETER_MESSAGE;


@CrossOrigin
@Api(value = "用户板块接口",tags = {"用户板块接口"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @author hln 2024-8-24
     *      用户注册
     * @param userRegBo
     * @return
     */
    @PostMapping("/userReg")
    @ApiOperation("用户注册")
    public String userReg(@RequestBody UserRegBo userRegBo){
        return JSONArray.toJSONString(userService.userReg(userRegBo));
    }

    /**
     * @author hln 2024-8-24
     *      用户登录
     * @param userLoginBo
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public String userLogin(@RequestBody UserLoginBo userLoginBo){

        if(userLoginBo == null){
            JSONArray.toJSONString(
                    ResponseVo.builder()
                            .code(PARAMETER_ERROR)
                            .message(PARAMETER_MESSAGE)
                            .data(null)
                            .build()
            );
        }

        return JSONArray.toJSONString(userService.userLogin(userLoginBo));
    }

}
