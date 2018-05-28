package top.crossrun.net.api;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.Response;

public class GetRequest<T> extends Request<T> {


    @Override
    public void http() {
        Observable
                .create(new ObservableOnSubscribe<T>() {

                    /**
                     * Called for each Observer that subscribes.
                     *
                     * @param e the safe emitter instance, never null
                     * @throws Exception on error
                     */
                    @Override
                    public void subscribe(ObservableEmitter<T> e) throws Exception {
                        okhttp3.Request request = new okhttp3.Request.Builder()
                                .url(param.getUrl())//地址
                                .post(param.getRequestBodey())//添加请求体
                                .build();
                        try {
                            Response response =ApiNet.newCall(request).execute();
                            String resp = response.body().string();
                            if (cls==String.class){
                                e.onNext((T)resp);
                            }else {
                                e.onNext((T) gson.fromJson(resp,cls));
                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                            e.onError(e1);
                        }
                    }
                })
                .subscribeOn(requestScheduler)
                .observeOn(responseScheduler)
        .subscribe(new Observer<T>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T value) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

//    public void http() {
//        ApiNet.getInstance().create(StringServices.class)
//                .get(param.getUrl(), param.getMapValues())
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
    public void recycle() {
        listener = null;
        param.recycle();
    }

}
