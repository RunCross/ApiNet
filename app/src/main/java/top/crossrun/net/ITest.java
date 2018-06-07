package top.crossrun.net;

import top.crossrun.net.listener.ApiResultListener;

public class ITest<T> {
    Class<T> s;
    ApiResultListener<T> listener;

    public ITest<T> setS(Class<T> s) {
        return this;
    }

    public void setListener(ApiResultListener<T> listener) {
        this.listener = listener;
    }
}
