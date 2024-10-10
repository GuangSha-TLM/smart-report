package com.gsxy.core.intercept;

import com.alibaba.fastjson2.JSONArray;
import com.gsxy.core.util.JwtUtil;
import com.gsxy.core.util.LoginUtils;
import com.gsxy.core.util.ThreadLocalUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationInterceptor implements HandlerInterceptor {

    /**
     * request前置环绕
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 在请求处理之前设置响应的字符集为 UTF-8
//        System.out.println("开始");
//        System.err.println(response.getCharacterEncoding());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain;charset=UTF-8");
//        System.err.println(response.getCharacterEncoding()+"？？？");
//
//
//        System.err.println("hello");
        StringBuffer requestURL = request.getRequestURL();
//        System.err.println(requestURL);

        //白名单
        if (
                requestURL.toString().endsWith("/user/login") ||
                requestURL.toString().endsWith("/user/userReg") ||
                requestURL.toString().endsWith("/carouselFile/findByCarouselId")
        ) {
            return true;
        }

        String authorizationHeader = null;

        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 401);

        String errorMessage = JSONArray.toJSONString(map);
        try {
            authorizationHeader = request.getHeader("Authorization");
        } catch (Exception e) {

            sendErrorResponse(response, errorMessage, HttpStatus.UNAUTHORIZED);
        }

        try {
            Map<String, Object> userInfo = JwtUtil.analysis(authorizationHeader);

            if (userInfo == null) {

                sendErrorResponse(response, errorMessage, HttpStatus.UNAUTHORIZED);
            }
            ThreadLocalUtil.mapThreadLocalOfJWT.get().put("userInfo", userInfo);
        } catch (Exception e) {

            sendErrorResponse(response, errorMessage, HttpStatus.UNAUTHORIZED);
        }
        response.getHeaderNames();
        response.getCharacterEncoding();

        //终止拦截器
        return true;
    }


    private void sendErrorResponse(HttpServletResponse response, String errorMessage, HttpStatus status) throws IOException, IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        response.getOutputStream().write(errorMessage.getBytes("UTF-8"));
        response.getOutputStream().flush();
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        //响应后调用

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 在请求完成之后调用，可以进行最终的清理工作
        ThreadLocalUtil.mapThreadLocalOfJWT.remove();
    }
}
