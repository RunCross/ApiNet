package top.crossrun.net.api.param;

import java.util.HashMap;
import java.util.Map;

import okhttp3.RequestBody;
import top.crossrun.net.interf.RecycleAble;

public abstract class BaseParam implements RecycleAble {
    String url;
    Map<String, Object> values;

    public BaseParam setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public BaseParam() {
        values = new HashMap<>();
    }

    public BaseParam addParam(String key, Object value) {
        values.put(key,value);
        return this;
    }


    public Map getMapValues() {
        return values;
    }

    public abstract RequestBody getRequestBodey();

    @Override
    public void recycle() {
        values.clear();
        values=null;
    }
}
