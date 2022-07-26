package yerova.yggdrasilsplegde.core.worldgen.dimension;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.DimensionType;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;


public class DimensionInit {
    public static final ResourceKey<Level> YGGDRASILS_INNER = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            new ResourceLocation(YggdrasilsPlegde.MOD_ID, "yggdrasils_inner"));

    public static final ResourceLocation YGGDRASILS_INNER_DIMENSION_SET = new ResourceLocation(YggdrasilsPlegde.MOD_ID, "yggdrasils_inner_structure_set");

    public static void register() {
        Registry.register(Registry.CHUNK_GENERATOR, new ResourceLocation(YggdrasilsPlegde.MOD_ID, "yggdrasils_inner_chunkgen"),
                YggdrasilsInnerChunkGenerator.CODEC);
        Registry.register(Registry.BIOME_SOURCE, new ResourceLocation(YggdrasilsPlegde.MOD_ID, "biomes"),
                YggrdasilsInnerBiomeProvider.CODEC);
    }
}
