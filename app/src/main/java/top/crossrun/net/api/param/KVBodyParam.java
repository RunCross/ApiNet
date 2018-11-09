package top.crossrun.net.api.param;

import java.net.URLEncoder;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 参数不放url里面，用于post请求
 * body ,Type is String
 */
public class KVBodyParam extends BaseParam {

    int encode = 1;

    String body = "";

    public KVBodyParam() {
        this(1);
    }

    public KVBodyParam(int encode) {
        this.encode = encode;
    }

    public KVBodyParam setBody(String body) {
        this.body = body;
        return this;
    }

    public String getBody(){
        return body;
    }

    @Override
    public RequestBody getRequestBodey() {
        if (1 == encode) {
            return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), URLEncoder.encode(body).toString().getBytes());
        } else {
            return RequestBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), body.toString().getBytes());
        }
    }
}
