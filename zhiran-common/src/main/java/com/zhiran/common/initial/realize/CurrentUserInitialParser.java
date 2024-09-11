package com.zhiran.common.initial.realize;

import com.zhiran.common.constant.ZhiranConstants;
import com.zhiran.common.initial.annotation.InitialParser;
import com.zhiran.common.initial.annotation.InitialResolver;
import com.zhiran.common.utils.RequestUtils;

public class CurrentUserInitialParser implements InitialParser {
    @Override
    public boolean isMatch(Class clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    @Override
    public Object getDefaultValue(Class clazz, InitialResolver initialResolver) {
        return RequestUtils.getRequestHeader(ZhiranConstants.HEADER_ACCOUNT_KEY);
    }
}
