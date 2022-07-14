package yerova.yggdrasilsplegde.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import yerova.yggdrasilsplegde.core.items.ItemInit;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {

    public ModRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> finishedRecipeConsumer) {


        ShapedRecipeBuilder.shaped(ItemInit.YGGD_RAMUS.get())
                .define('S', Items.NETHER_STAR)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .save(finishedRecipeConsumer);

    }
}
