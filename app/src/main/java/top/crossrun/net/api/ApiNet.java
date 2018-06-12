package top.crossrun.net.api;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Interceptor;
import retrofit2.Retrofit;

public class ApiNet{

    /**
     *
     * @param builder
     */
    public static void init(Builder builder) {
        if (builder == null) return;
        NetInner.ins = new ApiManager(builder);
    }

    public static  <T>PostRequest<T> post(Class<T> s) {
       return new PostRequest<T>(s);
    }

//    public static <T>ApiNet<T> create(Class<T> s){
//        ApiNet<T> a = new ApiNet<>();
//        return a;
//    }

    @SuppressWarnings("unchecked")
    public static <T>Request<T> get(Class<T> s) {
        return new GetRequest<T>(s);
    }

   public static <T>UploadRequest<T> upload(Class<T> s){
        return new UploadRequest<T>(s);
   }

    public static class Builder {
        String rootUrl;
        int CONNECT_TIMEOUT = 30;
        int READ_TIMEOUT = 30;
        int WRITE_TIMEOUT = 30;
        boolean debug = true;

        SSLSocketFactory sslSocketFactory;
        X509TrustManager trustManager;
        HostnameVerifier hostnameVerifier;

        List<Interceptor> interceptors;

        /**
         * 推荐斜杠结尾
         *
         * @param rootUrl
         */
        public Builder setRootUrl(String rootUrl) {
            this.rootUrl = rootUrl;
            return this;
        }

        public Builder setCONNECT_TIMEOUT(int CONNECT_TIMEOUT) {
            this.CONNECT_TIMEOUT = CONNECT_TIMEOUT;
            return this;
        }

        public Builder setREAD_TIMEOUT(int READ_TIMEOUT) {
            this.READ_TIMEOUT = READ_TIMEOUT;
            return this;
        }

        public Builder setWRITE_TIMEOUT(int WRITE_TIMEOUT) {
            this.WRITE_TIMEOUT = WRITE_TIMEOUT;
            return this;
        }

        public Builder setDebug(boolean debug) {
            this.debug = debug;
            return this;
        }

        public Builder setSSLFactory(SSLSocketFactory sslSocketFactory, X509TrustManager trustManager) {
            this.sslSocketFactory = sslSocketFactory;
            this.trustManager = trustManager;
            return this;
        }

        public Builder setHostnameVerifier(HostnameVerifier hostnameVerifier) {
            this.hostnameVerifier = hostnameVerifier;
            return this;
        }

        public Builder addInterceptor(Interceptor  interceptor){
            if (interceptors==null){
                interceptors = new ArrayList<>();
            }
            interceptors.add(interceptor);
            return this;
        }

        public String getRootUrl() {
            return rootUrl;
        }

        public int getCONNECT_TIMEOUT() {
            return CONNECT_TIMEOUT;
        }

        public int getREAD_TIMEOUT() {
            return READ_TIMEOUT;
        }

        public int getWRITE_TIMEOUT() {
            return WRITE_TIMEOUT;
        }

        public boolean getDebug() {
            return debug;
        }

    }

    protected static Retrofit getInstance(){
        return NetInner.ins.getInstance();
    }

    protected static Call newCall(okhttp3.Request request){
        return NetInner.ins.newCall(request);
    }

    private static class NetInner {
        private static ApiManager ins = new ApiManager(new Builder());
    }
}
