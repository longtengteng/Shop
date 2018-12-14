package com.hyphenate.easeui.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.easeui.EaseUI;
import com.hyphenate.easeui.R;
import com.hyphenate.easeui.domain.EaseUser;

public class EaseUserUtils {

    static EaseUI.EaseUserProfileProvider userProvider;

    static {
        userProvider = EaseUI.getInstance().getUserProfileProvider();
    }

    public static EMGroup getGroupInfo(String groupId, ImageView logoView) {
        if (null != userProvider) {
            return userProvider.getGroup(groupId, logoView);
        }
        return null;
    }

    public static EMGroup getGroupInfo(String groupId, TextView nameView) {
        if (null != userProvider) {
            return userProvider.getGroup(groupId, nameView);
        }
        return null;
    }

    /**
     * get EaseUser according username
     *
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username, ImageView avaterView, TextView nickView) {
        if (userProvider != null)
            return userProvider.getUser(username, avaterView, nickView);

        return null;
    }

    public static EaseUser getUserInfoSendAtString(String username, String appendString,
                                                   TextView nickView) {
        if (userProvider != null)
            return userProvider.getUser(username, appendString, nickView);

        return null;
    }

    /**
     * get EaseUser according username
     *
     * @param username
     * @return
     */
    public static EaseUser getUserInfo(String username) {
        if (userProvider != null)
            return userProvider.getUser(username);

        return null;
    }

    public static void setUserInfo(final Context context, String username, final ImageView imageView,
                                   final TextView textView) {
        EaseUser user = getUserInfo(username, imageView, textView);
        if (user != null) {
            if (null != imageView) {

                if (null != user.getAvatar()) {
                    Glide.with(context).load(user.getAvatar()).
                            transform(new CircleTransform(context)).
                            error(R.drawable.ease_default_avatar).
                            into(imageView);
                } else {
                    Glide.with(context).load(R.drawable.ease_default_avatar).
                            transform(new CircleTransform(context)).
                            into(imageView);
                }
            }
            if (null != textView) {

                if (user.getNick() != null) {
                    textView.setText(user.getNick());
                } else {
                    textView.setText(username);
                }
            }
        } else {
            if (null != imageView) {

                Glide.with(context).load(R.drawable.ease_default_avatar).
                        transform(new CircleTransform(context)).
                        into(imageView);
            }
            if (null != textView) {
                textView.setText(username);
            }
        }
    }

    /**
     * set user avatar
     *
     * @param username
     */
    public static void setUserAvatar(final Context context, String username, final ImageView imageView) {
        EaseUser user = getUserInfo(username, imageView, null);
        if (user != null && user.getAvatar() != null) {

            Glide.with(context).load(user.getAvatar()).
                    transform(new CircleTransform(context)).
                    error(R.drawable.ease_default_avatar).
                    into(imageView);
        } else {
            Glide.with(context).load(R.drawable.ease_default_avatar).
                    transform(new CircleTransform(context)).
                    into(imageView);
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView) {
        if (textView != null) {
            EaseUser user = getUserInfo(username, null, textView);
            if (user != null && user.getNick() != null) {
                textView.setText(user.getNick());
            } else {
                textView.setText(username);
            }
        }
    }

    /**
     * set user's nickname
     */
    public static void setUserNick(String username, TextView textView, String atString) {
        if (textView != null) {
            EaseUser user = getUserInfoSendAtString(username, atString, textView);
            if (user != null && user.getNick() != null) {
                textView.setText(atString + user.getNick());
            } else {
                textView.setText(atString + username);
            }
        }
    }

    public static void getGroupImg(Context context, String groupId, ImageView avatar) {
        EMGroup group = getGroupInfo(groupId, avatar);
        if (null != group && group.getDescription() != null && null != avatar) {
            Glide.with(context).load(group.getDescription()).
                    transform(new GlideRoundTransform(context, 8)).
                    into(avatar);
        }
    }

    public static void getGroupName(Context context, String groupId, TextView nameView) {
        EMGroup group = getGroupInfo(groupId, nameView);
        if (null != group && group.getGroupName() != null && null != nameView) {
            nameView.setText("" + group.getGroupName());
        }
    }

    public static class CircleTransform extends BitmapTransformation {
        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;

            // TODO this could be acquired from the pool too
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);

            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }

    public static class GlideRoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;

            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }
}
