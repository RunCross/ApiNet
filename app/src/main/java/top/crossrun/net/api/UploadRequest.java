package top.crossrun.net.api;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import top.crossrun.net.api.param.FileParam;
import top.crossrun.net.listener.ApiResultListener;
import top.crossrun.net.services.UploadServices;

/**
 * 上传
 */
public class UploadRequest extends Request {

    Class cls;

    Scheduler requestScheduler;

    Scheduler responseScheduler;

    ApiResultListener listener;

    FileParam param;

    public UploadRequest() {
        requestScheduler = Schedulers.io();
        responseScheduler = AndroidSchedulers.mainThread();
    }

    public UploadRequest setRequestScheduler(Scheduler requestScheduler) {
        this.requestScheduler = requestScheduler;
        return this;
    }

    public UploadRequest setResponseScheduler(Scheduler reponseScheduler) {
        this.responseScheduler = reponseScheduler;
        return this;
    }

    public UploadRequest setApiResultListener(ApiResultListener listener) {
        this.listener = listener;
        return this;
    }

    public UploadRequest setParam(FileParam param) {
        this.param = param;
        return this;
    }

    /**
     * 返回结果的实体类
     *
     * @param cls
     */
    public UploadRequest setCls(Class cls) {
        this.cls = cls;
        return this;
    }

    @Override
    public void http() {
        ApiNet.getInstance().create(UploadServices.class)
                .upLoad(param.getUrl(), param.getValues())
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

    @Override
    public void recycle() {
        listener = null;
        param.recycle();
    }
}
