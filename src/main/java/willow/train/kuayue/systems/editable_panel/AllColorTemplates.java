package willow.train.kuayue.systems.editable_panel;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtIo;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.AllPackets;
import willow.train.kuayue.network.c2s.ColorTemplateC2SPacket;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AllColorTemplates {
    public final ArrayList<ColorTemplate> templates;
    private final String path;
    private static final Component ownerTag = Component.translatable("tooltip.kuayue.color_template_screen.default_element_25.owner");
    public static final ColorTemplate color25G = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_25g.name"),
            15216648, ownerTag, false, false, false);
    public static final ColorTemplate color25B = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_25b.name")
            , 16776960, ownerTag, false, false, false);
    public static final ColorTemplate color25Z = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_25z.name"),
            0x60A0B0, ownerTag, false, false, false);
    public static final ColorTemplate color25T = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_25t.name")
            , 468326, ownerTag, false, false, false);
    public static final ColorTemplate color25K = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_25k.name")
            , 22220, ownerTag, false, false, false);
    public static final ColorTemplate color25Default = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_25.name")
            , 16776961, ownerTag, false, false, false);
    public static final ColorTemplate colorM1 = new ColorTemplate(
            Component.translatable("tooltip.kuayue.color_template_screen.default_element_m1.name")
            , 0x2B4CA1, ownerTag, false, false, false);

    static {
        color25G.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_25g.doc"));
        color25B.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_25b.doc"));
        color25Z.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_25z.doc"));
        color25T.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_25t.doc"));
        color25K.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_25k.doc"));
        color25Default.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_25.doc"));
        colorM1.setDocument(Component.translatable("tooltip.kuayue.color_template_screen.default_element_m1.doc"));
    }
    public AllColorTemplates(String path) {
        templates = new ArrayList<>();
        this.path = path;
        templates.add(color25G);
        templates.add(color25B);
        templates.add(color25Z);
        templates.add(color25T);
        templates.add(color25K);
        templates.add(color25Default);
        templates.add(colorM1);
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
            templates.add(color25G);
            templates.add(color25B);
            templates.add(color25Z);
            templates.add(color25T);
            templates.add(color25K);
            templates.add(color25Default);
            templates.add(colorM1);
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
        AllPackets.CHANNEL.sendToServer(new ColorTemplateC2SPacket(template));
    }
}
