package com.lnkj.privateshop.api.me;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by WRJ on 2016/10/7.
 */
public interface MeService {

    //启动页广告
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/startAppAd")
    Observable<String> startAppAd(@FieldMap Map<String, Object> map);

    /**
     * 获取商品分类
     */
    @FormUrlEncoded
    @POST("index.php/Api/GoodsCategoryApi/getCategoryList")
    Observable<String> getGoodsCategory(@FieldMap Map<String, Object> map);

    /**
     * 获取商品分类
     */
    @FormUrlEncoded
    @POST("Api/ShopCenterApi/isSetWithdrawPwd")
    Observable<String> isSetWithdrawPwd(@FieldMap Map<String, Object> map);

    /**
     * 验证密码
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/checkPassword")
    Observable<String> checkPassword(@FieldMap Map<String, Object> map);

    /**
     * 聊天室列表
     */
    @FormUrlEncoded
    @POST("/index.php/Api/IndexApi/getChatRoomList")
    Observable<String> getChatRoomList(@FieldMap Map<String, Object> map);

    /**
     * 保存新手机号
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/saveNewMobile")
    Observable<String> saveNewMobile(@FieldMap Map<String, Object> map);

    /**
     * 保存新手机号
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/payPassword")
    Observable<String> payPassword(@FieldMap Map<String, Object> map);

    /**
     * 保存新手机号
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/editPayPassword")
    Observable<String> editpayPassword(@FieldMap Map<String, Object> map);


    /**
     * 获取订单
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getOrderList")
    Observable<String> geOrder(@FieldMap Map<String, Object> map);

    /**
     * 退款退货原因
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/refundReason")
    Observable<String> refundReason(@FieldMap Map<String, Object> map);

    /**
     * 退款退货原因
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getTalkRecord")
    Observable<String> getTalkRecord(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getRefundPrice")
    Observable<String> getRturnPrice(@FieldMap Map<String, Object> map);


    /**
     * 改变订单状态
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/chanageOrderStatus")
    Observable<String> changeOrder(@FieldMap Map<String, Object> map);

    /**
     * 卖家改变订单状态
     */
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/chanageOrderStatus")
    Observable<String> chanageOrderStatus(@FieldMap Map<String, Object> map);

    /**
     * 获取收藏商品
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getFavoriteGoodsList")
    Observable<String> getGoodsCollection(@FieldMap Map<String, Object> map);

    /**
     * 获取收藏商品
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/cancelGoodsFavorite")
    Observable<String> getDeldteFavorGoods(@FieldMap Map<String, Object> map);

    /**
     * 获取收藏店铺列表
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getFavoriteShopList")
    Observable<String> getFollowShopList(@FieldMap Map<String, Object> map);

    /**
     * 取消收藏店铺列表
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/cancelShopFavorite")
    Observable<String> getDeldteShopList(@FieldMap Map<String, Object> map);

    /**
     * 我的足迹列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/getBrowseList")
    Observable<String> getFootPrintList(@FieldMap Map<String, Object> map);

    /**
     * 删除我的足迹
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/delUserBrowse ")
    Observable<String> delUserBrowse(@FieldMap Map<String, Object> map);

    /**
     * 我的余额
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/getBalance")
    Observable<String> getbalance(@FieldMap Map<String, Object> map);

    /**
     * 我的余额明细
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getBalanceDetails")
    Observable<String> getbalancedetail(@FieldMap Map<String, Object> map);

    //卖家余额明细
    @FormUrlEncoded
    @POST("/index.php/Api/ShopCenterApi/getBalanceDetails")
    Observable<String> getSellbalancedetail(@FieldMap Map<String, Object> map);

    /**
     * 地址列表
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getAddressList")
    Observable<String> getAddressList(@FieldMap Map<String, Object> map);

//

    /**
     * 匿名登录
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/delAddress")
    Observable<String> getDeleteAddress(@FieldMap Map<String, Object> map);
//

    /**
     * 设为默认地址
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/makeAddressDefault")
    Observable<String> getAddDefaultAddress(@FieldMap Map<String, Object> map);


    /**
     * 保存收获地址
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/addEditAddress")
    Observable<String> saveAddress(@FieldMap Map<String, Object> map);

    /**
     * t获取地址详细信息
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getAddressInfo")
    Observable<String> getAddressDetail(@FieldMap Map<String, Object> map);

    /**
     * t获取地址详细信息
     */
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/smartAddress")
    Observable<String> smartAddress(@FieldMap Map<String, Object> map);

    //更改密码
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/editPassword")
    Observable<String> chagePassWord(@FieldMap Map<String, Object> map);

    //
    //获取卖家订单
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getOrderList")
    Observable<String> getSellOrder(@FieldMap Map<String, Object> map);

    //订单搜索
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/searchOrder")
    Observable<String> searchOrder(@FieldMap Map<String, Object> map);


    //获取卖家订单
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/shopGrade")
    Observable<String> shopGrade(@FieldMap Map<String, Object> map);

    //获取卖家订单
    @FormUrlEncoded
    @POST("/index.php/Api/ShopCenterApi/reminderPayOrder")
    Observable<String> onRemindPay(@FieldMap Map<String, Object> map);


    //获取店铺信息接口接口
    @FormUrlEncoded
    @POST("index.php/Api/ShopEditApi/getEditShopInfo")
    Observable<String> getEditShopInfo(@FieldMap Map<String, Object> map);

    //获取商品管理
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/getGoodsList")
    Observable<String> getPutGoods(@FieldMap Map<String, Object> map);

    //
//上架/下架商品
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/changeGoodsStatus")
    Observable<String> getdownGoods(@FieldMap Map<String, Object> map);

    //删除商品
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/delGoods")
    Observable<String> deleteGoods(@FieldMap Map<String, Object> map);

    //
    //月管理
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/moneyStatistical")
    Observable<String> getMonths(@FieldMap Map<String, Object> map);

    //
    //获取颜色
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/getColorList")
    Observable<String> getColor(@FieldMap Map<String, Object> map);

    //
    //获取尺码
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/getSizeList")
    Observable<String> getsize(@FieldMap Map<String, Object> map);

    //
    //新增颜色，尺寸
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/shopSpecAdd")
    Observable<String> putNewColor(@FieldMap Map<String, Object> map);

    //新增颜色，尺寸
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/shop_list")
    Observable<String> getShopList(@FieldMap Map<String, Object> map);

    //
    //获得商品属性
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/getAttrList")
    Observable<String> getAttri(@FieldMap Map<String, Object> map);


    //添加新商品
    @Multipart
    @POST("index.php/Api/GoodsEditApi/goodsAddEdit")
    Observable<String> addGoods(@Part List<MultipartBody.Part> file);

    //设置提现密码
    @Multipart
    @POST("index.php/Api/ShopCenterApi/verifyIdCard")
    Observable<String> verifyIdCard(@Part List<MultipartBody.Part> file);

    //添加新店铺
    @Multipart
    @POST("index.php/Api/ShopEditApi/addShopStep1")
    Observable<String> openShop(@Part List<MultipartBody.Part> file);

    //保存店铺编辑
    @Multipart
    @POST("index.php/Api/ShopEditApi/saveEditShop")
    Observable<String> saveEditShop(@Part List<MultipartBody.Part> file);

    //获取验证码
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/smsCode")
    Observable<String> getRePWRandNumber(@FieldMap Map<String, String> map);

    //更改提现密码
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/editWithdrawPassword")
    Observable<String> editWithdrawPassword(@FieldMap Map<String, String> map); //更改提现密码

    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/editPayPassword")
    Observable<String> editPayPassword(@FieldMap Map<String, String> map);

    //验证  验证码
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/checkSmsCode")
    Observable<String> checkCode(@FieldMap Map<String, String> map);

    //商品搜索
    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/goodsList")
    Observable<String> seachGoods(@FieldMap Map<String, Object> map);

    //商品搜索
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/seachShopName")
    Observable<String> seachshop(@FieldMap Map<String, Object> map);

    //商品搜索
    @FormUrlEncoded
    @POST("Api/ShopCenterApi/setWithdrawPassword")
    Observable<String> setWithdrawPassword(@FieldMap Map<String, Object> map);

    //筛选商品分类
    @FormUrlEncoded
    @POST("index.php/Api/GoodsCategoryApi/all_category")
    Observable<String> classGoods(@FieldMap Map<String, Object> map);

    //提交资质
    @Multipart
    @POST("index.php/Api/ShopEditApi/addShopStep2")
    Observable<String> putShopZiZhi(@Part List<MultipartBody.Part> file);


    //
    //筛选商品属性
    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/getFilterAttr")
    Observable<String> addriGoods(@FieldMap Map<String, Object> map);

    //
    //定时查询邮件
    @FormUrlEncoded
    @POST("index.php/Api/CircleApi/getCircleList")
    Observable<String> getDynamicrag(@FieldMap Map<String, Object> map);

    //
    //标记已读
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getOrderDetail")
    Observable<String> getOrderDetailde(@FieldMap Map<String, Object> map);

    //
    //首页轮播广告
    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/adChangeBanner")
    Observable<String> getBanner(@FieldMap Map<String, Object> map);

    //首页爆款推荐图接口
    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/hotActivityBanner")
    Observable<String> gethotActivityBanner(@FieldMap Map<String, Object> map);


    //首页商品列表
    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/goodsListByType")
    Observable<String> getHomeGoodsList(@FieldMap Map<String, Object> map);

    //首页关注商品
    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/shopFocusGoods")
    Observable<String> getshopFocusGoods(@FieldMap Map<String, Object> map);


    //首页广告
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/startAppAd")
    Observable<String> getAdverList(@FieldMap Map<String, Object> map);

    //修改登陆密码
    @FormUrlEncoded
    @POST("index.php/Api/PublicApi/register")
    Observable<String> modifyLoginPassword(@FieldMap Map<String, Object> map);

    //商品详情
    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/goodsDetail")
    Observable<String> getGoodsInfo(@FieldMap Map<String, Object> map);   //商品详情

    //商品详情
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/getShopEmchat")
    Observable<String> getShopEmchat(@FieldMap Map<String, Object> map);   //商品详情

    //商品详情
    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/getServiceInfo")
    Observable<String> getServiceInfo(@FieldMap Map<String, Object> map);   //商品详情

    //店铺评论  index.php/Api/ShopApi/getShopComment
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/getCommentCount")
    Observable<String> getShopComment(@FieldMap Map<String, Object> map);

    //店铺档案index.php/Api/ShopApi/getShopComment
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/shopFiles")
    Observable<String> getShopArchives(@FieldMap Map<String, Object> map);

    //店铺详情
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/shop_home")
    Observable<String> getShopInfo(@FieldMap Map<String, Object> map);

    ////店铺详情
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/getShopComment")
    Observable<String> getShopCommentList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/shop_goods")
    Observable<String> getShopmerchandiseList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/userVipPrice/p/1")
    Observable<String> getShopMenberList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/getCircleList")
    Observable<String> getDynamicList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/goodsSpec")
    Observable<String> getGoodSspecification(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/CartApi/addCart")
    Observable<String> addShopCar(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/CartApi/getCartList")
    Observable<String> addGoodsCar(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getExpressList")
    Observable<String> addShipPing(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/deliverOrder")
    Observable<String> getFShipPing(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getOrderInfo")
    Observable<String> getSellOrderDetailde(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/setShopCircle")
    Observable<String> setShopCircle(@FieldMap Map<String, Object> map);

    //收藏商品
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/collectGoods")
    Observable<String> collecgGoods(@FieldMap Map<String, Object> map);

    // /卖家退货列表
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getRefundGoodsList")
    Observable<String> sellReturnDetails(@FieldMap Map<String, Object> map);

    // /买家退货列表
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getAllRefundList")
    Observable<String> ReturnDetails(@FieldMap Map<String, Object> map);

    //退货申请
    @Multipart
    @POST("index.php/Api/UserCenterApi/setOrderRefund")
    Observable<String> ReturnApplyFor(@Part List<MultipartBody.Part> file);


    // /帮助中心
    @FormUrlEncoded
    @POST("index.php/Api/ArticleApi/articleList")
    Observable<String> help(@FieldMap Map<String, Object> map);  // /帮助中心

    //帮助中心详情
    @FormUrlEncoded
    @POST("index.php/Api/ArticleApi/articleInfo")
    Observable<String> helpDetail(@FieldMap Map<String, Object> map);

    //意见反馈
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/feedBack")
    Observable<String> feedBack(@FieldMap Map<String, Object> map);

    //买家退货详情
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getRefundInfo")
    Observable<String> ReturnDetails2(@FieldMap Map<String, Object> map);

    //买家退货发货
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/setExpressSn")
    Observable<String> ReturnShipping(@FieldMap Map<String, Object> map);//买家退货发货

    //卖家退款详情
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getRefundInfo")
    Observable<String> sellReturnDetails2(@FieldMap Map<String, Object> map);

    //卖家确认收货物
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/finishOrderRfund")
    Observable<String> ReceiveGoods(@FieldMap Map<String, Object> map);

    //催促买家发货
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/reminderRefundOrder")
    Observable<String> onUrged(@FieldMap Map<String, Object> map);

    //删除退款订单
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/delRefundOrder")
    Observable<String> onDeleteOrder(@FieldMap Map<String, Object> map);

    //买家删除退款订单
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/delRefundOrder")
    Observable<String> onMaiDeleteOrder(@FieldMap Map<String, Object> map);

    ////获取买家个人信息
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getUserInfo ")
    Observable<String> getUserInfo(@FieldMap Map<String, Object> map);

    //获取运费模版
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getExpressTemplateByPage")
    Observable<String> getExpressTemplate(@FieldMap Map<String, Object> map);

    ////删除运费模版
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/delExpressTemplate")
    Observable<String> delExpressTemplate(@FieldMap Map<String, Object> map);///删除运费模版


//    //获取省
//    @FormUrlEncoded
//    @POST("index.php/Core/RegionApi/getRegionList")
//    Observable<String> getAddress(@FieldMap Map<String,Object> map);


    //获取省
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getTemplateRegion")
    Observable<String> getAddress(@FieldMap Map<String, Object> map);


    //添加运费模版
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/setExpressTemplate")
    Observable<String> addExpressTemplate(@FieldMap Map<String, Object> map);

    //添加运费模版
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/goBackRefundOrder")
    Observable<String> goBackRefundOrder(@FieldMap Map<String, Object> map);

    //同意或者拒绝退货
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/agreeRefund")
    Observable<String> sellReturnOkOrNO(@FieldMap Map<String, Object> map);

    //获取余额
    @FormUrlEncoded
    @POST("Api/ShopCenterApi/moneyInfo")
    Observable<String> getprice(@FieldMap Map<String, Object> map);

    //获取应缴纳保证金
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getShopBondMoney")
    Observable<String> getShopBondMoney(@FieldMap Map<String, Object> map);

    //获取应缴纳保证金
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/thawBond")
    Observable<String> thawBond(@FieldMap Map<String, Object> map);


    //收藏店铺/关注店铺 取消
    @FormUrlEncoded
    @POST("/index.php/Api/UserCenterApi/collectShop")
    Observable<String> setCollectShop(@FieldMap Map<String, Object> map);


    //发表评论
    @Multipart
    @POST("index.php/Api/UserCenterApi/setOrderComment")
    Observable<String> publishCommentaries(@Part List<MultipartBody.Part> file);


    //获取买家首页订单信息
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getOrderStatusCount ")
    Observable<String> getOredrData(@FieldMap Map<String, Object> map);


    //发表评论
    @Multipart
    @POST("index.php/Api/UserCenterApi/editUserInfo")
    Observable<String> editUser(@Part List<MultipartBody.Part> file);


    //获取银行卡列表
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getUserBankCardList")
    Observable<String> getBankCardList(@FieldMap Map<String, Object> map);

    //删除银行卡
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/delUserBankCard")
    Observable<String> deleteBankCard(@FieldMap Map<String, Object> map);

    //添加银行卡信息
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/addUserBankCard")
    Observable<String> addBankCard(@FieldMap Map<String, Object> map);

    //获取店铺基本信息
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/getShopInfo")
    Observable<String> addshopUser(@FieldMap Map<String, Object> map);

    //卖家首页订单信息
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/getOrderStatusCount")
    Observable<String> getSellOredrData(@FieldMap Map<String, Object> map);

    //确认订单
    @FormUrlEncoded
    @POST("index.php/Api/CartApi/cartConfirm")
    Observable<String> orderConfirm(@FieldMap Map<String, Object> map);

    //订单确认接口
    @FormUrlEncoded
    @POST("index.php/Api/CartApi/cartConfirm ")
    Observable<String> cartConfirm(@FieldMap Map<String, Object> map);

    //确认订单
    @FormUrlEncoded
    @POST("index.php/Api/OrderApi/addToOrder")
    Observable<String> createOrder(@FieldMap Map<String, Object> map);

    //删除购物车商品
    @FormUrlEncoded
    @POST("index.php/Api/CartApi/delCartGoods")
    Observable<String> deleteCarGoods(@FieldMap Map<String, Object> map);

    //获取限时特惠列表
    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/getActivitylist")
    Observable<String> getTimeGoods(@FieldMap Map<String, Object> map);

    //修改商品规格数量
    @FormUrlEncoded
    @POST("index.php/Api/CartApi/editCartGoodsSpec")
    Observable<String> alterGoodsCount(@FieldMap Map<String, Object> map);

    //修改购物车确定
    @FormUrlEncoded
    @POST("index.php/Api/CartApi/saveCartSpecNumber")
    Observable<String> alterGoods(@FieldMap Map<String, Object> map);

    //首页限时特惠接口
    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/adLimitedFavour")
    Observable<String> getLimitedFavour(@FieldMap Map<String, Object> map);

    //首页商品订制
    @FormUrlEncoded
    @POST("index.php/Api/IndexApi/adOrderWholeSale")
    Observable<String> getOrderWholeSale(@FieldMap Map<String, Object> map);

    //首页商品订制
    @FormUrlEncoded
    @POST("/index.php/Api/IndexApi/adActivityBanner")
    Observable<String> getadvertising(@FieldMap Map<String, Object> map);

    //订制批发列表
    @FormUrlEncoded
    @POST("index.php/Api/GoodsApi/isMadeGoodsList")
    Observable<String> getWholesaleList(@FieldMap Map<String, Object> map);

    //获取店铺分类列表
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/getGoodsCat")
    Observable<String> getShopClassList(@FieldMap Map<String, Object> map);

    //获取店铺分类列表
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/getShopGoodsList/p/1")
    Observable<String> getShopClass(@FieldMap Map<String, Object> map);

    //获取店铺分类下商品
    @FormUrlEncoded
    @POST("index.php/Api/ShopApi/getShopGoodsList/")
    Observable<String> getShopGoodsList(@FieldMap Map<String, Object> map);

    //获取客户评论
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/getClientEvaluate")
    Observable<String> getClentComment(@FieldMap Map<String, Object> map);

    //回复客户评论
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/replyClientMessage")
    Observable<String> getRevertClentComment(@FieldMap Map<String, Object> map);

    //提现申请
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/withdrawCard")
    Observable<String> getapplication(@FieldMap Map<String, Object> map);

    //编辑商品详情
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/goodsEditInfo")
    Observable<String> getGoodsEdit(@FieldMap Map<String, Object> map);

    //删除图片
    @FormUrlEncoded
    @POST("index.php/Api/GoodsEditApi/delGoodsImage")
    Observable<String> deleteImg(@FieldMap Map<String, Object> map);

    //物流信息
    @FormUrlEncoded
    @POST("index.php/Api/UserCenterApi/getExpressTraces")
    Observable<String> getExpressTraces(@FieldMap Map<String, Object> map);

    //订单信息修改
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/chanageOrderPrice")
    Observable<String> getEditOrders(@FieldMap Map<String, Object> map);

    //保存改价
    @FormUrlEncoded
    @POST("index.php/Api/ShopCenterApi/saveChanageOrderPrice")
    Observable<String> getSaveEditOrders(@FieldMap Map<String, Object> map);

    //订单支付
    @FormUrlEncoded
    @POST("index.php/Api/OrderApi/orderPay")
    Observable<String> getPay(@FieldMap Map<String, Object> map);

    //充值
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/shopRecharge")
    Observable<String> recharge(@FieldMap Map<String, Object> map);

    //缴纳保证金
    @FormUrlEncoded
    @POST("index.php/Api/shopCenterApi/shopPayBond")
    Observable<String> getPayshopPayBond(@FieldMap Map<String, Object> map);
    //修改登陆密码
//    @FormUrlEncoded
//    @POST("index.php/Member/MemberApi/modifyLoginPassword")
//    Observable<String> modifyLoginPassword(@Field("code") String param);
//    //修改支付密码
//    @FormUrlEncoded
//    @POST("index.php/Member/MemberApi/modifyPayPassword")
//    Observable<String> modifyPayPassword(@Field("code") String param);

}
