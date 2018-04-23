package com.kotlin.user.presenter

import com.kotlin.base.ext.execut
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.service.impl.UserServiceIml
import javax.inject.Inject

/**
 * Created by mac on 2018/4/23.
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {
    @Inject
    lateinit var userService: UserServiceIml

    fun login(mobile: String, pwd: String, pushId: String) {
        if (!checkNetWorkIsAvailable()) return

        mView.showLoading()

        userService.login(mobile, pwd, pushId)
                .execut(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)


    }
}