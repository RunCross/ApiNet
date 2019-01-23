package top.crossrun.net.listener;

public interface ApiResultListener<T> {

    void onRequestResultSucc(T result);

    void onRequestResultFailed(Throwable errMsg);
}
