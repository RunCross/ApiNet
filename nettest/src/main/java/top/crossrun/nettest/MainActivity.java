package top.crossrun.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.io.File;

import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.CompositeRecycle;
import top.crossrun.net.api.param.FileWithJSONParam;
import top.crossrun.net.api.param.FileWithKVParam;
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
//        ApiNet.init(new ApiNet.Builder());
//        ApiNet.get(new KVUrlParam()
//                .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do?data=ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
//                .setParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620"));
//        ApiNet.post(new KVUrlParam()
//                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do")
//                        .setParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
//                        .setApiSchedulerListener(new ApiSchedulerListener() {
//                            @Override
//                            public Scheduler requestScheduler() {
//                                return Schedulers.io();
//                            }
//
//                            @Override
//                            public Scheduler responseScheduler() {
//                                return AndroidSchedulers.mainThread();
//                            }
//                        })
//                , new ApiResultListener<UpdateResult>() {
//
//                    @Override
//                    public void onRequestResultSucc(UpdateResult result) {
//                        Log.e("onRequestResultSucc", result.updateUrl);
//                    }
//
//                    @Override
//                    public void onRequestResultFailed(Throwable errMsg) {
//
//                    }
//                }
//        );


//        ApiNet.create(UpdateResult.class).get().setCls(UpdateResult.class);
//        ApiNet.get(UpdateResult.class)
//                .setParam(new KVUrlParam()
//                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do?data=ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
//                        .addParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
//                )
//                .setApiResultListener(new ApiResultListener<UpdateResult>() {
//                    @Override
//                    public void onRequestResultSucc(UpdateResult result) {
//                        Log.e("===", result.updateUrl);
//                    }
//
//                    @Override
//                    public void onRequestResultFailed(Throwable errMsg) {
//                        Log.e("===", errMsg.getMessage());
//                    }
//                }).http();

//        ApiNet.get(String.class)
//                .registerRecycle(compositeRecycle)
//                .setParam(new KVUrlParam()
//                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do")
//                        .addParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
//                )
//                .setApiResultListener(new ApiResultListener<String>() {
//                    @Override
//                    public void onRequestResultSucc(String result) {
//                        Log.e("===", result);
//                    }
//
//                    @Override
//                    public void onRequestResultFailed(Throwable errMsg) {
//                        Log.e("===", errMsg.getMessage());
//                    }
//                }).http();


//        ApiNet
//                .post()
//                .registerRecycle(compositeRecycle)
//                .setParam(new KVUrlParam()
//                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do")
//                        .addParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620"))
//                .setCls(UpdateResult.class)
//                .setApiResultListener(new ApiResultListener<UpdateResult>() {
//                    @Override
//                    public void onRequestResultSucc(UpdateResult result) {
//                        Log.e("onRequestResultSucc", result.updateUrl);
//                        ((TextView) findViewById(R.id.text)).setText(result.updateUrl);
//                    }
//
//                    @Override
//                    public void onRequestResultFailed(Throwable errMsg) {
//                    }
//                })
//                .http();
//        ApiNet.upload().http();

        ApiNet.post(UpdateResult.class)
                .setParam(new FileWithJSONParam()
//                        .addFile("flashss0.jpg", "file", new File("/sdcard/Pictures/Screenshots/Screenshot_2018-04-13-16-46-48.png"))
//                        .addFile("flashss.jpg", "file", new File("/sdcard/moa/flash.jpg"))
                        .addParam("test","d")
                        .addParam("sss","xxxc")
                        .setUrl("http://10.70.148.34:9292/upfile"))
                .registerRecycle(compositeRecycle)
                .setApiResultListener(new ApiResultListener<UpdateResult>() {
                    @Override
                    public void onRequestResultSucc(UpdateResult result) {
                        text.setText(result.result);
                    }

                    @Override
                    public void onRequestResultFailed(Throwable errMsg) {
                        text.setText(errMsg.getMessage());
                    }
                }).http();

    }

    @Override
    protected void onDestroy() {
        if (compositeRecycle != null) {
            compositeRecycle.recycle();
        }
        super.onDestroy();
    }
}
