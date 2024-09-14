package willow.train.kuayue.systems.editable_panel;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.ColorTemplateC2SPacket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AllColorTemplates {
    public final ArrayList<ColorTemplate> templates;
    private final String path;
    public static final ColorTemplate testTemp = new ColorTemplate("test", 0x114514, "kasuga");

    static {
        testTemp.setDocument("This is a test template");
    }
    public AllColorTemplates(String path) {
        templates = new ArrayList<>();
        this.path = path;
        templates.add(testTemp);
    }

    public void readFromFile() {
        try {
            File file = new File(path);
            if (!file.exists() || !file.isFile()) return;
            CompoundTag nbt = NbtIo.read(file);
            templates.clear();
            int count = nbt.getInt("count");
            for (int i = 0; i < count; i++) {
                String name = "temp" + i;
                CompoundTag tempTag = nbt.getCompound(name);
                templates.add(new ColorTemplate(tempTag));
            }
            templates.add(testTemp);
        } catch (IOException e) {
            Kuayue.LOGGER.error("Fail to read color templates from disk.", e);
        }
    }

    public void writeToFile() {
        try {
            File file = new File(path);
            if (!file.exists() && !file.createNewFile()) return;
            CompoundTag nbt = new CompoundTag();
            int count = 0;
            for (ColorTemplate template : templates) {
                if (template.isTemporary()) continue;
                CompoundTag tag = new CompoundTag();
                template.write(tag);
                nbt.put("temp" + count, tag);
                count++;
            }
            nbt.putInt("count", count);
            NbtIo.write(nbt, file);
        } catch (IOException e) {
            Kuayue.LOGGER.error("Fail to write color templates to disk.", e);
        }
    }

    public void clear() {
        this.templates.clear();
    }

    public ColorTemplate addTemplate(ColorTemplate template) {
        this.templates.add(template);
        return template;
    }

    public ArrayList<ColorTemplate> getTemplates() {
        return templates;
    }

    public String getPath() {
        return path;
    }

    public ColorTemplate getTemplate(String name) {
        for (ColorTemplate template : templates) {
            if (template.getName().equals(name)) return template;
        }
        return null;
    }

    public Set<ColorTemplate> getTemplate(UUID playerId) {
        HashSet<ColorTemplate> result = new HashSet<>();
        for (ColorTemplate template : templates) {
            if (!template.hasOwner()) continue;
            if (template.getOwner().equals(playerId)) result.add(template);
        }
        return result;
    }

    public Set<ColorTemplate> getTemplate(Player player) {
        return getTemplate(player.getUUID());
    }

    public void shareTemplate(ColorTemplate template) {
        AllPackets.TEMPLATE.sendToServer(new ColorTemplateC2SPacket(template));
    }
}
