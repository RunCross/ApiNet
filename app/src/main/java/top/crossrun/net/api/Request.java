package top.crossrun.net.api;

import com.google.gson.Gson;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.crossrun.net.api.param.BaseParam;
import top.crossrun.net.interf.RecycleAble;
import top.crossrun.net.listener.ApiResultListener;

public abstract class Request<T> implements RecycleAble {

    BaseParam param;

    Class cls;

    Scheduler requestScheduler;

    Scheduler responseScheduler;

    ApiResultListener  listener;

    Gson gson;

    public Request(){
        gson = new Gson();
        requestScheduler = Schedulers.io();
        responseScheduler = AndroidSchedulers.mainThread();
        cls = String.class;
    }

    public Request registerRecycle(CompositeRecycle cr) {
        cr.add(this);
        return this;
    }

    public Request setParam(BaseParam param){
        this.param = param;
        return this;
    }

    public Request setRequestScheduler(Scheduler requestScheduler) {
        this.requestScheduler = requestScheduler;
        return this;
    }

    public Request setResponseScheduler(Scheduler reponseScheduler) {
        this.responseScheduler = reponseScheduler;
        return this;
    }

    public Request setApiResultListener(ApiResultListener listener) {
        this.listener = listener;
        return this;
    }

    /**
     * 返回结果的实体类
     *
     * @param cls
     */
    @SuppressWarnings("unchecked")
    public Request setCls(Class cls) {
        this.cls = cls;
        return this;
    }

    public abstract void http();

    IListener iListener;

    public Request setiListener(IListener iListener) {
        this.iListener = iListener;
        return this;
    }

    public interface IListener<T>{
        void list(T obj);
    }
}
