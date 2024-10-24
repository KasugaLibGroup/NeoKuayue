package willow.train.kuayue.item.animate_controller;

import com.google.common.collect.Lists;
import com.simibubi.create.content.contraptions.AbstractContraptionEntity;
import com.simibubi.create.content.trains.entity.CarriageContraption;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import kasuga.lib.KasugaLib;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import willow.train.kuayue.utils.client.ComponentTranslationTool;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.UUID;

public class AutomaticControlCouplingItem extends Item implements MenuProvider {


    public AutomaticControlCouplingItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public ItemStack getDefaultInstance() {
        ItemStack stack = super.getDefaultInstance();
        stack.getOrCreateTagElement("contents");
        return stack;
    }

    @Override
    public void onCraftedBy(ItemStack stack, Level pLevel, Player pPlayer) {
        stack.getOrCreateTagElement("contents");
        super.onCraftedBy(stack, pLevel, pPlayer);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        CompoundTag nbt = pStack.getOrCreateTagElement("contents");
        if(Screen.hasShiftDown()) {
            tooltip.add(Component.translatable("tooltip.kuayue.acc.explain.text"));
            tooltip.add(Component.translatable("tooltip.kuayue.acc.explain.text_2"));
            tooltip.add(Component.translatable("tooltip.kuayue.acc.explain.text_3"));
            tooltip.add(Component.translatable("tooltip.kuayue.acc.explain.text_4"));
            tooltip.add(Component.translatable("tooltip.kuayue.acc.explain.cautions").withStyle(ChatFormatting.RED));
        } else {
            tooltip.add(ComponentTranslationTool.HELP_WITH_LSHIFT_COMPONENT);
        }
        if(nbt.size() > 1) {
            tooltip.add(Component.translatable("item.kuayue.acc.tooltip_1")
                    .append(Component.literal(String.valueOf(nbt.size() - 1)).withStyle(ChatFormatting.GOLD))
                    .append(Component.translatable("item.kuayue.acc.tooltip_2")));
        }
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level pLevel, Player pPlayer, @NotNull InteractionHand pUsedHand) {
        if(pPlayer.isShiftKeyDown() && !pLevel.isClientSide()) {
            NetworkHooks.openScreen((ServerPlayer) pPlayer, this, consumer -> {consumer.writeItemStack(pPlayer.getItemInHand(pUsedHand), false);});
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        // if(!stack.is(ResourceItemInit.AutomaticControlCoupling.get())) return false;
        return contentCount(stack) >= 1;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        // if(!stack.is(ResourceItemInit.AutomaticControlCoupling.get())) return 0;
        return Math.round(((float) contentCount(stack))/32 * 13f);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        float percentage = ((float) contentCount(stack))/32;
        float f = Math.max(0.0F, 1 - percentage);
        return Mth.hsvToRgb(f / 3.0F, 1.0F, 1.0F);
    }

    public static boolean addBlock(ItemStack stack, AbstractContraptionEntity entity, BlockPos localPos) {
        if(!(stack.getItem() instanceof AutomaticControlCouplingItem)) return false;
        boolean isTrain = entity.getContraption() instanceof CarriageContraption;
        CompoundTag nbt = stack.getOrCreateTagElement("contents");
        if(!hasBasics(stack)){
            if(setBasics(nbt, isTrain, isTrain ? ((CarriageContraptionEntity) entity).trainId : null, entity.getUUID())){
                return addContents(nbt, localPos, entity.getUUID());
            }
        } else {
            if(containsBlock(stack, localPos, entity.getUUID()))
                return false;
            if(isOnTrain(nbt) && isTrain && ((CarriageContraptionEntity) entity).trainId.equals(getTrainId(nbt))) {
                return addContents(nbt, localPos, entity.getUUID());
            } else if (!isOnTrain(nbt) && !isTrain && (entity.getUUID()).equals(getEntityId(nbt))) {
                return addContents(nbt, localPos, entity.getUUID());
            }
        }
        return false;
    }

    public static boolean addBlock(ItemStack stack, CouplingDisplayData data) {
        return addContents(stack.getOrCreateTagElement("contents"), data.localPos, data.name, data.entityId);
    }

    public static boolean addBlocks(ItemStack stack, CouplingDisplayData... datas) {
        boolean result = true;
        for(CouplingDisplayData data : datas) {
            result = result && addContents(stack.getOrCreateTagElement("contents"), data.localPos, data.name, data.entityId);
        }
        return result;
    }

    public static boolean removeBlock(ItemStack stack, BlockPos localPos) {
        if(!(stack.getItem() instanceof AutomaticControlCouplingItem)) return false;
        CompoundTag nbt = stack.getOrCreateTagElement("contents");
        String wouldRemove = "";
        for(String name : nbt.getAllKeys()) {
            if(name.equals("basic")) continue;
            CompoundTag tag = nbt.getCompound(name);
            if(tag.getInt("px") == localPos.getX() && tag.getInt("py") == localPos.getY() && tag.getInt("pz") == localPos.getZ()) {
                wouldRemove = name;
                break;
            }
        }
        if(wouldRemove.equals(""))
            return false;
        nbt.remove(wouldRemove);
        if(nbt.size() == 1) {
            nbt.remove("basic");
        }
        return true;
    }

    public static void removeBasic(ItemStack stack) {
        if(!(stack.getItem() instanceof AutomaticControlCouplingItem)) return;
        stack.getOrCreateTagElement("contents").remove("basic");
    }

    public static boolean removeAllBlock(ItemStack stack) {
        if(!(stack.getItem() instanceof AutomaticControlCouplingItem)) return false;
        CompoundTag nbt = stack.getOrCreateTagElement("contents");
        if(nbt.isEmpty()) return false;
        CompoundTag basic = nbt.getCompound("basic");
        stack.removeTagKey("contents");
        nbt = stack.getOrCreateTagElement("contents");
        nbt.put("basic", basic);
        return true;
    }

    public static boolean containsBlock(ItemStack stack, BlockPos localPos, UUID entityId) {
        if(!(stack.getItem() instanceof AutomaticControlCouplingItem)) return false;
        CompoundTag nbt = stack.getOrCreateTagElement("contents");
        for(String name : nbt.getAllKeys()) {
            if(name.equals("basic")) continue;
            CompoundTag tag = nbt.getCompound(name);
            if(tag.getInt("px") == localPos.getX() && tag.getInt("py") == localPos.getY() && tag.getInt("pz") == localPos.getZ() && tag.getUUID("entity").equals(entityId))
                return true;
        }
        return false;
    }

    private static UUID getTrainId(CompoundTag tag) {
        if(!tag.contains("basic")) return null;
        return tag.getCompound("basic").getUUID("uuid");
    }

    private static UUID getEntityId(CompoundTag tag) {
        if(!tag.contains("basic")) return null;
        CompoundTag basic = tag.getCompound("basic");
        if(!basic.contains("id")) return null;
        return basic.getUUID("id");
    }

    private static boolean isOnTrain(CompoundTag tag) {
        if(!tag.contains("basic")) return false;
        return ((CompoundTag) tag.get("basic")).getBoolean("train");
    }

    public static boolean setBasics(CompoundTag nbt, boolean isOnTrain, @Nullable UUID trainId, UUID entityId){
        if(isOnTrain && trainId != null) {
            CompoundTag basicTag = new CompoundTag();
            basicTag.putBoolean("train", true);
            basicTag.putUUID("uuid", trainId);
            basicTag.putUUID("id", entityId);
            nbt.put("basic", basicTag);
            return true;
        } else if (!isOnTrain) {
            CompoundTag basicTag = new CompoundTag();
            basicTag.putBoolean("train", false);
            basicTag.putUUID("id", entityId);
            return true;
        } else
            return false;
    }

    public static boolean addContents(CompoundTag nbt, BlockPos localPos, UUID entityId) {
        CompoundTag contentTag = new CompoundTag();
        if(!nbt.contains("basic")) return false;
        contentTag.putUUID("entity", entityId);
        contentTag.putInt("px", localPos.getX());
        contentTag.putInt("py", localPos.getY());
        contentTag.putInt("pz", localPos.getZ());
        contentTag.putString("name", "controlled_block_" + (String.valueOf(Math.abs(KasugaLib.STACKS.random().nextInt()))).substring(0, 4));
        nbt.put("elements" + contentCount(nbt), contentTag);
        return true;
    }

    public static boolean addContents(CompoundTag nbt, BlockPos localPos, String name, UUID entityId) {
        CompoundTag contentTag = new CompoundTag();
        if(!nbt.contains("basic")) return false;
        contentTag.putUUID("entity", entityId);
        contentTag.putInt("px", localPos.getX());
        contentTag.putInt("py", localPos.getY());
        contentTag.putInt("pz", localPos.getZ());
        contentTag.putString("name", name);
        nbt.put("elements" + contentCount(nbt), contentTag);
        return true;
    }

    public static List<CouplingDisplayData> getDisplayDatas(ItemStack stack) {
        if(!(stack.getItem() instanceof AutomaticControlCouplingItem)) return List.of();
        List<CouplingDisplayData> result = Lists.newArrayList();
        CompoundTag nbt = stack.getOrCreateTagElement("contents");
        for(String name : nbt.getAllKeys()) {
            if(name.equals("basic")) continue;
            result.add(new CouplingDisplayData(nbt.getCompound(name)));
        }
        return result;
    }

    public static CompoundTag getBasics(ItemStack stack) {
        CompoundTag nbt = stack.getOrCreateTagElement("contents");
        if(hasBasics(stack))
            return nbt.getCompound("basic");
        return new CompoundTag();
    }

    public static boolean hasBasics(ItemStack stack) {
        return stack.getOrCreateTagElement("contents").contains("basic");
    }

    public static int contentCount(ItemStack stack) {
        return stack.getItem() instanceof AutomaticControlCouplingItem ? contentCount(stack.getOrCreateTagElement("contents")) : 0;
    }

    private static int contentCount(CompoundTag tag) {
        return (int) tag.getAllKeys().stream().filter(s -> s.contains("element")).count();
    }

    @Override
    public Component getDisplayName() {
        return Component.empty();
    }

    @Override
    public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> stacks) {
        if (this.allowedIn(group)) {
            stacks.add(getDefaultInstance());
        }
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pPlayerInventory, Player pPlayer) {
        // return new AutomaticControlCouplingMenu(pContainerId, pPlayerInventory);
        return null;
    }

    public static class CouplingDisplayData {
        @Nonnull public final BlockPos localPos;

        public String name = "";
        @Nullable public UUID trainId;
        public final UUID entityId;

        public CouplingDisplayData(CompoundTag tag) {
            localPos = new BlockPos(tag.getInt("px"), tag.getInt("py"), tag.getInt("pz"));
            this.entityId = tag.getUUID("entity");
            name = tag.getString("name");
        }

        public CouplingDisplayData(BlockPos localPos, UUID entityId, String name, @Nullable UUID trainId) {
            this.localPos = localPos;
            this.entityId = entityId;
            this.trainId = trainId;
            this.name = name;
        }

        public CompoundTag getCompound() {
            CompoundTag tag = new CompoundTag();
            tag.putInt("px", localPos.getX());
            tag.putInt("py", localPos.getY());
            tag.putInt("pz", localPos.getZ());
            tag.putUUID("id", entityId);
            tag.putString("name", name);
            if(trainId != null)
                tag.putUUID("uuid", trainId);
            return tag;
        }

        public boolean hasTrainId() {
            return trainId != null;
        }
    }
}
