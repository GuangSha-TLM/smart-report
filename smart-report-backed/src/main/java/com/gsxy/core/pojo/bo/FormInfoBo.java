package com.gsxy.core.pojo.bo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FormInfoBo implements Serializable {

    private String formData;  //组件
    private Long formId;  //表单id

}
