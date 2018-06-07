package top.crossrun.net.api.param;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 参数不放url里面，用于post请求
 * key=value , key and value ,Type is String
 */
public class KVStringParam extends BaseParam {

    @Override
    public RequestBody getRequestBodey() {
        Set<String> keys = values.keySet();
        StringBuilder sb = new StringBuilder();
        for (String key :
                keys) {
            sb.append(key);
            sb.append("=");
            sb.append(values.get(key));
            sb.append("&");
        }
        return RequestBody.create(MediaType.parse("text/plain"), sb.toString());
    }
}
