package com.gsxy.core.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserRegBo implements Serializable {

    @ApiModelProperty(value = "用户名", example = "用户名")
    private String username;
    @ApiModelProperty(value = "密码", example = "密码")
    private String password;
    @ApiModelProperty(value = "密码", example = "密码")
    private String againPassword;

}
