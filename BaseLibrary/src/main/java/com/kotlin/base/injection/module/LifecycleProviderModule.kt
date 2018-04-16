package com.kotlin.base.injection.module

import com.kotlin.base.injection.ActivityScope
import com.trello.rxlifecycle.LifecycleProvider
import dagger.Module
import dagger.Provides

/**
 * Created by mac on 2018/4/14.
 */
@Module
class LifecycleProviderModule(private val lifecycleProvider: LifecycleProvider<*>) {

    @ActivityScope
    @Provides
    fun providesLifecycleProvider(): LifecycleProvider<*> {
        return lifecycleProvider
    }
}