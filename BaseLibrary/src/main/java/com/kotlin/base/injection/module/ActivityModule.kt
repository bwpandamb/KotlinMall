package com.kotlin.base.injection.module

import android.app.Activity
import com.kotlin.base.injection.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * Created by mac on 2018/4/14.
 */
@Module
class ActivityModule(private val activity: Activity) {

    @ActivityScope
    @Provides
    fun providesActivity(): Activity {
        return activity
    }
}