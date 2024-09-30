package willow.train.kuayue.systems.editable_panel;

import kasuga.lib.core.base.NbtSerializable;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;

import java.util.UUID;

public class ColorTemplate implements NbtSerializable {
    private Component name, document, owner;
    private int color;
    private boolean temporary, deleteAble, editable;

    public ColorTemplate(Component name, int color, Component owner, boolean temporary, boolean deleteAble, boolean editable) {
        this(name, color, owner);
        this.deleteAble = deleteAble;
        this.temporary = temporary;
        this.editable = editable;
    }

    public ColorTemplate(CompoundTag nbt) {
        read(nbt);
        temporary = false;
        deleteAble = true;
    }

    public ColorTemplate(String name, int color, String owner) {
        this.name = Component.literal(name);
        this.color = color;
        this.document = Component.empty();
        this.owner = Component.literal(owner);
        temporary = false;
    }

    public ColorTemplate(Component name, int color, Component owner) {
        this.name = name;
        this.color = color;
        this.document = Component.empty();
        this.owner = owner;
        temporary = false;
    }
    @Override
    public void write(CompoundTag compoundTag) {
        compoundTag.putString("name", this.name.getString());
        compoundTag.putString("document", this.document.getString());
        compoundTag.putInt("color", this.color);
        if (owner != null)
            compoundTag.putString("owner", owner.getString());
    }

    @Override
    public void read(CompoundTag compoundTag) {
        this.name = Component.literal(compoundTag.getString("name"));
        this.color = compoundTag.getInt("color");
        this.document = Component.literal(compoundTag.getString("document"));
        if (compoundTag.contains("owner"))
            this.owner = Component.literal(compoundTag.getString("owner"));
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getName() {
        return name.getString();
    }

    public Component getNameComponent() {
        return name;
    }

    public String getDocument() {
        return document.getString();
    }

    public Component getDocumentComponent() {
        return document;
    }

    public String getOwner() {
        return owner.getString();
    }

    public Component getOwnerComponent() {
        return owner;
    }

    public boolean hasOwner() {
        return this.owner != null;
    }

    public void setName(String name) {
        this.name = Component.literal(name);
    }
    public void setName(Component name) {
        this.name = name;
    }

    public void setDocument(String document) {
        this.document = Component.literal(document);
    }
    public void setDocument(Component document) {
        this.document = document;
    }

    public void setOwner(String owner) {
        this.owner = Component.literal(owner);
    }
    public void setOwner(Component owner) {
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
    public boolean isDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(boolean deleteAble) {
        this.deleteAble = deleteAble;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }
}
