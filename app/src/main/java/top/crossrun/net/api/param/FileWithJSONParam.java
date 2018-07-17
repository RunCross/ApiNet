package top.crossrun.net.api.param;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileWithJSONParam extends FileParam {
    @Override
    public RequestBody getRequestBodey() {
        JSONObject obj = new JSONObject();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (files != null && files.size() > 0) {
            Set<String> keySet = files.keySet();
            for (String str :
                    keySet) {
                builder.addFormDataPart(filekeys.get(str), str, RequestBody.create(MediaType.parse("application/octet-stream"), files.get(str)));
            }
        }
        if (values != null && values.size() > 0) {
            Set<String> keySet = values.keySet();
            for (String str :
                    keySet) {
//                builder.addFormDataPart(str, values.get(str).toString());
                try {
                    obj.put(str, values.get(str));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        RequestBody param =
                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());

        builder.addPart(param);

        return builder.build();
    }
}
