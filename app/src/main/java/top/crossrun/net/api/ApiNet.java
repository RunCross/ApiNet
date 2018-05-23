package top.crossrun.net.api;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import top.crossrun.net.listener.ApiResultListener;

public class ApiNet {

    /**
     * 必须在调用之前
     *
     * @param builder
     */
    public static void init(Builder builder) {
        if (builder == null) return;
        NetInner.builder = builder;
    }

    public static void http() {
    }

    public static <T> void post(KVParam param, ApiResultListener listener) {
        NetInner.ins.post(param,listener);
    }

    public static <T> PostRequest post() {
       return new PostRequest();
    }

    public static <T> GetRequest get() {
        return new GetRequest();
    }

    public static void get(KVParam param) {
        NetInner.ins.get(param);
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

    private static class NetInner {
        private static ApiManager ins;

        private static Builder builder;

        static {
            if (null == builder) {
                builder = new Builder();
            }
            ins = new ApiManager(builder);
        }
    }
}
