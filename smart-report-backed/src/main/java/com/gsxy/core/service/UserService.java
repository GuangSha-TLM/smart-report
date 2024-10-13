package com.gsxy.core.service;

import com.gsxy.core.pojo.bo.UserLoginBo;
import com.gsxy.core.pojo.bo.UserPageBo;
import com.gsxy.core.pojo.bo.UserRegBo;
import com.gsxy.core.pojo.bo.UserUpdateBo;
import com.gsxy.core.pojo.vo.ResponseVo;

import java.util.Map;

public interface UserService {

    ResponseVo userReg(UserRegBo userRegBo);

    ResponseVo userLogin(UserLoginBo userLoginBo);

    ResponseVo userSelectById(Long id);

    ResponseVo userDeleteById(Long id);

    ResponseVo suerSelectAll();

    ResponseVo userUpdate(UserUpdateBo userUpdateBo);

    ResponseVo userPageQuery(UserPageBo userPageBo);
}
