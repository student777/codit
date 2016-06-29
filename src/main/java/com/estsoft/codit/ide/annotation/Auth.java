package com.estsoft.codit.ide.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/*
 세션이 부여된 사람만 접근 가능(로그인 제한 같은거)
 */
@Target(ElementType.METHOD)
@Retention( RetentionPolicy.RUNTIME)
public @interface Auth {}

