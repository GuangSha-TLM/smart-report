package com.gsxy.core.pojo;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {
    //ID
    private Long id;
    //用户名
    private String username;
    //密码
    private String password;
    //权限
    private Integer role;
    //创建时间
    private Date createdTime;
   // 修改人
    private long updatedBy;
   // 修改时间
    private Date updatedTime;
    //逻辑删除
    private int delFlag;

    //学院id
    private  int schoolId;

}
