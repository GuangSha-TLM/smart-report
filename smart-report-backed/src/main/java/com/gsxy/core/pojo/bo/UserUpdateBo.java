package com.gsxy.core.pojo.bo;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class UserUpdateBo {
    //ID
    private Long id;

    //密码
    private String passwd;
    //权限
    private Long role;

    // 修改人
    private Long updatedBy;

    // 修改时间
    private Date updatedTime;

    //逻辑删除
    private int delFlag;

}
