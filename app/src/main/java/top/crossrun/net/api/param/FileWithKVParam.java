package top.crossrun.net.api.param;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileWithKVParam extends FileParam {
    public RequestBody getValues() {
//        JSONObject obj = new JSONObject();
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        if (files != null && files.size() > 0) {
            Set<String> keySet = files.keySet();
            for (String str :
                    keySet) {
                builder.addFormDataPart("file", str, RequestBody.create(MediaType.parse("application/octet-stream"), files.get(str)));
            }
        }
        if (values != null && values.size() > 0) {
            Set<String> keySet = values.keySet();
            for (String str :
                    keySet) {
                builder.addFormDataPart(str, values.get(str).toString());
//                try {
//                    obj.put(str,keys.get(str));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        }

//        RequestBody param=
//                RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), obj.toString());
//
//        RequestBody fparam=
//                RequestBody.create(okhttp3.MediaType.parse("application/octet-stream"), new File(LauncherApplication.CRASH_Path+File.separator+"test.txt"));

//        MultipartBody.Builder b= new MultipartBody.Builder().setType(MultipartBody.MIXED);
//        b.addPart(param);
//        b.addPart(fparam);

        return builder.build();
    }
}
