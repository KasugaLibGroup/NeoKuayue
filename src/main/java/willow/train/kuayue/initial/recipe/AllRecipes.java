package willow.train.kuayue.initial.recipe;

import kasuga.lib.registrations.common.RecipeReg;
import willow.train.kuayue.initial.AllElements;
import willow.train.kuayue.recipe.BlueprintTableRecipe;

public class AllRecipes {

    public static final RecipeReg<BlueprintTableRecipe, BlueprintTableRecipe.BlueprintRecipeSerializer> BLUEPRINT =
            new RecipeReg<BlueprintTableRecipe, BlueprintTableRecipe.BlueprintRecipeSerializer>("blueprint_table")
                    .withSerializer(new BlueprintTableRecipe.BlueprintRecipeSerializer())
                    .submit(AllElements.testRegistry);

    public static void invoke(){}
}
