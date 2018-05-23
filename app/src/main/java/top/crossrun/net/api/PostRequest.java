package top.crossrun.net.api;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.crossrun.net.listener.ApiResultListener;
import top.crossrun.net.services.StringServices;

public class PostRequest extends Request {

    Class cls;

    Scheduler requestScheduler;

    Scheduler responseScheduler;

    ApiResultListener listener;

    KVParam param;

    public PostRequest() {
        requestScheduler = Schedulers.io();
        responseScheduler = Schedulers.io();
    }

    public PostRequest setRequestScheduler(Scheduler requestScheduler) {
        this.requestScheduler = requestScheduler;
        return this;
    }

    public PostRequest setResponseScheduler(Scheduler reponseScheduler) {
        this.responseScheduler = reponseScheduler;
        return this;
    }

    public PostRequest setApiResultListener(ApiResultListener listener) {
        this.listener = listener;
        return this;
    }

    public PostRequest setParam(KVParam param) {
        this.param = param;
        return this;
    }

    /**
     * 返回结果的实体类
     *
     * @param cls
     */
    public PostRequest setCls(Class cls) {
        this.cls = cls;
        return this;
    }

    public void http() {
        ApiNet.getInstance().create(StringServices.class)
                .post(param.url, param.getValues())
                .subscribeOn(requestScheduler)
                .observeOn(responseScheduler)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String value) {
                        if (listener == null) {
                            return;
                        }
                        if (cls!=null){
                            if (cls == String.class){
                                listener.onRequestResultSucc(value);
                            }else {
                                try {
                                    listener.onRequestResultSucc(ObjectParseUtils.pasrse(value,cls));
                                }catch (Exception e){
                                    listener.onRequestResultFailed(e);
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (listener == null) {
                            return;
                        }
                        listener.onRequestResultFailed(e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
