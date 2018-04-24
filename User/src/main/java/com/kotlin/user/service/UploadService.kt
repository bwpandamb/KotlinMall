package com.kotlin.user.service

import rx.Observable

/**
 * Created by mac on 2018/4/10.
 */
interface UploadService {

    fun getUploadToken(): Observable<String>
}