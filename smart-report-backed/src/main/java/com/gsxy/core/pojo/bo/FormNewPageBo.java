package com.gsxy.core.pojo.bo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FormNewPageBo implements Serializable {

    private String name;
    private Long page;
    private Long limit;

}
