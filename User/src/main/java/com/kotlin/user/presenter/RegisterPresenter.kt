package com.kotlin.user.presenter

import com.kotlin.base.ext.execut
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.presenter.view.RegisterView
import com.kotlin.user.service.impl.UserServiceIml
import javax.inject.Inject


/**
 * Created by mac on 2018/4/9.
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterView>() {
    @Inject
    lateinit var userService: UserServiceIml

    fun register(mobile:String,verifyCode:String,pwd:String){

//        val userService = UserServiceIml()

//        userService.register(mobile,verifyCode,pwd)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(object : BaseSubscriber<Boolean>(){
//                    override fun onNext(t: Boolean) {
//                        super.onNext(t)
//                        mView.onRegisterResult(t)
//                    }
//                })

        userService.register(mobile,verifyCode,pwd)
                .execut(object : BaseSubscriber<Boolean>(){
                    override fun onNext(t: Boolean) {
                        if (t) {
                            mView.onRegisterResult("注册成功")
                        }else{
                            mView.onRegisterResult("注册失败")
                        }
                    }
                },lifecycleProvider)


    }
}