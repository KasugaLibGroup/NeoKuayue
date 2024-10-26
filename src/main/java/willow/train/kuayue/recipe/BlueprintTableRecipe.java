package willow.train.kuayue.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import willow.train.kuayue.Kuayue;
import willow.train.kuayue.initial.recipe.AllRecipes;

public class BlueprintTableRecipe extends SingleItemRecipe {

    public final ResourceLocation img_location;
    public final int level_require, exp_consume;

    public BlueprintTableRecipe(ResourceLocation pId, ResourceLocation img_location,
                                String pGroup, int level_require, int exp_consume,
                                Ingredient pIngredient, ItemStack pResult) {
        super(
                AllRecipes.BLUEPRINT.getRecipeType(), AllRecipes.BLUEPRINT.getSerializer(),
                pId, pGroup, pIngredient, pResult
        );
        this.img_location = img_location;
        this.level_require = level_require;
        this.exp_consume = exp_consume;
    }

    @Override
    public boolean matches(Container pContainer, Level pLevel) {
        return false;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(Container pContainer) {
        return super.getRemainingItems(pContainer);
    }

    public static class BlueprintRecipeSerializer implements RecipeSerializer<BlueprintTableRecipe> {

        public BlueprintRecipeSerializer() {}


        public BlueprintTableRecipe fromJson(ResourceLocation pRecipeId, JsonObject pJson) {
            String s = GsonHelper.getAsString(pJson, "group", "");
            int level_require = GsonHelper.getAsInt(pJson, "level_require", 0);
            int exp_consume = GsonHelper.getAsInt(pJson, "exp_consume", 0);
            String img = GsonHelper.getAsString(pJson, "img_location", "kuayue:blank.png");

            ResourceLocation img_location;
            if(!img.equals("kuayue:blank.png"))
                img_location = new ResourceLocation(pRecipeId.getNamespace(), "textures/" + img);
            else
                img_location = new ResourceLocation(Kuayue.MODID, "textures/recipes/blank.png");

            Ingredient ingredient;
            if (GsonHelper.isArrayNode(pJson, "ingredients")) {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(pJson, "ingredients"));
            } else {
                ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(pJson, "ingredients"));
            }

            String s1 = GsonHelper.getAsString(pJson, "result");
            int i = GsonHelper.getAsInt(pJson, "count", 1);
            Item itemLike = ForgeRegistries.ITEMS.getValue(new ResourceLocation(s1));
            if(itemLike != null) {
                ItemStack itemStack = new ItemStack(itemLike, i);
                return new BlueprintTableRecipe(pRecipeId, img_location, s, level_require, exp_consume, ingredient, itemStack);
            }
            return null;
        }

        public BlueprintTableRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            String s = pBuffer.readUtf();
            ResourceLocation img_location = pBuffer.readResourceLocation();
            int level_require = pBuffer.readInt();
            int exp_consume = pBuffer.readInt();
            Ingredient ingredient = Ingredient.fromNetwork(pBuffer);
            ItemStack itemstack = pBuffer.readItem();
            return new BlueprintTableRecipe(pRecipeId, img_location, s, level_require, exp_consume, ingredient, itemstack);
        }

        public void toNetwork(FriendlyByteBuf pBuffer, BlueprintTableRecipe pRecipe) {
            pBuffer.writeUtf(pRecipe.group);
            pBuffer.writeResourceLocation(pRecipe.img_location);
            pBuffer.writeInt(pRecipe.level_require);
            pBuffer.writeInt(pRecipe.exp_consume);
            pRecipe.ingredient.toNetwork(pBuffer);
            pBuffer.writeItem(pRecipe.result);
        }

        interface SingleItemMaker<T extends SingleItemRecipe> {
            T create(ResourceLocation pId, ResourceLocation pImgLocation,
                     String pGroup, int level_require, int exp_consume,
                     Ingredient pIngredient, ItemStack pResult);
        }
    }
}
