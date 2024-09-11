package com.zhiran.common.initial.annotation;

import com.zhiran.common.enums.InitialResolverType;
import com.zhiran.common.group.Group;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.TYPE})
@Documented
public @interface InitialResolver {
    InitialResolverType resolver();
    Class<?>[] groups() default Group.All.class;

    String def() default "" ;

}
