package com.gsxy.core.pojo.vo;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserVo {
    //ID
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //权限
    private Long role;
    //创建时间
    private Date createdTime;
    // 修改人
    private Date updatedBy;
    // 修改时间
    private Date updatedTime;


}
