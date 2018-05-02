package com.kotlin.base.widgets

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.kotlin.base.R
import com.kotlin.base.ext.onClick
import kotlinx.android.synthetic.main.layout_header_bar.view.*

/**
 * Created by mac on 2018/4/17.
 */
class HeaderBar @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    //是否显示"返回"图标
    private var isShowBack = true
    //Title文字
    private var titleText:String? = null
    //右侧文字
    private var rightText:String? = null

    init {
        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.HeaderBar)

        isShowBack = typeArray.getBoolean(R.styleable.HeaderBar_isShowBack, true)
        titleText = typeArray.getString(R.styleable.HeaderBar_titleText)
        rightText = typeArray.getString(R.styleable.HeaderBar_rightText)
        initView()
    }

    private fun initView() {
        View.inflate(context, R.layout.layout_header_bar, this)
        mLeftIv.visibility = if (isShowBack) View.VISIBLE else View.GONE
        titleText?.let { mTitleTv.text = it }
        rightText?.let {
            mRightTv.text = it
            mRightTv.visibility = View.VISIBLE
        }
        mLeftIv.onClick {
            if (context is Activity) {
                (context as Activity).finish()
            }
        }
    }

    /*
           获取左侧视图
        */
    fun getLeftView(): ImageView {
        return mLeftIv
    }

    /*
        获取右侧视图
     */
    fun getRightView(): TextView {
        return mRightTv
    }

    /*
        获取右侧文字
     */
    fun getRightText(): String {
        return mRightTv.text.toString()
    }
}