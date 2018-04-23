package com.kotlin.base.rx

import com.kotlin.base.presenter.view.BaseView
import rx.Subscriber

/**
 * Created by mac on 2018/4/11.
 */
open class BaseSubscriber<T>(val baseView: BaseView) : Subscriber<T>() {
    override fun onCompleted() {
        baseView.hideLoading()
    }
    //这个报错功能是每个subscriber都需要且相同，因此在这里统一一下
    override fun onError(e: Throwable?) {
        baseView.hideLoading()

        if (e is BaseException) {
            baseView.onError(e.msg)
        }else{

        }
    }
    //这个方法是只要功能，让下面去重写即可
    override fun onNext(t: T) {

    }
}