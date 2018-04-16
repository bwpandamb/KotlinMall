package com.kotlin.base.injection

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.RUNTIME
import javax.inject.Scope
/**
 * Created by mac on 2018/4/14.
 */
@Scope
@Documented
@Retention(RUNTIME)
annotation class ActivityScope