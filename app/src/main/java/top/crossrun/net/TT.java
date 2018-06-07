package top.crossrun.net;

import top.crossrun.net.api.param.BaseParam;
import top.crossrun.net.listener.ApiResultListener;

public class TT {

    public void test(){
        new ITest<BaseParam>().setS(BaseParam.class).setListener(new ApiResultListener<BaseParam>() {
            @Override
            public void onRequestResultSucc(BaseParam result) {

            }

            @Override
            public void onRequestResultFailed(Throwable errMsg) {

            }
        });
    }
}
