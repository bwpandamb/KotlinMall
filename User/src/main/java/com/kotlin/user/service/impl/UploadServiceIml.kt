package com.kotlin.user.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.user.data.repository.UploadTokenRepository
import com.kotlin.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/**
 * Created by mac on 2018/4/10.
 */
class UploadServiceIml @Inject constructor() : UploadService {


    @Inject
    lateinit var repository: UploadTokenRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }

}