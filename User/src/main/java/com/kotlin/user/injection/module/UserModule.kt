package com.kotlin.user.injection.module

import com.kotlin.user.service.UserService
import com.kotlin.user.service.impl.UserServiceIml
import dagger.Module
import dagger.Provides

/**
 * Created by mac on 2018/4/13.
 */
@Module
class UserModule {

    @Provides
    fun providesUserService(serviceIml: UserServiceIml) :UserService{
        return serviceIml
    }
}