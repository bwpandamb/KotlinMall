package com.kotlin.base.ui.activity

import android.os.Bundle
import com.kotlin.base.common.AppManager
import com.trello.rxlifecycle.components.support.RxAppCompatActivity

/**
 * Created by mac on 2018/4/9.
 */
open class BaseActivity : RxAppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppManager.instantce.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        AppManager.instantce.finishActivity(this)
    }
}