package com.kotlin.base.rx

import rx.Subscriber

/**
 * Created by mac on 2018/4/11.
 */
open class BaseSubscriber<T> : Subscriber<T>() {
    override fun onCompleted() {

    }

    override fun onError(e: Throwable?) {

    }

    override fun onNext(t: T) {

    }
}