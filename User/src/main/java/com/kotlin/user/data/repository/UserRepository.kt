package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocol.LoginReq
import com.kotlin.user.data.protocol.RegisterReq
import com.kotlin.user.data.protocol.UserInfo
import rx.Observable
import javax.inject.Inject

/**
 * Created by mac on 2018/4/12.
 */
class UserRepository @Inject constructor() {
    fun register(mobile: String, pwd: String, verfiCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .register(RegisterReq(mobile, pwd, verfiCode))

    }

    fun login(mobile: String, pwd: String, pushId: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .login(LoginReq(mobile, pwd, pushId))
    }

}