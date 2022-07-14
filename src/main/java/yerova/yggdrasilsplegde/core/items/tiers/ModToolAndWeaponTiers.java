package yerova.yggdrasilsplegde.core.items.tiers;

import net.minecraft.client.Minecraft;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

import java.util.function.Supplier;

public class ModToolAndWeaponTiers {

    public static final ForgeTier YGGDRAFOLIUM = new ForgeTier(6, 0, 4, 16, 40,
            BlockTags.NEEDS_DIAMOND_TOOL,  () -> Ingredient.of(Items.NETHER_STAR));


}
