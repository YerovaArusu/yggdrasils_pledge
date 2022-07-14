package yerova.yggdrasilsplegde.core.worldgen.structures;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.StructureFeature;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;

public class StructureInit {
    public static final DeferredRegister<StructureFeature<?>> STRUCTURES = DeferredRegister.create(ForgeRegistries.STRUCTURE_FEATURES, YggdrasilsPlegde.MOD_ID);


    public static final RegistryObject<StructureFeature> YGGDRASIL_TREE = STRUCTURES.
            register("yggdrasil_tree", YggdrasilTree::new);

    public static final TagKey<Biome> HAS_YGGDRASIL_TREE = TagKey
            .create(Registry.BIOME_REGISTRY, new ResourceLocation(YggdrasilsPlegde.MOD_ID, "has_structure/yggdrasil_tree"));
}
