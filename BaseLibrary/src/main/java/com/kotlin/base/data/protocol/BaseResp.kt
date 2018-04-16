package com.kotlin.base.data.protocol

/**
 * Created by mac on 2018/4/12.
 */
class BaseResp<out T>(val status:Int,val message:String,val data:T) {
}