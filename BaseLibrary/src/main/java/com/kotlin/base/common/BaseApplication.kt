package com.kotlin.base.common

import android.app.Application
import com.kotlin.base.injection.component.AppComponent
import com.kotlin.base.injection.component.DaggerAppComponent
import com.kotlin.base.injection.module.AppModule

/**
 * Created by mac on 2018/4/13.
 */
class BaseApplication : Application() {

    lateinit var appComponent: AppComponent


//    companion object {
//        lateinit var context: Context
//    }

    override fun onCreate() {
        super.onCreate()
//        context = this
        initAppInjection()
    }

    private fun initAppInjection() {
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}