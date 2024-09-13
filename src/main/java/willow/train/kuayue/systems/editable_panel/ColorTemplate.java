package willow.train.kuayue.systems.editable_panel;

import kasuga.lib.core.base.NbtSerializable;
import kasuga.lib.core.client.render.SimpleColor;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class ColorTemplate implements NbtSerializable {
    private String name, document;
    private int color;
    private String owner;
    private boolean temporary;

    public ColorTemplate(CompoundTag nbt) {
        read(nbt);
        temporary = false;
    }

    public ColorTemplate(String name, int color, String owner) {
        this.name = name;
        this.color = color;
        this.document = "";
        this.owner = owner;
        temporary = false;
    }
    @Override
    public void write(CompoundTag compoundTag) {
        compoundTag.putString("name", this.name);
        compoundTag.putString("document", this.document);
        compoundTag.putInt("color", this.color);
        if (owner != null)
            compoundTag.putString("owner", owner);
    }

    @Override
    public void read(CompoundTag compoundTag) {
        this.name = compoundTag.getString("name");
        this.color = compoundTag.getInt("color");
        this.document = compoundTag.getString("document");
        if (compoundTag.contains("owner"))
            this.owner = compoundTag.getString("owner");
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getOwner() {
        return owner;
    }

    public boolean hasOwner() {
        return this.owner != null;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public boolean isTemporary() {
        return temporary;
    }

    public void setTemporary(boolean temporary) {
        this.temporary = temporary;
    }
    public int getColor() {
        return color;
    }
}
