package com.lnkj.privateshop.greendao;

import android.content.Context;
import android.util.Log;

import com.lnkj.privateshop.entity.ShopEmchatBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */


public class UserInfoDaoUtils {
    private static final String TAG = UserInfoDaoUtils.class.getSimpleName();
    private DaoManager mManager;

    public UserInfoDaoUtils(Context context) {
        mManager = DaoManager.getInstance();
        mManager.init(context);
    }

    /**
     * 完成meizi记录的插入，如果表未创建，先创建Meizi表
     *
     * @param bean
     * @return
     */
    public boolean insertUserInfo(ShopEmchatBean bean) {
        boolean flag = false;
        flag = mManager.getDaoSession().getShopEmchatBeanDao().insertOrReplace(bean) == -1 ? false : true;
        Log.i(TAG, "insert userinfo :" + flag + "-->" + bean.toString());
        return flag;
    }

    /**
     * 插入多条数据，在子线程操作
     *
     * @param been
     * @return
     */
    public boolean insertUserInfo(final List<ShopEmchatBean> been) {
        boolean flag = false;
        try {
            mManager.getDaoSession().runInTx(new Runnable() {
                @Override
                public void run() {
                    for (ShopEmchatBean bean : been) {
                        mManager.getDaoSession().insertOrReplace(bean);
                    }
                }
            });
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 修改一条数据
     *
     * @param bean
     * @return
     */
    public boolean updateUserInfo(ShopEmchatBean bean) {
        boolean flag = false;
        try {
            mManager.getDaoSession().update(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除单条记录
     *
     * @param bean
     * @return
     */
    public boolean deleteUserInfo(ShopEmchatBean bean) {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().delete(bean);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除所有记录
     *
     * @return
     */
    public boolean deleteAll() {
        boolean flag = false;
        try {
            //按照id删除
            mManager.getDaoSession().deleteAll(ShopEmchatBean.class);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<ShopEmchatBean> queryAllUserInfo() {
        return mManager.getDaoSession().loadAll(ShopEmchatBean.class);
    }

    /**
     * 根据主键id查询记录
     *
     * @param key
     * @return
     */
    public ShopEmchatBean queryUserInfoById(long key) {
        return mManager.getDaoSession().load(ShopEmchatBean.class, key);
    }

    /**
     * 使用native sql进行查询操作
     */
    public List<ShopEmchatBean> queryUserInfoByNativeSql(String sql, String[] conditions) {
        return mManager.getDaoSession().queryRaw(ShopEmchatBean.class, sql, conditions);
    }

//    /**
//     * 使用queryBuilder进行查询
//     *
//     * @return
//     */
//    public List<ShopEmchatBean> queryUserInfoByQueryBuilder(long id) {
//        QueryBuilder<ShopEmchatBean> queryBuilder = mManager.getDaoSession().queryBuilder(ShopEmchatBean.class);
//        return queryBuilder.where(ShopEmchatBeanDao.Properties._id.eq(id)).list();
//    }

    public List<ShopEmchatBean> queryUserInfoByEId(String ease_id) {
        QueryBuilder<ShopEmchatBean> queryBuilder = mManager.getDaoSession().queryBuilder(ShopEmchatBean.class);
        return queryBuilder.where(ShopEmchatBeanDao.Properties.Emchat_username.eq(ease_id)).list();
    }
}
