package yerova.yggdrasilsplegde.core.items;


import net.minecraft.ChatFormatting;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yerova.yggdrasilsplegde.core.items.tiers.ModToolAndWeaponTiers;
import yerova.yggdrasilsplegde.core.items.tools.YggdrafoliumPickaxe;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.items.weapons.YggdRamus;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YggdrasilsPlegde.MOD_ID);

    public static final RegistryObject<Item> YGGDRAFOLIUM_PICKAXE = ITEMS.register("yggdrafolium_pickaxe",
            () -> new YggdrafoliumPickaxe(ModToolAndWeaponTiers.YGGDRAFOLIUM, 8, 1f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> YGGD_RAMUS = ITEMS.register("yggd_ramus", () -> new YggdRamus(
            new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant().rarity(Rarity.create("Unique", ChatFormatting.AQUA)).stacksTo(1)));




}
