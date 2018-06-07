package top.crossrun.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.CompositeRecycle;
import top.crossrun.net.api.param.KVUrlParam;
import top.crossrun.net.listener.ApiResultListener;

public class MainActivity extends AppCompatActivity {

    CompositeRecycle compositeRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        ApiNet.post(String.class)
                .setParam(new KVUrlParam()
                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do")
                        .addParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
                )
                .setApiResultListener(new ApiResultListener<String>() {
                    @Override
                    public void onRequestResultSucc(String result) {
                        Log.e("===", result);
                    }

                    @Override
                    public void onRequestResultFailed(Throwable errMsg) {
                        Log.e("===", errMsg.getMessage());
                    }
                }).http();


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
    }

    @Override
    protected void onDestroy() {
        if (compositeRecycle != null) {
            compositeRecycle.recycle();
        }
        super.onDestroy();
    }
}
