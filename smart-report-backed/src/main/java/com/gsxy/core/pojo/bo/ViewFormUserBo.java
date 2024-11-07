package com.gsxy.core.pojo.bo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ViewFormUserBo implements Serializable {

    private Long page;//页码
    private Long limit;//分页展示数量
    private Long formId;//分页展示数量

}
