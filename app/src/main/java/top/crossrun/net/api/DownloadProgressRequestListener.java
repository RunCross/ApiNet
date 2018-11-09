package top.crossrun.net.api;

public interface DownloadProgressRequestListener {
    /**
     * 下载进度
     *
     * @param total    总数
     * @param progress 已经下载的
     */
    void onDownloadProgress(long total, long progress);
}
