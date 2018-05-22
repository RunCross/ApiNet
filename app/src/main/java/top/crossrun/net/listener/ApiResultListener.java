package top.crossrun.net.listener;

public abstract class  ApiResultListener<T> {

    public abstract void onRequestResultSucc(T result);
    public abstract void onRequestResultFailed(String errMsg);
}
