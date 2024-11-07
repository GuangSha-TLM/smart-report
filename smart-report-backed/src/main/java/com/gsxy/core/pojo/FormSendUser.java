package com.gsxy.core.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 发送表单给用户(FormSendUser)实体类
 *
 * @author makejava
 * @since 2024-11-07 17:40:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FormSendUser implements Serializable {
    private static final long serialVersionUID = -48009263785119618L;

    private Long id;
/**
     * 关联表单id
     */
    private Long formId;
/**
     * 接收人
     */
    private Long reserveId;
/**
     * 创建时间
     */
    private Date createdTime;
/**
     * 创建人
     */
    private Long createdBy;
/**
     * 逻辑删除（0：存在，1：删除）
     */
    private Integer delFlag;


}

