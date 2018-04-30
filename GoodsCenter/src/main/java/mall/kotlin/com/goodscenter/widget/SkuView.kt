package com.kotlin.goods.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import com.eightbitlab.rxbus.Bus
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.GoodsSku
import com.kotlin.goods.event.SkuChangedEvent
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import com.zhy.view.flowlayout.TagFlowLayout
import kotlinx.android.synthetic.main.layout_sku_view.view.*
import mall.kotlin.com.goodscenter.R

/*
    单个SKU
 */
class SkuView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {
    private lateinit var mGoodsSku: GoodsSku

    init {
        View.inflate(context, R.layout.layout_sku_view, this)

        mSkuContentView.setOnTagClickListener(object : TagFlowLayout.OnTagClickListener {
            override fun onTagClick(view: View?, position: Int, parent: FlowLayout?): Boolean {
                Toast.makeText(context, "点击的是 $position", Toast.LENGTH_SHORT).show()
                return true
            }

        })
    }

    /*
        动态设置SKU数据
     */
    fun setSkuData(goodsSku: GoodsSku) {
        mGoodsSku = goodsSku
        mSkuTitleTv.text = goodsSku.skuTitle

        //FlowLayout设置数据
        mSkuContentView.adapter = object : TagAdapter<String>(goodsSku.skuContent) {
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(context)
                        .inflate(R.layout.layout_sku_item, parent, false) as TextView
                view.text = t
                return view
            }
        }

        mSkuContentView.adapter.setSelectedList(0)

        mSkuContentView.setOnTagClickListener { _, _, _ ->
            Bus.send(SkuChangedEvent())
            true
        }
    }

    /*
        获取选中的SKU
     */
    fun getSkuInfo(): String {

        if (!mSkuContentView.selectedList.isEmpty()) {
            return mSkuTitleTv.text.toString() + GoodsConstant.SKU_SEPARATOR +
                    mGoodsSku.skuContent[mSkuContentView.selectedList.first()]
        } else {

            return mSkuTitleTv.text.toString() + GoodsConstant.SKU_SEPARATOR
        }

    }
}

