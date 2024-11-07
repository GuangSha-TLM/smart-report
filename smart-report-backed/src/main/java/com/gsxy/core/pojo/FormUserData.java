package com.gsxy.core.pojo;

import lombok.*;

import java.io.Serializable;

/**
 * (FormUserData)实体类
 *
 * @author makejava
 * @since 2024-11-07 15:13:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FormUserData implements Serializable {
    private static final long serialVersionUID = 446515823219791734L;

    private Long id;
/**
     * 关联表单id
     */
    private Long formId;
/**
     * 创建人
     */
    private Long createdBy;
/**
     * 用户填写信息
     */
    private String formData;
/**
     * 逻辑删除（0：存在，1：删除）
     */
    private Integer delFlag;

}

