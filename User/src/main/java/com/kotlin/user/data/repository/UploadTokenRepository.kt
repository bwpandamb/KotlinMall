package com.kotlin.user.data.repository

import com.kotlin.base.data.net.RetrofitFactory
import com.kotlin.base.data.protocol.BaseResp
import com.kotlin.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/**
 * Created by mac on 2018/4/12.
 */
class UploadTokenRepository @Inject constructor() {

    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.creat(UploadApi::class.java)
                .getUploadToken()
    }
}