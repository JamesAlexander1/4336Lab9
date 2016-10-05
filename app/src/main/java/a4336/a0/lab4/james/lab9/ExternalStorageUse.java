package a4336.a0.lab4.james.lab9;

import android.os.Environment;
import android.os.StatFs;

import java.io.File;

/**
 * Created by james on 5/10/2016.
 */
public class ExternalStorageUse {

    private Environment environment;

    public ExternalStorageUse() {
        Environment environment = new Environment();
    }

    public boolean getExternalDirectoryStatus(){

        String state = Environment.getExternalStorageState();

        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;
        }else {
            return false;
        }
    }
    public long getExternalDirectoryStorageSize(){

        File file = Environment.getExternalStorageDirectory();
        StatFs fs = new StatFs(file.getPath());

        long bytes_available = fs.getBlockSizeLong() * fs.getBlockCountLong();

        long m_bytes = bytes_available / 1048576;

        return m_bytes;
    }
}
