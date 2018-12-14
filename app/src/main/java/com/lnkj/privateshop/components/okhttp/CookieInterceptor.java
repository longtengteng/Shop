package com.lnkj.privateshop.components.okhttp;

import android.text.TextUtils;

import java.io.IOException;
import java.net.URLEncoder;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by sll on 2016/2/23.
 */
public class CookieInterceptor implements Interceptor {



  public CookieInterceptor() {
  }

  @Override
  public Response intercept(Chain chain) throws IOException {
    Request original = chain.request();
    if (!original.url()
        .toString()
        .contains("loginUsernameEmail")) {
      Request request = original.newBuilder()
          .addHeader("Cookie", "u=" + URLEncoder.encode("") + ";")
          .build();
      return chain.proceed(request);
    } else {
      for (String header : chain.proceed(original).headers("Set-Cookie")) {
        if (header.startsWith("u=")) {
          String cookie = header.split(";")[0].substring(2);
          if (!TextUtils.isEmpty(cookie)) {
//            Constants.Cookie = cookie;
          }
        }
      }
    }
    return chain.proceed(original);
  }
}
