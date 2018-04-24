package com.kotlin.user.presenter

import com.kotlin.base.ext.execut
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.ResetPwdView
import com.kotlin.user.service.impl.UserServiceIml
import javax.inject.Inject

/**
 * Created by mac on 2018/4/9.
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdView>() {
    @Inject
    lateinit var userService: UserServiceIml

    fun resetPwd(mobile: String, pwd: String) {

        if (!checkNetWorkIsAvailable()) return

        mView.showLoading()

        userService.resetPwd(mobile, pwd)
                .execut(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        mView.onResetPwdResult("重置密码成功")
                    }
                }, lifecycleProvider)


    }
}