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
public class FormPageSBo {

    private Long userId;//用户id

    private int index;//起始索引

    private int pageSize;//分页展示数量

}
