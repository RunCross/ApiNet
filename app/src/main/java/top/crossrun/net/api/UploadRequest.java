package top.crossrun.net.api;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import top.crossrun.net.listener.ApiResultListener;

/**
 * 上传
 */
public class UploadRequest extends Request {

    Class cls;

    Scheduler requestScheduler;

    Scheduler responseScheduler;

    ApiResultListener listener;

    KVParam param;

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

    public UploadRequest setParam(KVParam param) {
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

    }

    @Override
    public void recycle() {
        listener = null;
        param.recycle();
    }
}
