package com.zhiran.common.initial.annotation;

import com.zhiran.common.group.Group;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface RequestInitial {
    Class<? > [] group() default Group.All.class;
}
