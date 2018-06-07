# ApiNet
试图封装retrofit2，弄一个简化版的Http请求

## get
```
ApiNet.get(String.class)
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
```

## post
```
ApiNet.post(String.class)
                .setParam(new KVStringParam()
                        .setUrl("http://xx.xxx")
                        .addParam("key", "value")
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
```
