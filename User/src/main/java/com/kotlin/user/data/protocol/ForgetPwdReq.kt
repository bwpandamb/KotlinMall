package com.kotlin.user.data.protocol

/**
 * Created by mac on 2018/4/12.
 * 忘记密码实体类
 */
data class ForgetPwdReq(val mobile: String, val verifyCode: String) {
}