package com.gsxy.core.pojo.bo;

import lombok.*;
import org.ehcache.xml.model.ConfigType;

import java.io.Serializable;
import java.util.List;

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
public class FormAddBo implements Serializable {

//    private List<Object> config;  //组件
    private String config;  //组件
    private String option;  //组件
    private String formName;  //表名

}
