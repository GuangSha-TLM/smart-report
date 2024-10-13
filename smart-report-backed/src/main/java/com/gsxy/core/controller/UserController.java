package com.gsxy.core.controller;

import com.alibaba.fastjson2.JSONArray;
import com.gsxy.core.pojo.bo.UserLoginBo;
import com.gsxy.core.pojo.bo.UserPageBo;
import com.gsxy.core.pojo.bo.UserRegBo;
import com.gsxy.core.pojo.bo.UserUpdateBo;
import com.gsxy.core.pojo.enums.CodeValues;
import com.gsxy.core.pojo.enums.MessageValues;
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
     * @author xcp 2024-10-12
     *      用户注册
     * @param userRegBo
     * @return
     */
    @PostMapping("/userReg")
    @ApiOperation("用户注册")
    public String userReg(@RequestBody UserRegBo userRegBo){
        if(userRegBo == null){
            JSONArray.toJSONString(
                    ResponseVo.builder()
                            .code(PARAMETER_ERROR)
                            .message(PARAMETER_MESSAGE)
                            .data(null)
                            .build()
            );
        }
        ResponseVo responseVo = userService.userReg(userRegBo);
        return JSONArray.toJSONString(responseVo);
    }

    /**
     * @author xcp 2024-10-12
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
//        ResponseVo responseVo = userService.userLogin(userLoginBo);
        return JSONArray.toJSONString(userService.userLogin(userLoginBo));
    }

    /**
     * @author xcp 2024-10-12
     *      根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("根据id查询用户")
    public String userSelectById(@PathVariable("id") Long id){
      ResponseVo responseVo =  userService.userSelectById(id);
      return JSONArray.toJSONString(responseVo);
    }


    /**
     * @author xcp 2024-10-12
     *      根据id删除用户
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("根据id删除用户")
    public String userDeleteById(@PathVariable("id") Long id){
        ResponseVo responseVo =  userService.userDeleteById(id);
        return JSONArray.toJSONString(responseVo);
    }

    /**
     * @author xcp 2024-10-12
     *      查询全部用户
     * @param
     * @return
     */
    @GetMapping("list")
    @ApiOperation("查询全部用户")
    public String userSelectAll(){
        ResponseVo responseVo=userService.suerSelectAll();
        return JSONArray.toJSONString(responseVo);
    }


    /**
     * @author xcp 2024-10-12
     *      修改用户信息
     * @param
     * @return
     */
    @PutMapping
    @ApiOperation("修改用户信息")
    public String userUpdate(@RequestBody UserUpdateBo userUpdateBo){
        if(userUpdateBo == null){
            return JSONArray.toJSONString(ResponseVo.builder()
                    .code(CodeValues.ERROR_CODE)
                    .message(PARAMETER_MESSAGE)
                    .build());
        }

        ResponseVo responseVo=userService.userUpdate(userUpdateBo);
        return JSONArray.toJSONString(responseVo);

    }


    /**
     * @author xcp 2024-10-12
     *      用户信息分页查询
     * @param
     * @return
     */
    @PostMapping("page")
    @ApiOperation("用户信息分页查询")
    public String userSelectPage(@RequestBody UserPageBo userPageBo){
        if(userPageBo == null){
            return JSONArray.toJSONString(
                    ResponseVo.builder()
                            .code(CodeValues.ERROR_CODE)
                            .message(PARAMETER_MESSAGE)
                            .build()
            );
        }

        ResponseVo responseVo=userService.userPageQuery(userPageBo);
        return JSONArray.toJSONString(responseVo);
    }





}
