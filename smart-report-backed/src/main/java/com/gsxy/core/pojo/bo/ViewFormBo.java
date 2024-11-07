package com.gsxy.core.pojo.bo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ViewFormBo implements Serializable {

    private Long page;
    private Long limit;

}
