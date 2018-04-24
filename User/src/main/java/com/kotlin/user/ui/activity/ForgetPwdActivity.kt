package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.ForgetPwdPresenter
import com.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity

/**
 * 忘记密码界面
 */

class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mNextBtn.setOnClickListener(this)
        mVerifyCodeBtn.setOnClickListener(this)
    }

    //通过抽象方法让子类实现必须实现的方法，父类控制流程
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPersenter.mView = this
    }

    /*
        结果回调
     */
    override fun onForgetPwdResult(result: String) {
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
        startActivity<ResetPwdActivity>("moblie" to mMobileEt.text.toString())
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mNextBtn -> {
                mPersenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                Toast.makeText(this, "发送验证码成功！（模拟123456）", Toast.LENGTH_SHORT).show()
            }
        }


    }


    /*
        判断按钮是否可用
    */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()

    }
}