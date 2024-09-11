package com.zhiran.common.enums;

import com.zhiran.common.initial.realize.CurrentDateInitialParser;
import com.zhiran.common.initial.realize.CurrentUserInitialParser;
import com.zhiran.common.initial.realize.SnowflakeIDInitialParser;

public enum InitialResolverType {
    /**
     * 获取当前时间
     */

    CURRENT_DATE("CURRENT_DATE",CurrentDateInitialParser.class),

    /**
     * 通过雪华算法计算ID
     */

    GEN_SNOWFLAKE_ID("GEN_SNOWFLAKE_ID",SnowflakeIDInitialParser.class),

    /**
     * 获取当前账户
     */
    CURRENT_ACCOUNT("CURRENT_ACCOUNT",CurrentUserInitialParser .class);

    InitialResolverType(String resolverName,Class resolverClass){
            this.resolverName = resolverName;
            this.resolverClass = resolverClass;
    }



    /**
     * 错误码
     */
    private String resolverName;
    public void setResolverName(String resolverName) {
        this.resolverName = resolverName;
    }
    public String getResolverName() {
        return resolverName;
    }



    /**
     * 具体错误信息
     */
    private Class resolverClass;
    public Class getResolverClass() {
        return resolverClass;
    }

    public void setResolverClass(Class resolverClass) {
        this.resolverClass = resolverClass;
    }

}
