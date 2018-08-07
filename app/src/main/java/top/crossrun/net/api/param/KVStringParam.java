package top.crossrun.net.api.param;

import java.net.URLEncoder;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.http.FormUrlEncoded;

/**
 * 参数不放url里面，用于post请求
 * key=value , key and value ,Type is String
 */
public class KVStringParam extends BaseParam {

    int encode = 1;

    public KVStringParam() {
        this(1);
    }

    public KVStringParam(int encode) {
        this.encode = encode;
    }

    @Override
    public RequestBody getRequestBodey() {
        Set<String> keys = values.keySet();
        StringBuilder sb = new StringBuilder();
        for (String key :
                keys) {
            sb.append(key);
            sb.append("=");
            if (1 == encode) {
                sb.append(URLEncoder.encode(values.get(key).toString()));
            } else {
                sb.append(values.get(key).toString());
            }
            sb.append("&");
        }
        return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), sb.toString().getBytes());
    }
}
