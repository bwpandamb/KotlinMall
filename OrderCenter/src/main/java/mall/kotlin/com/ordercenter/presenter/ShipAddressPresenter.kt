package com.kotlin.order.presenter

import com.kotlin.base.ext.execut
import com.kotlin.base.presenter.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.order.data.protocol.ShipAddress
import com.kotlin.order.presenter.view.ShipAddressView
import com.kotlin.order.service.ShipAddressService
import javax.inject.Inject

/*
    收货人列表Presenter
 */
class ShipAddressPresenter @Inject constructor() : BasePresenter<ShipAddressView>() {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
        获取收货人列表
     */
    fun getShipAddressList() {
        if (!checkNetWorkIsAvailable()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList().execut(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
            override fun onNext(t: MutableList<ShipAddress>?) {
                mView.onGetShipAddressResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        设置默认收货人信息
     */
    fun setDefaultShipAddress(address:ShipAddress) {
        if (!checkNetWorkIsAvailable()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).execut(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, lifecycleProvider)

    }

    /*
        删除收货人信息
     */
    fun deleteShipAddress(id:Int) {
        if (!checkNetWorkIsAvailable()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id).execut(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteResult(t)
            }
        }, lifecycleProvider)

    }

}
