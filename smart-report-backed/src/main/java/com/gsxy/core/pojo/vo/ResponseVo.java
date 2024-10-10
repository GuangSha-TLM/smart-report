package com.gsxy.core.pojo.vo;

import lombok.*;
import org.springframework.http.HttpHeaders;

/**
 * 2023-10-23
 * 后端返回给前端的数据封装题
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ResponseVo<T> {

    private String message; //返回信息
    private HttpHeaders headers; //返回信息
    private T data;    //返回数据
    private String code;    //响应状态码
    private Long count;    //数组总数长度（用来返回分页数据总数）
}
