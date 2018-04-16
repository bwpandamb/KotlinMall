package com.kotlin.base.rx

import com.kotlin.base.data.protocol.BaseResp
import rx.Observable
import rx.functions.Func1

/**
 * Created by mac on 2018/4/16.
 */
class BaseFunBoolean<T> : Func1<BaseResp<T>, Observable<Boolean>> {

    override fun call(t: BaseResp<T>): Observable<Boolean> {
        if (t.status != 0) {
            return Observable.error(BaseException(t.status, t.message))
        }

        return Observable.just(true)
    }
}