package com.gsxy.core.pojo;

import lombok.*;

/**
 * 表单实体
 * @author luyiinn
 * @date 11/10/2024
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Form {

    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 组件
     */
    private String config;

    /**
     * 逻辑删除(0:存在  1:删除)
     */
    private Integer delFlag;

}
