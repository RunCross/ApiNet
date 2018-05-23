package top.crossrun.nettest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.android.schedulers.AndroidSchedulers;
import top.crossrun.net.api.ApiNet;
import top.crossrun.net.api.KVParam;
import top.crossrun.net.listener.ApiResultListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        ApiNet
                .post()
                .setParam(new KVParam()
                        .setUrl("http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do")
                        .setParam("data", "ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620"))
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
    }
}
