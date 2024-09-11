package com.zhiran.common.utils;

import com.zhiran.common.constant.ZhiranConstants;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils  {
    /**
     * 获取请求 Power BY zhiran wangks0504
     */
    public static HttpServletRequest getRequest(){
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
       return   servletRequestAttributes.getRequest();
    }
    /**
     *获取请求头
     */
    public static String getRequestHeader (String headerName){
        HttpServletRequest request = getRequest();
        return request.getHeader(headerName);
    }
    /**
     * 获取当前用户信息
     */
    public static String getCurrentAccountID(){
        return getRequestHeader(ZhiranConstants.HEADER_ACCOUNT_KEY);
    }

}
