package com.annotation;

import java.lang.annotation.*;

/**
 * Created by wm on 2017/8/15.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect {
    Class<? extends Annotation> value();
}
