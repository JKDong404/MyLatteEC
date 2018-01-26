package com.mymxhbyf.dongjk.latte_annotations.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by DongJK on 2018/1/26.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface EntryGenerator {

    String packageName();

    Class<?> entryTempltete();
}
