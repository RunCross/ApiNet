package top.crossrun.net.services;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface StringServices {
    @FormUrlEncoded
    @POST()
    Observable<String> post(@Url() String url, @FieldMap Map<String, Object> data);

    @GET()
    Observable<String> get(@Url() String url, @QueryMap Map<String, Object> data);
}