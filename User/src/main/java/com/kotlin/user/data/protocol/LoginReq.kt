package com.kotlin.user.data.protocol

/**
 * Created by mac on 2018/4/12.
 * 注册实体类
 */
data class LoginReq(val mobile: String, val pwd: String, val pushId: String) {
}