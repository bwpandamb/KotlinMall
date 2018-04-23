package com.kotlin.base.presenter.view

/**
 * Created by mac on 2018/4/9.
 */
interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(errorText:String)

}