package top.crossrun.net.api.param;

import android.util.Log;

import java.util.Set;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileWithKVParam extends FileParam {

    @Override
    public RequestBody getRequestBodey() {
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
                Object value = values.get(str);
                if (value==null){
                    Log.e("top.crossrun",str+"'s valus is null");
                    continue;
                }
                builder.addFormDataPart(str, value.toString());
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
