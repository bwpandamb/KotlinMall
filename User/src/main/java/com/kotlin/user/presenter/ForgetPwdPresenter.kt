package com.kotlin.user.presenter

import com.kotlin.base.ext.execut
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.ForgetPwdView
import com.kotlin.user.service.impl.UserServiceIml
import javax.inject.Inject

/**
 * Created by mac on 2018/4/9.
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdView>() {
    @Inject
    lateinit var userService: UserServiceIml

    fun forgetPwd(mobile: String, verifyCode: String) {

        if (!checkNetWorkIsAvailable()) return

        mView.showLoading()

        userService.forgetPwd(mobile, verifyCode)
                .execut(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onForgetPwdResult("验证成功")
                    }
                }, lifecycleProvider)


    }
}