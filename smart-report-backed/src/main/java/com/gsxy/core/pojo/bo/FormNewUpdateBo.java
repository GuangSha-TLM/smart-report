package com.gsxy.core.pojo.bo;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class FormNewUpdateBo implements Serializable {

    private String name;
    private Long id;

}
