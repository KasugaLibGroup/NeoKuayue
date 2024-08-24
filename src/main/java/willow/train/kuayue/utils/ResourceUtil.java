package willow.train.kuayue.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import willow.train.kuayue.Kuayue;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ResourceUtil {

    public static Map<String, InputStream> getFileStream(String jarResourcePath, boolean isFile) throws IOException {
        return neoFileStreamGetter(jarResourcePath, isFile);
    }

    public static Map<String, InputStream> neoFileStreamGetter(String path, boolean isFile) throws IOException {
        ResourceManager rm = Minecraft.getInstance().getResourceManager();

        if(isFile) {
            ResourceLocation location = new ResourceLocation(Kuayue.MODID, path);
            return Map.of(path, rm.getResourceOrThrow(location).open());
        }
        Map<ResourceLocation, Resource> resources = rm.listResources(path, location -> location.getNamespace().equals(Kuayue.MODID));
        HashMap<String, InputStream> result = new HashMap<>();
        for(ResourceLocation location : resources.keySet()) {
            result.put(location.getPath().replaceAll(path + "/", ""), resources.get(location).open());
        }
        return result;
    }
}
