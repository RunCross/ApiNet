package top.crossrun.net.api;

import com.google.gson.Gson;

public class ObjectParseUtils {

    private static Gson gson = new Gson();

    public static <T> T pasrse(String json,Class<T> classOfT){
        return gson.fromJson(json,classOfT);
    }
}
