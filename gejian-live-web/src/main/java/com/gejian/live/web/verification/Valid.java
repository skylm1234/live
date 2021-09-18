package com.gejian.live.web.verification;

import java.lang.annotation.*;

/**
 * @author ：lijianghuai
 * @date ：2021-09-18 9:28
 * @description：
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Valid {

	ValidType[] type();

	int order() default 0;
}
