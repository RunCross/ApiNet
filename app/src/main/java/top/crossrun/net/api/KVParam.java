package top.crossrun.net.api;

import java.util.HashMap;
import java.util.Map;

import top.crossrun.net.listener.ApiSchedulerListener;

public class KVParam extends BaseParam {
    Map<String, Object> values;
    String url;
    ApiSchedulerListener apiSchedulerListener;

    public KVParam() {
        values = new HashMap<>();
    }

    public KVParam setUrl(String url) {
        this.url = url;
        return this;
    }

    public KVParam setParam(String key, Object value) {
        values.put(key,value);
        return this;
    }

    public Map getValues(){
        return values;
    }


    public ApiSchedulerListener getApiSchedulerListener() {
        return apiSchedulerListener;
    }

    public KVParam setApiSchedulerListener(ApiSchedulerListener apiSchedulerListener) {
        this.apiSchedulerListener = apiSchedulerListener;
        return this;
    }
}
