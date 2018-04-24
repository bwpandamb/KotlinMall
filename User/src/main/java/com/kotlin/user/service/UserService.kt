package com.kotlin.user.service

import com.kotlin.user.data.protocol.UserInfo
import rx.Observable

/**
 * Created by mac on 2018/4/10.
 */
interface UserService {

    fun register(mobile: String, verifyCode: String, pwd: String): Observable<Boolean>

    fun login(mobile: String, verifyCode: String, pushId: String): Observable<UserInfo>

    fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean>

    fun resetPwd(mobile: String, pwd: String): Observable<Boolean>

    //编辑用户资料
    fun editUser(userIcon:String,userName:String,userGender:String,userSign:String):Observable<UserInfo>
}