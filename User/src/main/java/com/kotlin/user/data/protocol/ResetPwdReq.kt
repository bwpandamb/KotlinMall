package com.kotlin.user.data.protocol

/**
 * Created by mac on 2018/4/12.
 * 重置密码实体类
 */
data class ResetPwdReq(val mobile: String, val pwd: String) {
}