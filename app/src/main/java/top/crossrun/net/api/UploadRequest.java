package top.crossrun.net.api;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Response;

/**
 * 上传
 */
public class UploadRequest<T> extends Request<T> {


    public UploadRequest(Class<T> cls) {
        super(cls);
    }

//    @Override
//    public void http() {
//        ApiNet.getInstance().create(UploadServices.class)
//                .upLoad(param.getUrl(), param.getRequestBodey())
//                .subscribeOn(requestScheduler)
//                .observeOn(responseScheduler)
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(String value) {
//                        if (listener == null) {
//                            return;
//                        }
//                        if (cls!=null){
//                            if (cls == String.class){
//                                listener.onRequestResultSucc(value);
//                            }else {
//                                try {
//                                    listener.onRequestResultSucc(ObjectParseUtils.pasrse(value,cls));
//                                }catch (Exception e){
//                                    listener.onRequestResultFailed(e);
//                                }
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        if (listener == null) {
//                            return;
//                        }
//                        listener.onRequestResultFailed(e);
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//    }

    @Override
    public Observable<String> getRequestObservable() {
        return Observable
                .create(new ObservableOnSubscribe<String>() {

                    /**
                     * Called for each Observer that subscribes.
                     *
                     * @param e the safe emitter instance, never null
                     * @throws Exception on error
                     */
                    @Override
                    public void subscribe(ObservableEmitter<String> e) {
                        okhttp3.Request request = getRequestBuilder()
                                .url(param.getUrl())//地址
                                .post(param.getRequestBodey())//添加请求体
                                .build();
                        try {
                            Response response = createCall(request).execute();
                            String resp = response.body().string();
                            e.onNext(resp);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            e.onError(e1);
                        }
                        e.onComplete();
                    }
                });
    }

}
