# ApiNet
试图封装okhttp，弄一个简化版的Http请求

## import
[![](https://jitpack.io/v/RunCross/ApiNet.svg)](https://jitpack.io/#RunCross/ApiNet)https://jitpack.io/#RunCross/ApiNet

## get
```
ApiNet.get(String.class)
                .setParam(new KVUrlParam()
                        .setUrl("http://xxx.xxx")
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
