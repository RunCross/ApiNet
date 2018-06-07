package top.crossrun.net.test;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import top.crossrun.net.api.ApiNet;

public class Test {
    public void test(){
        ApiNet.init(new ApiNet.Builder());
//        ApiNet.post(new KVParam());
//        text/plain
//        http://igotone.zj.chinamobile.com:88/ZJMOAPortalNew/portal/index.do
//        data=ECDB7D5A6EFBFC7A7804D8A30BE803062409D17B746798EAB848A9A482DFD620
        RequestBody.create(MediaType.parse("text/plain"),"s");
    }
}
