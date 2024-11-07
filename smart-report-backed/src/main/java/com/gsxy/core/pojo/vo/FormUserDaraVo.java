package com.gsxy.core.pojo.vo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class FormUserDaraVo implements Serializable {

    private String formData;
    private String config;

}
