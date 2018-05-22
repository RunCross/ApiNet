package top.crossrun.net.api;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class TrustAllCerts implements X509TrustManager {
    public TrustAllCerts() {
    }

    public void checkClientTrusted(X509Certificate[] chain, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init((KeyManager[])null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception var2) {
            ;
        }

        return ssfFactory;
    }
}
