package com.kotlin.user.presenter.view

import com.kotlin.base.presenter.view.BaseView
import com.kotlin.user.data.protocol.UserInfo

/**
 * Created by mac on 2018/4/23.
 */
interface UserInfoView : BaseView {

    fun onGetUploadTokenResult(result: String)

    /*
        编辑用户资料回调
     */
    fun onEditUserResult(result: UserInfo)

}