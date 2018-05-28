package top.crossrun.net.services;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface UploadServices {

    @POST()
    Observable<String> upLoad(
            @Url() String url,
            @Body RequestBody Body);
}
