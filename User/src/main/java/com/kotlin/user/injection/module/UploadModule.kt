package com.kotlin.user.injection.module

import com.kotlin.user.service.UploadService
import com.kotlin.user.service.impl.UploadServiceIml
import dagger.Module
import dagger.Provides

/**
 * Created by mac on 2018/4/13.
 */
@Module
class UploadModule {

    @Provides
    fun provideUploadService(uploadService: UploadServiceIml): UploadService {
        return uploadService
    }
}