package com.kotlin.order.ui.activity

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.order.common.OrderConstant
import com.kotlin.order.common.OrderStatus
import com.kotlin.order.ui.adapter.OrderVpAdapter
import kotlinx.android.synthetic.main.activity_order.*
import mall.kotlin.com.ordercenter.R
import mall.kotlin.com.ordercenter.event.ChangeTabEvent
import mall.kotlin.com.ordercenter.event.FinishOrderEvent

/*
    订单Activity
    主要包括不同订单状态的Fragment
 */
class OrderActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        initView()
        initObserve()
    }

    private var isFinishOrder: Boolean = false

    /*
            初始化视图
         */
    private fun initView() {
        mOrderTab.tabMode = TabLayout.MODE_FIXED
        mOrderVp.adapter = OrderVpAdapter(supportFragmentManager, this)
        mOrderTab.setupWithViewPager(mOrderVp)
        mOrderVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                //加了一个监听，因为点击标签不会刷新相邻的fragment，因为使用的viewpager有缓存
                //这会导致比如点击完成订单后，立马点击查看已完成的订单tab并不会刷出正确的数据（压根就没重新读数据）
                //现在这样就会在更换标签的时候刷新对应标签下的数据
                if (isFinishOrder) {
                    Bus.send(ChangeTabEvent(position))
                }
                isFinishOrder = false
            }
        })

        //根据订单状态设置当前页面
        mOrderVp.currentItem = intent.getIntExtra(OrderConstant.KEY_ORDER_STATUS, OrderStatus.ORDER_ALL)
    }


    /*
        初始化事件监听
     */
    private fun initObserve() {
        Bus.observe<FinishOrderEvent>()
                .subscribe {
                    isFinishOrder = true
                }
                .registerInBus(this)

    }


    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
