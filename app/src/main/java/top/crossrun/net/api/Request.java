package top.crossrun.net.api;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import top.crossrun.net.api.param.BaseParam;
import top.crossrun.net.interf.RecycleAble;
import top.crossrun.net.listener.ApiResultListener;

public abstract class Request<T> implements RecycleAble {

    BaseParam param;

    Class<T> cls;

    Scheduler requestScheduler;

    Scheduler responseScheduler;

    Scheduler parseScheduler;

    ApiResultListener<T> listener;

    Gson gson;

    Map<String,String> header;

    public Request(Class<T> cls) {
        gson = new Gson();
        requestScheduler = Schedulers.io();
        parseScheduler = Schedulers.io();
        responseScheduler = AndroidSchedulers.mainThread();
        this.cls = cls;
        header = new HashMap<>();
    }

    public Request<T> registerRecycle(CompositeRecycle cr) {
        cr.add(this);
        return this;
    }

    public Request<T> setParam(BaseParam param) {
        this.param = param;
        return this;
    }

    public Request<T> setRequestScheduler(Scheduler requestScheduler) {
        this.requestScheduler = requestScheduler;
        return this;
    }

    public Request<T> setResponseScheduler(Scheduler reponseScheduler) {
        this.responseScheduler = reponseScheduler;
        return this;
    }

    public Request<T> setParseScheduler(Scheduler parseScheduler) {
        this.parseScheduler = parseScheduler;
        return this;
    }

    public Scheduler getRequestScheduler() {
        return requestScheduler;
    }

    public Scheduler getResponseScheduler() {
        return responseScheduler;
    }

    public Scheduler getParseScheduler() {
        return parseScheduler;
    }

    public Request<T> setApiResultListener(ApiResultListener<T> listener) {
        this.listener = listener;
        return this;
    }

    public Request<T>  addHeader(String key,String value) {
        header.put(key,value);
        return this;
    }

    /**
     * 返回结果的实体类
     *
     * @param cls
     */
    public Request<T> setCls(Class<T> cls) {
        this.cls = cls;
        return this;
    }

    public void http(){
        getRequestObservable()
                .subscribeOn(requestScheduler)
                .observeOn(parseScheduler)
                .map(new Function<String, T>() {
                    /**
                     * Apply some calculation to the input value and return some other value.
                     *
                     * @param s the input value
                     * @return the output value
                     * @throws Exception on error
                     */
                    @Override
                    public T apply(String s) throws Exception {
                        T result = gson.fromJson(s, cls);
                        return result;
                    }
                })
                .observeOn(responseScheduler)
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(T value) {
                        if (listener != null) {
                            listener.onRequestResultSucc(value);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener != null) {
                            listener.onRequestResultFailed(e);
                        }
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    IListener iListener;

    public Request<T> setiListener(IListener iListener) {
        this.iListener = iListener;
        return this;
    }

    public interface IListener<T> {
        void list(T obj);
    }

    protected okhttp3.Request.Builder getRequestBuilder(){
        okhttp3.Request.Builder b = new okhttp3.Request.Builder();
        if (header!=null&&header.size()>0){
            Set<String> keys = header.keySet();
            for (String key :
                    keys) {
                b.addHeader(key, header.get(key));
            }
        }
        return b;
    }

    protected abstract Observable<String> getRequestObservable();

    @Override
    public void recycle() {
        listener = null;
        param.recycle();
        param=null;
    }
}
