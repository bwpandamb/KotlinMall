package com.kotlin.user.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.enable
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.provider.router.RouterPath
import com.kotlin.user.R
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.LoginPresenter
import com.kotlin.user.presenter.view.LoginView
import com.kotlin.user.utils.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    /**
     * 初始化视图
     */
    private fun initView() {
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })
        mLoginBtn.setOnClickListener(this)
        //这样的方法是无法获取到mRightTv的，必须让其提供内部的方法来获取，如下实现
//        mHeaderBar.mRightTv.setOnClickListener(this)
        mHeaderBar.getRightView().setOnClickListener(this)
        mForgetPwdTv.setOnClickListener(this)
    }

    //通过抽象方法让子类实现必须实现的方法，父类控制流程
    override fun injectComponent() {
        //注入了对应的mPersenter
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPersenter.mView = this
    }

    /**
     * 登录 回调
     */
    override fun onLoginResult(result: UserInfo) {
        Toast.makeText(this@LoginActivity, result.userName + "登陆成功", Toast.LENGTH_SHORT).show()
        UserPrefsUtils.putUserInfo(result)
//        startActivity<UserInfoActivity>()
        finish()
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            R.id.mLoginBtn -> {
                mPersenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), "")
            }
            R.id.mForgetPwdTv -> {
                startActivity(Intent(this@LoginActivity, ForgetPwdActivity::class.java))
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
                mPwdEt.text.isNullOrEmpty().not()
    }
}