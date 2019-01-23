package top.crossrun.net.listener;

public interface OnDownloadProcessListener extends ApiResultListener<String> {
    /**
     * @param progress 下载进度
     */
    void onRequestProcess(int progress);

}