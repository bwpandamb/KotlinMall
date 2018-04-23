package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

/**
 * Created by mac on 2018/4/23.
 */
interface LoginView : BaseView {

    fun onLoginResult(result: UserInfo)
}