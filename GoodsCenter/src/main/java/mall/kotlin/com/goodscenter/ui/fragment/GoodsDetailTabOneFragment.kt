package com.kotlin.goods.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseActivity
import com.kotlin.base.ui.fragment.BaseMvpFragment
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.base.widgets.BannerImageLoader
import com.kotlin.goods.common.GoodsConstant
import com.kotlin.goods.data.protocol.Goods
import com.kotlin.goods.event.AddCartEvent
import com.kotlin.goods.event.GoodsDetailImageEvent
import com.kotlin.goods.event.SkuChangedEvent
import com.kotlin.goods.event.UpdateCartSizeEvent
import com.kotlin.goods.injection.component.DaggerGoodsComponent
import com.kotlin.goods.injection.module.GoodsModule
import com.kotlin.goods.presenter.GoodsDetailPresenter
import com.kotlin.goods.presenter.view.GoodsDetailView
import com.kotlin.goods.ui.activity.GoodsDetailActivity
import com.kotlin.goods.widget.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import mall.kotlin.com.goodscenter.R

/*
    商品详情Tab One
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {
    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                    , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0, 0
            )
            //这个contentView很有意思，获取的是整体页面的根部局
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    /*
      初始化缩放动画
   */
    private fun initAnim() {
        //fillBefore是指动画结束时画面停留在此动画的第一帧; fillAfter是指动画结束是画面停留在此动画的最后一帧
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /*
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView!!.startAnimation(mAnimationEnd)
        }
    }

    /*
        加载数据
     */
    private fun loadData() {
        mPersenter.getGoodsDetailList(activity.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPersenter.mView = this
    }

    /*
        获取商品详情 回调
     */
    override fun onGetGoodsDetailResult(result: Goods) {

        mCurGoods = result //这里获取到商品后存一个全局变量，以便后面加入购物车传入数据

        mGoodsDetailBanner.setImages(result.goodsBanner.split(",")) //给banner设置滚动的图片地址，这里是...逗号分隔的一个参数，使用split直接获取List集合
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc    //商品描叙详情
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice) //商品价格（要转换一下）
        mSkuSelectedTv.text = result.goodsDefaultSku //商品sku，也就是不同的属性

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo)) //给另外一个fragment发送一下显示用的图片地址

        loadPopData(result) //加载弹框内的数据
    }

    /*
        加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    /*
        监听SKU变化及加入购物车事件
     */
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
        加入购物车
     */
    private fun addCart() {
        //判断是否为null，不为null就取用其参数
        mCurGoods?.let {
            mPersenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
            )
        }

    }

    /*
        加入购物车 回调
     */
    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

}
