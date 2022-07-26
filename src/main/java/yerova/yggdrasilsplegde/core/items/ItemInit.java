package yerova.yggdrasilsplegde.core.items;


import net.minecraft.ChatFormatting;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yerova.yggdrasilsplegde.core.blocks.BlockInit;
import yerova.yggdrasilsplegde.core.items.tiers.ModToolAndWeaponTiers;
import yerova.yggdrasilsplegde.core.items.tools.YggdrafoliumPickaxe;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.items.weapons.YggdRamus;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraft.world.level.levelgen.feature.configurations.JigsawConfiguration;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, YggdrasilsPlegde.MOD_ID);

    public static final RegistryObject<Item> YGGDRAFOLIUM_PICKAXE = ITEMS.register("yggdrafolium_pickaxe",
            () -> new YggdrafoliumPickaxe(ModToolAndWeaponTiers.YGGDRAFOLIUM, 8, 1f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> YGGD_RAMUS = ITEMS.register("yggd_ramus", () -> new YggdRamus(
            new Item.Properties().tab(CreativeModeTab.TAB_TOOLS).fireResistant().rarity(Rarity.create("Unique", ChatFormatting.AQUA)).stacksTo(1)));

    public static final RegistryObject<Item> PORTAL_ITEM = fromBlock(BlockInit.PORTAL_BLOCK);




















    public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> block) {
        return ITEMS.register(block.getId().getPath(), () -> new BlockItem(block.get(), ITEM_PROPERTIES));
    }
}
