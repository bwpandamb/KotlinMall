package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.api.UserApi
import com.kotlin.user.data.protocol.*
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

    fun forgetPwd(mobile: String, verfiCode: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .forgetPwd(ForgetPwdReq(mobile, verfiCode))
    }


    fun resetPwd(mobile: String, pwd: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UserApi::class.java)
                .resetPwd(ResetPwdReq(mobile, pwd))
    }

    /*
            编辑用户资料
         */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String): Observable<BaseResp<UserInfo>> {
        return RetrofitFactory.instance.creat(UserApi::class.java).editUser(EditUserReq(userIcon, userName, userGender, userSign))
    }

}