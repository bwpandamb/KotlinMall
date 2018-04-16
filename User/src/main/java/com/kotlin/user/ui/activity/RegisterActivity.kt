package com.kotlin.user.ui.activity

import android.os.Bundle
import android.widget.Toast
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.kotlin.user.presenter.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView {
    //通过抽象方法让子类实现必须实现的方法，父类控制流程
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPersenter.mView = this
    }

    override fun onRegisterResult(result: String) {

        Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

//        mPersenter = RegisterPresenter()//使用dagger实例化，这里就不需要了
//        mPersenter.mView = this

        //原始的方法(写了三种)
//        mRegisterBtn.setOnClickListener(View.OnClickListener { mPersenter.register(mMoblieEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdet.text.toString()) })
//        mRegisterBtn.setOnClickListener { mPersenter.register(mMoblieEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdet.text.toString()) }
//        mRegisterBtn.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                mPersenter.register(mMoblieEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdet.text.toString())
//            }
//        })

        //传入onClickListener对象？？这样有什么改写的意义？
//        mRegisterBtn.onClick(object : View.OnClickListener {
//            override fun onClick(p0: View?) {
//                mPersenter.register(mMoblieEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdet.text.toString())
//            }
//
//        })

        //lamadda风格的自定义方法，感觉很有意思，虽然还是觉得多余
        mRegisterBtn.onClick {
            mPersenter.register(mMoblieEt.text.toString(), mVerifyCodeEt.text.toString(), mPwdet.text.toString())
        }


    }


}
