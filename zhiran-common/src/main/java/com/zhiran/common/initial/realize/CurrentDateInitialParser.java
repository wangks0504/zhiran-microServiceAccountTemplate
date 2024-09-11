package com.zhiran.common.initial.realize;

import com.zhiran.common.initial.annotation.InitialParser;
import com.zhiran.common.initial.annotation.InitialResolver;

import java.util.Date;

public class CurrentDateInitialParser implements InitialParser {
//    判断需要赋予值的数据类型是不是日期类型
    @Override
    public boolean isMatch(Class clazz) {
        return clazz.isAssignableFrom(Date.class);
    }

    @Override
    public Object getDefaultValue(Class clazz, InitialResolver initialResolver) {
        return new Date();
    }
}
