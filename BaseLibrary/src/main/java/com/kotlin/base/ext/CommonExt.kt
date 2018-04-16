package com.kotlin.base.ext

import android.view.View
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.base.rx.BaseFunBoolean
import com.kotlin.base.rx.BaseFunc
import com.kotlin.base.rx.BaseSubscriber
import com.trello.rxlifecycle.LifecycleProvider
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by mac on 2018/4/11.
 */
fun <T> Observable<T>.execut(subscriber: BaseSubscriber<T>, lifecycleProvider: LifecycleProvider<*>) {
    this.subscribeOn(Schedulers.io())
            .compose(lifecycleProvider.bindToLifecycle())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(subscriber)
}

fun <T> Observable<BaseResp<T>>.convert():Observable<T> {

    return this.flatMap(BaseFunc())
}

fun <T> Observable<BaseResp<T>>.convertBoolean():Observable<Boolean> {

    return this.flatMap(BaseFunBoolean())
}


//关于自定义的点击事件，这里就是调用了View的点击事件并重写
fun View.onClick(listener: View.OnClickListener) {
    this.setOnClickListener(listener)
}

//如果想定义lamadda形式的代码，可以如下改写,method是一个方法类型？？我不是很明白
fun View.onClick(method: () -> Unit) {
    this.setOnClickListener { method() }
}