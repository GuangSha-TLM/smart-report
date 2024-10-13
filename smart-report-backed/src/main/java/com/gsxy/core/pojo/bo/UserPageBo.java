package com.gsxy.core.pojo.bo;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class UserPageBo {
    private int page;//页码
    private int pageSize;//分页展示数量

}
