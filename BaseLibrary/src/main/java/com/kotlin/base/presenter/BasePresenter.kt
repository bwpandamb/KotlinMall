package com.kotlin.base.presenter

import com.kotlin.base.presenter.view.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

/**
 * Created by mac on 2018/4/9.
 */
//通过泛型让BasePersenter持有baseView的对象
open class BasePresenter<T : BaseView> {
    lateinit var mView: T

    @Inject
    lateinit var lifecycleProvider: LifecycleProvider<*>

}