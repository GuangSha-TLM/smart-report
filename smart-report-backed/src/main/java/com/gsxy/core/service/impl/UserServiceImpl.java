package com.gsxy.core.service.impl;

import com.gsxy.core.mapper.UserMapper;
import com.gsxy.core.pojo.User;
import com.gsxy.core.pojo.bo.UserLoginBo;
import com.gsxy.core.pojo.bo.UserPageBo;
import com.gsxy.core.pojo.bo.UserRegBo;
import com.gsxy.core.pojo.bo.UserUpdateBo;
import com.gsxy.core.pojo.enums.CodeValues;
import com.gsxy.core.pojo.enums.MessageValues;
import com.gsxy.core.pojo.vo.ResponseVo;
import com.gsxy.core.pojo.vo.UserVo;
import com.gsxy.core.service.UserService;
import com.gsxy.core.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;



    @Override
    public ResponseVo userReg(UserRegBo userRegBo) {
        log.info("注册信息:{}", userRegBo);
        User user = userMapper.selectByUsername(userRegBo.getUsername());
        if (user != null) {
            return ResponseVo.builder()
                    .code(CodeValues.ERROR_CODE)
                    .message("用户名已存在")
                    .build();
        }

        userMapper.insert(User.builder()
                        .password(userRegBo.getPassword())
                        .username(userRegBo.getUsername())
                        .createdTime(new Date())
                        .build());

        return ResponseVo.builder()
                .code(CodeValues.SUCCESS_CODE)
                .message(MessageValues.SUCCESS_MESSAGE)
                .build();
    }



    @Override
    public ResponseVo userLogin(UserLoginBo userLoginBo) {
        log.info("登录信息:{}", userLoginBo);
        User user = userMapper.selectByUsername(userLoginBo.getUsername());
        if (user == null) {
            return ResponseVo.builder()
                    .code(CodeValues.ERROR_CODE)
                    .message("用户名不存在")
                    .build();
        }
        if (userLoginBo.getPasswd().equals(user.getPassword())) {
            String jwt = JwtUtil.createJWT(user);
            return ResponseVo.builder()
                    .code(CodeValues.SUCCESS_CODE)
                    .message(MessageValues.SUCCESS_MESSAGE)
                    .role(user.getRole())
                    .data(jwt)
                    .build();
        }
        return ResponseVo.builder()
                .code(CodeValues.ERROR_CODE)
                .message("密码错误")
                .build();
    }


    @Override
    public ResponseVo userSelectById(Long id) {
      UserVo userVo =  userMapper.selectById(id);
//      if (user == null) {
//          return ResponseVo.builder()
//                  .code(CodeValues.ERROR_CODE)
//                  .message("查询id不存在")
//                  .build();
//      }
      userVo.setPassword(null);

      return ResponseVo.builder()
              .code(CodeValues.SUCCESS_CODE)
              .message(MessageValues.SUCCESS_MESSAGE)
              .data(userVo)
              .build();
    }

    @Override
    public ResponseVo userDeleteById(Long id) {
        userMapper.DeleteByid(id);

        return ResponseVo.builder()
                .code(CodeValues.SUCCESS_CODE)
                .message(MessageValues.SUCCESS_MESSAGE)
                .build();
    }

    @Override
    public ResponseVo suerSelectAll() {
      List<UserVo> userList = userMapper.selectAll();
      if (userList==null){
          return ResponseVo.builder()
                  .code(CodeValues.ERROR_CODE)
                  .message("当前还没有用户")
                  .build();
      }

      return ResponseVo.builder()
              .code(CodeValues.SUCCESS_CODE)
              .message(MessageValues.SUCCESS_MESSAGE)
              .data(userList)
              .build();
    }

    @Override
    public ResponseVo userUpdate(UserUpdateBo userUpdateBo) {
        //校验用户权限是否正确
        if (userUpdateBo.getRole()<0||userUpdateBo.getRole()>2){
            return ResponseVo.builder()
                    .code(CodeValues.SUCCESS_CODE)
                    .message("用户权限仅有0,1,2")
                    .build();
        }
        UserVo userVo = userMapper.selectById(userUpdateBo.getId());
        if (userVo==null){
            return ResponseVo.builder()
                    .code(CodeValues.SUCCESS_CODE)
                    .message("用户不存在")
                    .build();
        }
        if (userUpdateBo.getPasswd()!=null && !"".equals(userUpdateBo.getPasswd())){
            //校验密码是否为原密码
            if (userUpdateBo.getPasswd().equals(userVo.getPassword())) {
                return ResponseVo.builder()
                        .code(CodeValues.SUCCESS_CODE)
                        .message("修改密码是原密码")
                        .build();
            }
        }


//        userUpdateBo.setUpdatedTime(new Date());
        userMapper.Update(userUpdateBo);

        return ResponseVo.builder()
                .code(CodeValues.SUCCESS_CODE)
                .message(MessageValues.SUCCESS_MESSAGE)
                .build();
    }


    @Override
    public ResponseVo userPageQuery(UserPageBo userPageBo) {
        int page=(userPageBo.getPage()-1)*userPageBo.getPageSize();
        userPageBo.setPage(page);

        System.out.println(userPageBo);
        List<UserVo> userVoList=userMapper.selectPageQuery(userPageBo);


        return ResponseVo.builder()
                .message(MessageValues.SUCCESS_MESSAGE)
                .data(userVoList)
                .code(CodeValues.SUCCESS_CODE)
                .build();
    }


}
