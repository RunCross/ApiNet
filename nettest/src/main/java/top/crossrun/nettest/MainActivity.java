package top.crossrun.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.CompositeRecycle;
import top.crossrun.net.api.DownloadProgressRequestListener;
import top.crossrun.net.api.param.KVStringParam;
import top.crossrun.net.api.param.KVUrlParam;
import top.crossrun.net.listener.ApiResultListener;

public class MainActivity extends AppCompatActivity {

    CompositeRecycle compositeRecycle;

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);
        compositeRecycle = new CompositeRecycle();

//        post();
//post2();
        download();

    }

    private void download() {

        ApiNet.download()
                .setDownloadPathAndName("/sdcard/test/test.png", true)
                .setDownloadRequestListener(new DownloadProgressRequestListener() {
                    @Override
                    public void onDownloadProgress(long total, long progress) {
                        Log.e("top.crossrun", String.format("%d,%d", total, progress));
                    }
                })
                .setParam(new KVUrlParam().setUrl(""))
                .setApiResultListener(new ApiResultListener<String>() {
                    @Override
                    public void onRequestResultSucc(String result) {
                        Log.e("top.crossrun", result);
                    }

                    @Override
                    public void onRequestResultFailed(Throwable errMsg) {
                        Log.e("top.crossrun", errMsg.toString());
                    }
                })
                .http();
    }

    public void post() {
        ApiNet.post(String.class)
                .setParam(new KVStringParam()
                        .setUrl("http://61.175.223.170/law/client/enterprisepatrol.do")
                        .addParam("invoke", "updatepositioninfo")
                        .addParam("phone", "87120315")
                        .addParam("moduleid", "4028b88150f98f170150f9a22afc0001")
                        .addParam("phoneMark", "")
                        .addParam("longitude", "120.152791")
                        .addParam("jwaddress", "浙江省玉环市中国无菌医疗器械装备制造基地西部")
                        .addParam("linkman", "陈财能")
                        .addParam("latitude", "30.267446")
                        .addParam("qybh", "3310210700517264")
                )
                .setApiResultListener(
                        new ApiResultListener<String>() {
                            @Override
                            public void onRequestResultSucc(String result) {
                                Log.e("top.crossrun", result);
                            }

                            @Override
                            public void onRequestResultFailed(Throwable errMsg) {
                                errMsg.printStackTrace();
                            }
                        }
                )
                .http();
    }

    public void post2() {
        ApiNet.post(String.class)
                .setParam(new KVUrlParam()
                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do?data=ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
                        .addParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620"))
                .setApiResultListener(new ApiResultListener<String>() {
                    @Override
                    public void onRequestResultSucc(String result) {
                        Log.e("top.crossrun", result);
                    }

                    @Override
                    public void onRequestResultFailed(Throwable errMsg) {
                        Log.e("top.crossrun", errMsg.toString());
                    }
                })
                .registerRecycle(compositeRecycle)
                .http();


    }

    @Override
    protected void onDestroy() {
        if (compositeRecycle != null) {
            compositeRecycle.recycle();
        }
        super.onDestroy();
    }
}
