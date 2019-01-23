package top.crossrun.net.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import top.crossrun.net.listener.OnDownloadProcessListener;

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
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                HttpURLConnection connection = (HttpURLConnection) new URL(param.getUrl()).openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream is = connection.getInputStream();
                byte[] buf = new byte[2048];
                int len = 0;
                long total = 0, sum = 0;
                FileOutputStream fos = null;
                File file = new File(path);
                isExistDir(file.getParentFile().getAbsolutePath());
                try {

                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                        total = connection.getContentLengthLong();
                    } else {
                        total = connection.getContentLength();
                    }
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
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
                    e.onNext(path);
                } catch (Exception ex1) {
                    throw ex1;
                } finally {
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException ex2) {
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException ex3) {
                    }
                }
            }
        });
    }

    @Override
    public void http() {
        File file = new File(path);
        if (file.exists() && !deleteOnExist) {
            if (listener != null) {
                Observable
                        .create(new ObservableOnSubscribe<String>() {
                            /**
                             * Called for each Observer that subscribes.
                             *
                             * @param e the safe emitter instance, never null
                             * @throws Exception on error
                             */
                            @Override
                            public void subscribe(ObservableEmitter<String> e) throws Exception {
                                e.onNext(path);
                            }
                        })
                        .observeOn(responseScheduler)
                        .subscribeOn(requestScheduler)
                        .subscribe(new Observer<String>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(String value) {
                                if (listener != null) {
                                    listener.onRequestResultSucc(path);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
            return;
        } else if (file.exists() && deleteOnExist) {
            file.delete();
        }
        super.http();
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
}
