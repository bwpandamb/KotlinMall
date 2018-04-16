package com.kotlin.user.data.protocol

/**
 * Created by mac on 2018/4/12.
 */
data class RegisterReq(val mobile: String, val pwd: String, val verifyCode: String) {
}