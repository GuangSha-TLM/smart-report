package com.gsxy.core.pojo.bo;

import lombok.*;

/**
 * @author luyiinn
 * @date 11/10/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FormUpdateBo {

    private Long id;    //表单id

    private String config;  //组件

}
