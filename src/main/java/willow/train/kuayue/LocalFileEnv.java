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
            createDir();
        } catch (IOException e) {
            Minecraft.crash(new CrashReport("Failed to gen kuayue dir.", e));
        }
    }

    private void createDir() throws IOException {
        File file = new File(dir);
        if (!file.exists() || !file.isDirectory()) file.mkdirs();
    }

    public String getPath(String fileName) {
        return dir + File.separator + fileName;
    }
}
