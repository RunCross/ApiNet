package top.crossrun.net.api.param;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 参数放url里面，用于get请求
 * key=value , key and value ,Type is String
 */
public class KVUrlParam extends BaseParam {

    @Override
    public String getUrl() {
        String u = super.getUrl();
        if (values.size() > 0) {

            StringBuilder sb = new StringBuilder(u);
            if (!u.contains("?")) {
                sb.append("?");
            }else {
                sb.append("&");
            }
            Set<String> keys = values.keySet();
            for (String key :
                    keys) {
                sb.append(key);
                sb.append("=");
                sb.append(values.get(key));
                sb.append("&");
            }
            u = sb.toString();
        }
        return u;
    }

    @Override
    public RequestBody getRequestBodey() {
        return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), "");
    }

    @Override
    public void recycle() {
        super.recycle();
    }
}
