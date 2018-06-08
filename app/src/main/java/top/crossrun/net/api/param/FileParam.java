package top.crossrun.net.api.param;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import okhttp3.RequestBody;

public abstract class FileParam extends BaseParam {

    Map<String, File> files;
    Map<String, String> filekeys;

    public FileParam() {
        files = new HashMap<>();
        filekeys = new HashMap<>();
    }

    public FileParam addFile(String fileName,String filekey, File file) {
        files.put(fileName, file);
        filekeys.put(fileName, filekey);
        return this;
    }

    @Override
    public void recycle() {
        super.recycle();
        files.clear();
        files = null;
        filekeys.clear();
        filekeys=null;
    }
}
