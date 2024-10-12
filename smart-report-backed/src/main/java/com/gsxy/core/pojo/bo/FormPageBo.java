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
public class FormPageBo {
    private int page;//页码
    private int pageSize;//分页展示数量

}
