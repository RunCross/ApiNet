package top.crossrun.net.api.param;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import okhttp3.RequestBody;

public abstract class FileParam extends BaseParam {

    Map<String, File> files;

    public FileParam() {
        files = new HashMap<>();
    }

    public FileParam addFile(String fileName, File file) {
        files.put(fileName, file);
        return this;
    }

    public abstract RequestBody getValues();

    @Override
    public void recycle() {
        super.recycle();
        files.clear();
        files = null;
    }
}
