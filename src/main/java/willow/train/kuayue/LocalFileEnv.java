package willow.train.kuayue;

import net.minecraft.CrashReport;
import net.minecraft.client.Minecraft;

import java.io.File;
import java.io.IOException;

public class LocalFileEnv {

    public final String dir;

    public LocalFileEnv(String dir) {
        this.dir = dir;
        try{
            if (!createDir()) throw new RuntimeException("Find an unknown error!");
        } catch (IOException e) {
            Minecraft.crash(new CrashReport("Failed to gen kuayue dir.", e));
        }
    }

    private boolean createDir() throws IOException {
        File file = new File(dir);
        if (file.exists() && file.isDirectory()) return true;
        if (!file.exists() || !file.isDirectory())
            return file.mkdirs();
        return false;
    }

    public String getPath(String fileName) {
        return dir + File.separator + fileName;
    }
}
