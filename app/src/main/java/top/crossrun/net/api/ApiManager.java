package top.crossrun.net.api;

import android.text.TextUtils;
import android.util.Log;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import top.crossrun.net.listener.ApiResultListener;
import top.crossrun.net.services.StringServices;

class ApiManager {
    private static Retrofit instance;

    ApiManager(ApiNet.Builder builder) {
        OkHttpClient.Builder b = new OkHttpClient.Builder()
                .readTimeout(builder.READ_TIMEOUT, TimeUnit.SECONDS)//设置读取超时时间
                .connectTimeout(builder.CONNECT_TIMEOUT, TimeUnit.SECONDS)//设置连接超时时间
                .writeTimeout(builder.WRITE_TIMEOUT, TimeUnit.SECONDS);

        if (builder.sslSocketFactory != null && builder.trustManager != null) {
            b.sslSocketFactory(builder.sslSocketFactory, builder.trustManager);
        }

        if (builder.hostnameVerifier != null) {
            b.hostnameVerifier(builder.hostnameVerifier);
        }

        if (builder.debug) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            b.addInterceptor(logging);
        }

        if (builder.interceptors != null && builder.interceptors.size() > 0) {
            for (Interceptor inter :
                    builder.interceptors) {
                b.addInterceptor(inter);
            }
        }

        instance = new Retrofit.Builder()
                .baseUrl(TextUtils.isEmpty(builder.rootUrl) ? "https://www.baidu.com" : builder.rootUrl)
                .client(b.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
    }

    public void get(KVParam param) {
        Scheduler request;
        Scheduler response;
        if (param.apiSchedulerListener != null) {
            request = param.apiSchedulerListener.requestScheduler();
            response = param.apiSchedulerListener.responseScheduler();
        } else {
            request = Schedulers.io();
            response = AndroidSchedulers.mainThread();
        }
        instance
                .create(StringServices.class)
                .get(param.url, param.getValues())
                .subscribeOn(request)
                .observeOn(response)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        Log.e("crossrun", value);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("crossrun", e.getMessage());
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public <T> void post(KVParam param, final ApiResultListener listener) {
        Scheduler request;
        Scheduler response;
        if (param.apiSchedulerListener != null) {
            request = param.apiSchedulerListener.requestScheduler();
            response = param.apiSchedulerListener.responseScheduler();
        } else {
            request = Schedulers.io();
            response = Schedulers.io();
        }
        instance
                .create(StringServices.class)
                .post(param.url, param.getValues())
                .subscribeOn(request)
                .observeOn(response)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        if (listener==null){
                            return;
                        }
                        listener.onRequestResultSucc(listener.parse(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener==null){
                            return;
                        }
                        listener.onRequestResultFailed(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
