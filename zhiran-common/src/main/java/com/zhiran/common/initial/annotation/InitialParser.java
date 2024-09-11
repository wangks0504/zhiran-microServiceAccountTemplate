package com.zhiran.common.initial.annotation;

public interface InitialParser {
    //判断是否配置相关类型
    boolean isMatch(Class clazz);
    //返回默认值
    Object getDefaultValue(Class clazz,InitialResolver initialResolver);
}
