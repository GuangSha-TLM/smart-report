package com.gsxy.core.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.*;
import java.util.Date;
import java.util.Enumeration;

@Slf4j
public class LoginUtils {

    /**
     * @author hln - 2024-8-26
     * 该方法用来获取当前登录用户的id
     * @return
     */
    public static Long getLoginUserId(){
        return Long.valueOf((String) ThreadLocalUtil.mapThreadLocalOfJWT.get().get("userInfo").get("id"));
    }

}
