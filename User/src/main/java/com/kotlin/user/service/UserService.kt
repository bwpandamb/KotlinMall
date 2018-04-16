package com.kotlin.user.service

import rx.Observable

/**
 * Created by mac on 2018/4/10.
 */
interface UserService {
    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>
}