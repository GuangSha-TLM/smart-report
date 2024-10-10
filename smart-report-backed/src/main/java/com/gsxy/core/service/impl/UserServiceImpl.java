package com.gsxy.core.service.impl;

import com.gsxy.core.mapper.UserMapper;
import com.gsxy.core.pojo.bo.UserLoginBo;
import com.gsxy.core.pojo.bo.UserRegBo;
import com.gsxy.core.pojo.vo.ResponseVo;
import com.gsxy.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseVo userReg(UserRegBo userRegBo) {
        return null;
    }

    @Override
    public ResponseVo userLogin(UserLoginBo userLoginBo) {
        return null;
    }
}
