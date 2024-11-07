package com.gsxy.core.pojo.vo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class FormSendUserVo implements Serializable {

    private String name;
    private String option;
    private String config;
    private Long formId;

}
