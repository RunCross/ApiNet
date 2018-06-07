package top.crossrun.net.api.param;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 传递json格式数据
 */
public class JsonParam extends BaseParam {
    @Override
    public RequestBody getRequestBodey() {
        Set<String> keys = values.keySet();
        JSONObject jsonObject = new JSONObject();
        try {
            for (String key :
                    keys) {
                jsonObject.put(key, values.get(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json"),jsonObject.toString());
    }
}
