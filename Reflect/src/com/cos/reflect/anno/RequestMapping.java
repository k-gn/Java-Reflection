package com.cos.reflect.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD}) // 어노테이션 사용 위치
@Retention(RetentionPolicy.RUNTIME) // 어노테이션 동작 시점을 결정해야 한다.
public @interface RequestMapping {

	String value();
}
