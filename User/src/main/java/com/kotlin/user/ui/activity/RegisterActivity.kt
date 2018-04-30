package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        mPersenter = RegisterPresenter()//使用dagger实例化，这里就不需要了
//        mPersenter.mView = this
        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mRegisterBtn.enable(mMobileEt, { isBtnEnable() })
        mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })
        mRegisterBtn.setOnClickListener(this)
        mVerifyCodeBtn.setOnClickListener(this)
    }
    //通过抽象方法让子类实现必须实现的方法，父类控制流程
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPersenter.mView = this
    }

    /**
     * 注册回调
     */
    override fun onRegisterResult(result: String) {

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRegisterBtn -> {
                mPersenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdEt.text.toString())
            }
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                Toast.makeText(this, "发送验证码成功！（模拟）", Toast.LENGTH_SHORT).show()
            }
        }


    }

    override fun onBackPressed() {

        val time = System.currentTimeMillis()

        if (time - pressTime > 2000) {
            Toast.makeText(this, "再点击一次退出程序", Toast.LENGTH_SHORT).show()
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    /*
        判断按钮是否可用
    */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
