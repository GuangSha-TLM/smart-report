package com.gsxy.core.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal 工具类
 */
public class ThreadLocalUtil {
    /**
     * @author bryan yang 2023-10-12
     * 用户鉴权ThreadLocal
     */
    public static ThreadLocal<Map<String,Map<String,Object>>> mapThreadLocalOfJWT
            = ThreadLocal.withInitial(()-> new HashMap<String,Map<String,Object>>());

    /**
     * @author bryan yang 2023-10-12
     *  Aop切面数据共享，如 aop切面发现出现问题，把问题返回给前端
     */
    public static ThreadLocal<Map<String, String>> mapThreadLocal = ThreadLocal.withInitial(()->new HashMap<String, String>());

    /**
     * @author bryan yang 2023-10-12
     * 线程内共享数据
     */
    public static ThreadLocal<Map<String, Object>> DataOfThreadLocal = ThreadLocal.withInitial(()->new HashMap<String, Object>());
}
