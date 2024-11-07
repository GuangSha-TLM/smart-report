package com.gsxy.core.pojo.vo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
public class FormUserVo implements Serializable {

    private Long id;
    /**
     * 接收人
     */
    private Long reserveId;
    private String reserveIdtEXT;
    private boolean isWrite; //是否填写

}
