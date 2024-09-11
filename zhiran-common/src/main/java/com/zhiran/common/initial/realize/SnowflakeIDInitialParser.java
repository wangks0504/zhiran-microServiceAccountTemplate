package com.zhiran.common.initial.realize;

import com.zhiran.common.initial.annotation.InitialParser;
import com.zhiran.common.initial.annotation.InitialResolver;
import com.zhiran.common.utils.SnowflakeIdWorker;

public class SnowflakeIDInitialParser implements InitialParser {
    SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(13,14);
    @Override
    public boolean isMatch(Class clazz) {
        return clazz.isAssignableFrom(String.class);
    }

    @Override
    public Object getDefaultValue(Class clazz, InitialResolver initialResolver) {
        return String.valueOf(snowflakeIdWorker.nextId());
    }
}
