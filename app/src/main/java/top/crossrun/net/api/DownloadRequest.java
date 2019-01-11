package top.crossrun.net.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.crossrun.net.listener.ApiResultListener;

public class DownloadRequest extends Request<String> {
    String path;
    boolean deleteOnExist;

    public DownloadRequest(String path, boolean deleteOnExist) {
        super(String.class);
        this.path = path;
        this.deleteOnExist = deleteOnExist;
    }

    @Override
    public Observable<String> getRequestObservable() {
        return null;
    }

    @Override
    public void http() {
        File file = new File(path);
        if (file.exists() && !deleteOnExist) {
            if (listener != null) {
                listener.onRequestResultSucc(path);
            }
        } else if (file.exists() && deleteOnExist) {
            file.delete();
        }
        okhttp3.Request request = getRequestBuilder().url(param.getUrl())//地址
                .get()//添加请求体
                .build();
        createCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // 下载失败
                if (listener != null) {
                    listener.onRequestResultFailed(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                byte[] buf = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                File file = new File(path);
                isExistDir(file.getAbsolutePath());
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    OnDownloadProcessListener processListener = null;
                    if (listener != null && listener instanceof OnDownloadProcessListener) {
                        processListener = (OnDownloadProcessListener) listener;
                    }
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        if (processListener != null) {
                            processListener.onRequestProcess(progress);
                        }
                    }
                    fos.flush();
                    // 下载完成
                    if (listener != null) {
                        listener.onRequestResultSucc(path);
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onRequestResultFailed(e);
                    }
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                    }
                }
            }
        });
    }

    /**
     * 判断下载目录是否存在
     *
     * @param dir
     */
    private void isExistDir(String dir) {
        // 下载位置
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
    }


    public abstract static class OnDownloadProcessListener extends ApiResultListener<String> {
        /**
         * @param progress 下载进度
         */
        public abstract void onRequestProcess(int progress);

    }
}
