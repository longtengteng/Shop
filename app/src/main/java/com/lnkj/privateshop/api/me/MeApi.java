package com.lnkj.privateshop.api.me;

import com.lnkj.privateshop.MyApplication;
import com.lnkj.privateshop.api.ApiUtils;
import com.lnkj.privateshop.components.retrofit.RequestHelper;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Retrofit;
import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by WRJ on 2016/10/7.
 */
public class MeApi {

    private MeService meService;
    private RequestHelper requestHelper;

    public MeApi() {

        this.requestHelper = requestHelper;
        Retrofit retrofit = ApiUtils.initRetrofit(MyApplication.getOkHttpClient());
        meService = retrofit.create(MeService.class);
    }

    //登录
    public Observable<String> getGoodsCategory(Map<String, Object> map) {
//        map = requestHelper.getMap(map);
        return meService.getGoodsCategory(map).subscribeOn(Schedulers.io());
    }

    //是否设置提现密码没
    public Observable<String> isSetWithdrawPwd(Map<String, Object> map) {
        return meService.isSetWithdrawPwd(map).subscribeOn(Schedulers.io());
    }

    //登录
    public Observable<String> getChatRoomList(Map<String, Object> map) {
        return meService.getChatRoomList(map).subscribeOn(Schedulers.io());
    }

    //验证秘密
    public Observable<String> checkPassword(Map<String, Object> map) {
        return meService.checkPassword(map).subscribeOn(Schedulers.io());

    }

    //保存新手机号
    public Observable<String> saveNewMobile(Map<String, Object> map) {
        return meService.saveNewMobile(map).subscribeOn(Schedulers.io());

    }

    //设置支付密码
    public Observable<String> payPassword(Map<String, Object> map) {
        return meService.payPassword(map).subscribeOn(Schedulers.io());

    } //修改支付密码

    public Observable<String> editpayPassword(Map<String, Object> map) {
        return meService.editpayPassword(map).subscribeOn(Schedulers.io());

    }

    //获取全部订单
    public Observable<String> getOrder(Map<String, Object> map) {
        return meService.geOrder(map).subscribeOn(Schedulers.io());
    }

    //获取全部订单
    public Observable<String> refundReason(Map<String, Object> map) {
        return meService.refundReason(map).subscribeOn(Schedulers.io());
    }

    //退货协商记录
    public Observable<String> getTalkRecord(Map<String, Object> map) {
        return meService.getTalkRecord(map).subscribeOn(Schedulers.io());
    }

    //退款金额
    public Observable<String> getRturnPrice(Map<String, Object> map) {
        return meService.getRturnPrice(map).subscribeOn(Schedulers.io());
    }

    //改变订单状态
    public Observable<String> changeOrder(Map<String, Object> map) {
        return meService.changeOrder(map).subscribeOn(Schedulers.io());
    }

    //卖家改变订单状态
    public Observable<String> chanageOrderStatus(Map<String, Object> map) {
        return meService.chanageOrderStatus(map).subscribeOn(Schedulers.io());
    }
    //获取收藏商品列表

    public Observable<String> getGoodsCollection(Map<String, Object> map) {
        return meService.getGoodsCollection(map).subscribeOn(Schedulers.io());

    }

    // 取消收藏商品
    public Observable<String> getDeldteFavorGoods(Map<String, Object> map) {
        return meService.getDeldteFavorGoods(map).subscribeOn(Schedulers.io());

    }

    // 关注店铺列表
    public Observable<String> getFollowShopList(Map<String, Object> map) {
        return meService.getFollowShopList(map).subscribeOn(Schedulers.io());

    }

    // 取消关注店铺列表
    public Observable<String> getDeldteShopList(Map<String, Object> map) {
        return meService.getDeldteShopList(map).subscribeOn(Schedulers.io());

    }

    // 我的足迹列表
    public Observable<String> getFootPrintList(Map<String, Object> map) {
        return meService.getFootPrintList(map).subscribeOn(Schedulers.io());

    }    // 删除足迹

    public Observable<String> delUserBrowse(Map<String, Object> map) {
        return meService.delUserBrowse(map).subscribeOn(Schedulers.io());

    }

    // 我的余额
    public Observable<String> getbalance(Map<String, Object> map) {
        return meService.getbalance(map).subscribeOn(Schedulers.io());

    }

    // 我的余额明细
    public Observable<String> getbalancedetail(Map<String, Object> map) {
        return meService.getbalancedetail(map).subscribeOn(Schedulers.io());

    }

    // 卖家余额明细
    public Observable<String> getSellbalancedetail(Map<String, Object> map) {
        return meService.getSellbalancedetail(map).subscribeOn(Schedulers.io());

    }

    // 我的地址列表
    public Observable<String> getAddressList(Map<String, Object> map) {
        return meService.getAddressList(map).subscribeOn(Schedulers.io());

    }

    // 删除地址
    public Observable<String> getDeleteAddress(Map<String, Object> map) {
        return meService.getDeleteAddress(map).subscribeOn(Schedulers.io());

    }

    // 我的地址列表
    public Observable<String> getAddDefaultAddress(Map<String, Object> map) {
        return meService.getAddDefaultAddress(map).subscribeOn(Schedulers.io());

    }

    // 保存收获地址
    public Observable<String> saveAddress(Map<String, Object> map) {
        return meService.saveAddress(map).subscribeOn(Schedulers.io());

    }

    // 获取地址详细信息
    public Observable<String> getAddressDetail(Map<String, Object> map) {
        return meService.getAddressDetail(map).subscribeOn(Schedulers.io());

    }

    // 获取地址详细信息
    public Observable<String> smartAddress(Map<String, Object> map) {
        return meService.smartAddress(map).subscribeOn(Schedulers.io());

    }

    // 更改密码
    public Observable<String> chagePassWord(Map<String, Object> map) {
        return meService.chagePassWord(map).subscribeOn(Schedulers.io());

    }

    // 卖家订单
    public Observable<String> getSellOrder(Map<String, Object> map) {
        return meService.getSellOrder(map).subscribeOn(Schedulers.io());

    }

    // 订单搜索
    public Observable<String> searchOrder(Map<String, Object> map) {
        return meService.searchOrder(map).subscribeOn(Schedulers.io());
    }

    // 等级信息
    public Observable<String> shopGrade(Map<String, Object> map) {
        return meService.shopGrade(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺信息接口接口
    public Observable<String> getEditShopInfo(Map<String, Object> map) {
        return meService.getEditShopInfo(map).subscribeOn(Schedulers.io());

    }

    // 提醒付款
    public Observable<String> onRemindPay(Map<String, Object> map) {
        return meService.onRemindPay(map).subscribeOn(Schedulers.io());

    }

    // 商品管理
    public Observable<String> getPutGoods(Map<String, Object> map) {
        return meService.getPutGoods(map).subscribeOn(Schedulers.io());

    }

    // 上架、下架商品
    public Observable<String> getdownGoods(Map<String, Object> map) {
        return meService.getdownGoods(map).subscribeOn(Schedulers.io());

    }

    // 删除商品
    public Observable<String> deleteGoods(Map<String, Object> map) {
        return meService.deleteGoods(map).subscribeOn(Schedulers.io());
    }

    // 月管理
    public Observable<String> getMonths(Map<String, Object> map) {
        return meService.getMonths(map).subscribeOn(Schedulers.io());

    }

    // 获取颜色
    public Observable<String> getColor(Map<String, Object> map) {
        return meService.getColor(map).subscribeOn(Schedulers.io());

    }

    // 获取尺码
    public Observable<String> getsize(Map<String, Object> map) {
        return meService.getsize(map).subscribeOn(Schedulers.io());

    }

    // 获取尺码
    public Observable<String> putNewColor(Map<String, Object> map) {
        return meService.putNewColor(map).subscribeOn(Schedulers.io());

    }

    // 店铺列表
    public Observable<String> getShopList(Map<String, Object> map) {
        return meService.getShopList(map).subscribeOn(Schedulers.io());

    }


    // 获取商品属性
    public Observable<String> getAttri(Map<String, Object> map) {
        return meService.getAttri(map).subscribeOn(Schedulers.io());

    }

    // 上传新商品
    public Observable<String> addGoods(List<MultipartBody.Part> partList) {
        return meService.addGoods(partList).subscribeOn(Schedulers.io());

    }

    // 设置提现密码
    public Observable<String> verifyIdCard(List<MultipartBody.Part> partList) {
        return meService.verifyIdCard(partList).subscribeOn(Schedulers.io());

    }

    // 开店(在原来开网店的基础上修改)
    public Observable<String> openShop(List<MultipartBody.Part> partList) {
        return meService.openShop(partList).subscribeOn(Schedulers.io());

    }

    // 保存店铺编辑接口
    public Observable<String> saveEditShop(List<MultipartBody.Part> partList) {
        return meService.saveEditShop(partList).subscribeOn(Schedulers.io());

    }

    //获取验证码
    public Observable<String> getRePWRandNumber(Map<String, String> map) {
        return meService.getRePWRandNumber(map).subscribeOn(Schedulers.io());

    }

    //修改了
    public Observable<String> editWithdrawPassword(Map<String, String> map) {
        return meService.editWithdrawPassword(map).subscribeOn(Schedulers.io());

    }

    //修改了
    public Observable<String> editPayPassword(Map<String, String> map) {
        return meService.editPayPassword(map).subscribeOn(Schedulers.io());

    }

    //验证  验证码
    public Observable<String> checkCode(Map<String, String> map) {
        return meService.checkCode(map).subscribeOn(Schedulers.io());

    }

    // 商品搜索列表
    public Observable<String> seachGoods(Map<String, Object> map) {
        return meService.seachGoods(map).subscribeOn(Schedulers.io());
    }

    // 店铺搜索列表
    public Observable<String> seachshop(Map<String, Object> map) {
        return meService.seachshop(map).subscribeOn(Schedulers.io());
    }

    // 商品搜索列表
    public Observable<String> setWithdrawPassword(Map<String, Object> map) {
        return meService.setWithdrawPassword(map).subscribeOn(Schedulers.io());
    }

    // 筛选商品分类
    public Observable<String> classGoods(Map<String, Object> map) {
        return meService.classGoods(map).subscribeOn(Schedulers.io());
    }

    //提交资质
    public Observable<String> putShopZiZhi(List<MultipartBody.Part> partList) {
        return meService.putShopZiZhi(partList).subscribeOn(Schedulers.io());

    }

    // 筛选商品属性
    public Observable<String> addriGoods(Map<String, Object> map) {
        return meService.addriGoods(map).subscribeOn(Schedulers.io());
    }

    // 圈子动态
    public Observable<String> getDynamicrag(Map<String, Object> map) {
        return meService.getDynamicrag(map).subscribeOn(Schedulers.io());
    }

    // 订单详情
    public Observable<String> getOrderDetailde(Map<String, Object> map) {
        return meService.getOrderDetailde(map).subscribeOn(Schedulers.io());
    }

    // 首页轮播图
    public Observable<String> getBanner(Map<String, Object> map) {
        return meService.getBanner(map).subscribeOn(Schedulers.io());
    }

    //首页爆款推荐图
    public Observable<String> gethotActivityBanner(Map<String, Object> map) {
        return meService.gethotActivityBanner(map).subscribeOn(Schedulers.io());
    }

    //首页限时特惠
    public Observable<String> getLimitFavour(Map<String, Object> map) {
        return meService.getLimitFavourList(map).subscribeOn(Schedulers.io());
    }

    // 首页商品列表
    public Observable<String> getHomeGoodsList(Map<String, Object> map) {
        return meService.getHomeGoodsList(map).subscribeOn(Schedulers.io());
    }   // 首页关注商品

    public Observable<String> getshopFocusGoods(Map<String, Object> map) {
        return meService.getshopFocusGoods(map).subscribeOn(Schedulers.io());
    }

    // 获取商品详情
    public Observable<String> getGoodsInfo(Map<String, Object> map) {
        return meService.getGoodsInfo(map).subscribeOn(Schedulers.io());
    }

    // 获取限时特惠商品详情
    public Observable<String> getGoodsDetailFromLimit(Map<String, Object> map) {
        return meService.getGoodsDetailFromLimit(map).subscribeOn(Schedulers.io());
    }

    // 获取商品详情--规格--根据参数获取信息
    public Observable<String> getPriceAndStoreBySpce(Map<String, Object> map) {
        return meService.getPriceAndStoreBySpce(map).subscribeOn(Schedulers.io());
    }

    // 立即购买或者结算购物车进入订单确认页
    public Observable<String> getcartConfirm(Map<String, Object> map) {
        return meService.getcartConfirm(map).subscribeOn(Schedulers.io());
    }

    // 加入购物车的操作
    public Observable<String> getaddCart(Map<String, Object> map) {
        return meService.getaddCart(map).subscribeOn(Schedulers.io());
    }

    // 获取商品详情
    public Observable<String> getShopEmchat(Map<String, Object> map) {
        return meService.getShopEmchat(map).subscribeOn(Schedulers.io());
    }

    // 获取商品详情
    public Observable<String> getServiceInfo(Map<String, Object> map) {
        return meService.getServiceInfo(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺品论列表
    public Observable<String> getShopComment(Map<String, Object> map) {
        return meService.getShopComment(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺档案
    public Observable<String> getShopArchives(Map<String, Object> map) {
        return meService.getShopArchives(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺详情
    public Observable<String> getShopInfo(Map<String, Object> map) {
        return meService.getShopInfo(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺评论列表

    public Observable<String> getShopCommentList(Map<String, Object> map) {
        return meService.getShopCommentList(map).subscribeOn(Schedulers.io());
    }
    /*xfl   店铺，点击免费开店*/

    public Observable<String> apply_start_shop(Map<String, Object> map) {
        return meService.apply_start_shop(map).subscribeOn(Schedulers.io());
    }
    //获取店铺商品列表
    public Observable<String> getShopmerchandiseList(Map<String, Object> map) {
        return meService.getShopmerchandiseList(map).subscribeOn(Schedulers.io());
    }

    //获取店铺会员商品列表
    public Observable<String> getShopMenberList(Map<String, Object> map) {
        return meService.getShopMenberList(map).subscribeOn(Schedulers.io());
    }

    //获取圈子动态列表
    public Observable<String> getDynamicList(Map<String, Object> map) {
        return meService.getDynamicList(map).subscribeOn(Schedulers.io());
    }

    //获取商品规格
    public Observable<String> getGoodSspecification(Map<String, Object> map) {
        return meService.getGoodSspecification(map).subscribeOn(Schedulers.io());
    }

    //加入购物车
    public Observable<String> addShopCar(Map<String, Object> map) {
        return meService.addShopCar(map).subscribeOn(Schedulers.io());
    }

    //购物车列表
    public Observable<String> addGoodsCar(Map<String, Object> map) {
        return meService.addGoodsCar(map).subscribeOn(Schedulers.io());
    }
    //获取物流

    public Observable<String> getShipPing(Map<String, Object> map) {
        return meService.addShipPing(map).subscribeOn(Schedulers.io());
    }

    //发货
    public Observable<String> getFShipPing(Map<String, Object> map) {
        return meService.getFShipPing(map).subscribeOn(Schedulers.io());

    }

    //卖家订单详情
    public Observable<String> getSellOrderDetailde(Map<String, Object> map) {
        return meService.getSellOrderDetailde(map).subscribeOn(Schedulers.io());
    }

    //发布圈子
    public Observable<String> setShopCircle(Map<String, Object> map) {
        return meService.setShopCircle(map).subscribeOn(Schedulers.io());
    }

    //收藏商品
    public Observable<String> collecgGoods(Map<String, Object> map) {
        return meService.collecgGoods(map).subscribeOn(Schedulers.io());
    }

    // 退货申请
    public Observable<String> ReturnApplyFor(List<MultipartBody.Part> partList) {
        return meService.ReturnApplyFor(partList).subscribeOn(Schedulers.io());

    }

    //卖家退货列表
    public Observable<String> sellReturnDetails(Map<String, Object> map) {
        return meService.sellReturnDetails(map).subscribeOn(Schedulers.io());
    }

    //卖家同意货拒绝退款身亲g
    public Observable<String> sellReturnOkOrNO(Map<String, Object> map) {
        return meService.sellReturnOkOrNO(map).subscribeOn(Schedulers.io());
    }

    //买家退货列表
    public Observable<String> ReturnDetails(Map<String, Object> map) {
        return meService.ReturnDetails(map).subscribeOn(Schedulers.io());
    }

    //帮助中心
    public Observable<String> help(Map<String, Object> map) {
        return meService.help(map).subscribeOn(Schedulers.io());
    }

    //帮助中心详情
    public Observable<String> helpDetail(Map<String, Object> map) {
        return meService.helpDetail(map).subscribeOn(Schedulers.io());
    }

    //意见反馈
    public Observable<String> feedBack(Map<String, Object> map) {
        return meService.feedBack(map).subscribeOn(Schedulers.io());
    }

    //买家退货详情
    public Observable<String> ReturnDetails2(Map<String, Object> map) {
        return meService.ReturnDetails2(map).subscribeOn(Schedulers.io());
    }

    //卖家退货详情
    public Observable<String> sellReturnDetails2(Map<String, Object> map) {
        return meService.sellReturnDetails2(map).subscribeOn(Schedulers.io());
    }

    //买家退货发货
    public Observable<String> ReturnShipping(Map<String, Object> map) {
        return meService.ReturnShipping(map).subscribeOn(Schedulers.io());
    }

    //卖家确认收货
    public Observable<String> ReceiveGoods(Map<String, Object> map) {
        return meService.ReceiveGoods(map).subscribeOn(Schedulers.io());
    }

    //催促买家发货
    public Observable<String> onUrged(Map<String, Object> map) {
        return meService.onUrged(map).subscribeOn(Schedulers.io());
    }

    //删除退款订单
    public Observable<String> onDeleteOrder(Map<String, Object> map) {
        return meService.onDeleteOrder(map).subscribeOn(Schedulers.io());
    }

    //买家删除退款订单
    public Observable<String> onMaiDeleteOrder(Map<String, Object> map) {
        return meService.onMaiDeleteOrder(map).subscribeOn(Schedulers.io());
    }

    //获取买家个人信息
    public Observable<String> getUserInfo(Map<String, Object> map) {
        return meService.getUserInfo(map).subscribeOn(Schedulers.io());
    }

    //获取运费模版
    public Observable<String> getExpressTemplate(Map<String, Object> map) {
        return meService.getExpressTemplate(map).subscribeOn(Schedulers.io());
    }

    //删除运费模版
    public Observable<String> delExpressTemplate(Map<String, Object> map) {
        return meService.delExpressTemplate(map).subscribeOn(Schedulers.io());
    }

    //获取省列表
    public Observable<String> getAddress(Map<String, Object> map) {
        return meService.getAddress(map).subscribeOn(Schedulers.io());
    }

    //添加/更新运费模版
    public Observable<String> addExpressTemplate(Map<String, Object> map) {
        return meService.addExpressTemplate(map).subscribeOn(Schedulers.io());
    }

    //撤销未进行退货的退款退货接口
    public Observable<String> goBackRefundOrder(Map<String, Object> map) {
        return meService.goBackRefundOrder(map).subscribeOn(Schedulers.io());
    }

    //获取余额
    public Observable<String> getprice(Map<String, Object> map) {
        return meService.getprice(map).subscribeOn(Schedulers.io());
    }
    //获取应缴纳保证金

    public Observable<String> getShopBondMoney(Map<String, Object> map) {
        return meService.getShopBondMoney(map).subscribeOn(Schedulers.io());
    }
    //解冻保证金

    public Observable<String> thawBond(Map<String, Object> map) {
        return meService.thawBond(map).subscribeOn(Schedulers.io());
    }

    //收藏关注店铺
    public Observable<String> setCollectShop(Map<String, Object> map) {
        return meService.setCollectShop(map).subscribeOn(Schedulers.io());
    }

    // 上传新商品
    public Observable<String> publishCommentaries(List<MultipartBody.Part> partList) {
        return meService.publishCommentaries(partList).subscribeOn(Schedulers.io());
    }

    //获取买家首页订单信息
    public Observable<String> getOredrData(Map<String, Object> map) {
        return meService.getOredrData(map).subscribeOn(Schedulers.io());
    }

    // 上编辑用户信息
    public Observable<String> editUser(List<MultipartBody.Part> partList) {
        return meService.editUser(partList).subscribeOn(Schedulers.io());
    }

    //获取银行卡列表
    public Observable<String> getBankCardList(Map<String, Object> map) {
        return meService.getBankCardList(map).subscribeOn(Schedulers.io());
    }

    //删除银行卡
    public Observable<String> deleteBankCard(Map<String, Object> map) {
        return meService.deleteBankCard(map).subscribeOn(Schedulers.io());
    }

    //添加银行卡信息
    public Observable<String> addBankCard(Map<String, Object> map) {
        return meService.addBankCard(map).subscribeOn(Schedulers.io());
    }

    //获取店铺基本信息
    public Observable<String> addshopUser(Map<String, Object> map) {
        return meService.addshopUser(map).subscribeOn(Schedulers.io());
    }

    //获取卖家首页订单信息
    public Observable<String> getSellOredrData(Map<String, Object> map) {
        return meService.getSellOredrData(map).subscribeOn(Schedulers.io());
    }

    //订单确认
    public Observable<String> orderConfirm(Map<String, Object> map) {
        return meService.orderConfirm(map).subscribeOn(Schedulers.io());
    }

    //订单确认
    public Observable<String> cartConfirm(Map<String, Object> map) {
        return meService.orderConfirm(map).subscribeOn(Schedulers.io());
    }

    //创建订单
    public Observable<String> createOrder(Map<String, Object> map) {
        return meService.createOrder(map).subscribeOn(Schedulers.io());
    }

    //删除购物车商品
    public Observable<String> deleteCarGoods(Map<String, Object> map) {
        return meService.deleteCarGoods(map).subscribeOn(Schedulers.io());
    }

    //获取限时特惠接口
    public Observable<String> getTimeGoods(Map<String, Object> map) {
        return meService.getTimeGoods(map).subscribeOn(Schedulers.io());
    }

    //首页，产品分类
    public Observable<String> getGoodsCategoryList(Map<String, Object> map) {
        return meService.getGoodsCategoryList(map).subscribeOn(Schedulers.io());
    }

    //限时特惠的列表
    public Observable<String> getLimitSaleList(Map<String, Object> map) {
        return meService.getLimitSaleList(map).subscribeOn(Schedulers.io());
    }

    //修改商品规格数量
    public Observable<String> alterGoodsCount(Map<String, Object> map) {
        return meService.alterGoodsCount(map).subscribeOn(Schedulers.io());
    }

    //修改购物车确定
    public Observable<String> alterGoods(Map<String, Object> map) {
        return meService.alterGoods(map).subscribeOn(Schedulers.io());
    }

    //首页限时活动
    public Observable<String> getLimitedFavour(Map<String, Object> map) {
        return meService.getLimitedFavour(map).subscribeOn(Schedulers.io());
    }

    //商品首页订制
    public Observable<String> getOrderWholeSale(Map<String, Object> map) {
        return meService.getOrderWholeSale(map).subscribeOn(Schedulers.io());
    }

    //商品广告
    public Observable<String> getadvertising(Map<String, Object> map) {
        return meService.getadvertising(map).subscribeOn(Schedulers.io());
    }

    //订制批发列表
    public Observable<String> getWholesaleList(Map<String, Object> map) {
        return meService.getWholesaleList(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺品论列表
    public Observable<String> getShopClassList(Map<String, Object> map) {
        return meService.getShopClassList(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺分类
    public Observable<String> getShopClass(Map<String, Object> map) {
        return meService.getShopClass(map).subscribeOn(Schedulers.io());
    }

    // 获取店铺分类下商品
    public Observable<String> getShopGoodsList(Map<String, Object> map) {
        return meService.getShopGoodsList(map).subscribeOn(Schedulers.io());
    }

    // 获取客户评论
    public Observable<String> getClentComment(Map<String, Object> map) {
        return meService.getClentComment(map).subscribeOn(Schedulers.io());
    }

    // 回复客户评论
    public Observable<String> getRevertClentComment(Map<String, Object> map) {
        return meService.getRevertClentComment(map).subscribeOn(Schedulers.io());
    }

    // 提现申请
    public Observable<String> getapplication(Map<String, Object> map) {
        return meService.getapplication(map).subscribeOn(Schedulers.io());
    }

    // 商品编辑
    public Observable<String> getGoodsEdit(Map<String, Object> map) {
        return meService.getGoodsEdit(map).subscribeOn(Schedulers.io());
    }

    // 删除图片
    public Observable<String> deleteImg(Map<String, Object> map) {
        return meService.deleteImg(map).subscribeOn(Schedulers.io());
    }

    // 物流
    public Observable<String> getExpressTraces(Map<String, Object> map) {
        return meService.getExpressTraces(map).subscribeOn(Schedulers.io());
    }

    // 改价
    public Observable<String> getEditOrders(Map<String, Object> map) {
        return meService.getEditOrders(map).subscribeOn(Schedulers.io());
    }

    // 保存改价
    public Observable<String> getSaveEditOrders(Map<String, Object> map) {
        return meService.getSaveEditOrders(map).subscribeOn(Schedulers.io());
    }

    // 订单支付
    public Observable<String> getPay(Map<String, Object> map) {
        return meService.getPay(map).subscribeOn(Schedulers.io());
    }

    // 充值
    public Observable<String> recharge(Map<String, Object> map) {
        return meService.recharge(map).subscribeOn(Schedulers.io());
    }

    // 保证金支付
    public Observable<String> getPayshopPayBond(Map<String, Object> map) {
        return meService.getPayshopPayBond(map).subscribeOn(Schedulers.io());
    }

    // 启动页广告
    public Observable<String> startAppAd(Map<String, Object> map) {
        return meService.startAppAd(map).subscribeOn(Schedulers.io());
    }


}