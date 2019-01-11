package top.crossrun.nettest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.DownloadRequest;
import top.crossrun.net.api.param.KVStringParam;
import top.crossrun.net.api.param.KVUrlParam;
import top.crossrun.net.listener.ApiResultListener;

public class MainActivity extends AppCompatActivity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
//
        String path = Environment.getExternalStorageDirectory() + "/apk/temp.a";
        Log.e("top.crossrun",path);

        ApiNet.download(path, true)
                .setParam(new KVUrlParam().setUrl("http://192.168.31.165:9393/apk/%E5%89%91%E4%B8%89%E7%AD%94%E9%A2%98%E5%99%A8.apk"))
                .setApiResultListener(new DownloadRequest.OnDownloadProcessListener() {
                    @Override
                    public void onRequestResultSucc(String result) {
                        Log.e("top.crossrun", result);
                    }

                    @Override
                    public void onRequestResultFailed(Throwable errMsg) {
                        errMsg.printStackTrace();
                    }

                    @Override
                    public void onRequestProcess(int progress) {
                        Log.e("top.crossrun", progress+"");
                    }
                })
                .http();

    }
}
