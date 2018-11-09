package top.crossrun.net.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.Response;

public class DownloadRequest extends Request<String> {
    DownloadProgressRequestListener downloadRequestListener;

    String path;

    boolean exitDelete = true;

    public DownloadRequest() {
        super(String.class);
    }

    public DownloadRequest setDownloadRequestListener(DownloadProgressRequestListener downloadRequestListener) {
        this.downloadRequestListener = downloadRequestListener;
        return this;
    }

    /**
     * 路径包含文件名
     *
     * @param path       路径包含文件名
     * @param exitDelete 文件已经存在的时候是否删除 true = 删除
     * @return
     */
    public DownloadRequest setDownloadPathAndName(String path, boolean exitDelete) {
        this.path = path;
        this.exitDelete = exitDelete;
        return this;
    }

    @Override
    public Observable<String> getRequestObservable() {
        return Observable
                .create(new ObservableOnSubscribe<String>() {

                    /**
                     * Called for each Observer that subscribes.
                     *
                     * @param e the safe emitter instance, never null
                     * @throws Exception on error
                     */
                    @Override
                    public void subscribe(ObservableEmitter<String> e) {
                        okhttp3.Request request = getRequestBuilder()
                                .url(param.getUrl())//地址
                                .get()//添加请求体
                                .build();
                        InputStream is = null;
                        FileOutputStream fos = null;
                        try {
                            Response response = createCall(request).execute();
                            File file = new File(path);
                            File dir = file.getParentFile();
                            if (!dir.exists()) {
                                dir.mkdirs();
                            }
                            if (!dir.isDirectory()) {
                                e.onError(new Throwable(path + "路径错误"));
                                return;
                            }
                            if (exitDelete) {
                                file.delete();
                            }
                            byte[] buf = new byte[2048];
                            int len;
                            is = response.body().byteStream();
                            long total = response.body().contentLength();
                            fos = new FileOutputStream(file);
                            long sum = 0;
                            while ((len = is.read(buf)) != -1) {
                                fos.write(buf, 0, len);
                                sum += len;
                                // 下载中
                                downloadRequestListener.onDownloadProgress(total, sum);
                            }
                            fos.flush();
                            e.onNext("");
                        } catch (Throwable e1) {
                            e1.printStackTrace();
                            e.onError(e1);
                        } finally {
                            if (is != null) {
                                try {
                                    is.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            if (fos != null) {
                                try {
                                    fos.close();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                        e.onComplete();
                    }
                });
    }

    @Override
    public void recycle() {
        super.recycle();
        downloadRequestListener = null;
    }
}
