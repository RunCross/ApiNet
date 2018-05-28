package top.crossrun.nettest;

import android.icu.lang.UProperty;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.CompositeRecycle;
import top.crossrun.net.api.GetRequest;
import top.crossrun.net.api.Request;
import top.crossrun.net.api.param.KVParam;
import top.crossrun.net.listener.ApiResultListener;

public class MainActivity extends AppCompatActivity {

    CompositeRecycle compositeRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeRecycle = new CompositeRecycle();
//        ApiNet.init(new ApiNet.Builder());
//        ApiNet.get(new KVParam()
//                .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do?data=ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620")
//                .setParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620"));
//        ApiNet.post(new KVParam()
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

        ApiNet.get().setCls(UpdateResult.class).setApiResultListener(null).setApiResultListener(new ApiResultListener<UpdateResult>(){

            @Override
            public void onRequestResultSucc(UpdateResult result) {

            }

            @Override
            public void onRequestResultFailed(Throwable errMsg) {

            }
        });

        ApiNet
                .post()
                .registerRecycle(compositeRecycle)
                .setParam(new KVParam()
                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do")
                        .addParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620"))
                .setCls(UpdateResult.class)
                .setApiResultListener(new ApiResultListener<UpdateResult>() {
                    @Override
                    public void onRequestResultSucc(UpdateResult result) {
                        Log.e("onRequestResultSucc", result.updateUrl);
                        ((TextView)findViewById(R.id.text)).setText(result.updateUrl);
                    }

                    @Override
                    public void onRequestResultFailed(Throwable errMsg) {
                    }
                })
                .http();
        ApiNet.upload().http();
    }

    @Override
    protected void onDestroy() {
        if (compositeRecycle!=null){
            compositeRecycle.recycle();
        }
        super.onDestroy();
    }
}
