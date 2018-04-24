package com.kotlin.user.data.api

import com.kotlin.base.data.protocol.BaseResp
import retrofit2.http.POST
import rx.Observable

/**
 * Created by mac on 2018/4/12.
 */
interface UploadApi {
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>



}