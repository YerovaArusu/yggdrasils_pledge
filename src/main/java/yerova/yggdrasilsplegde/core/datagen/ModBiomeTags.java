package yerova.yggdrasilsplegde.core.datagen;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.TagManager;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.worldgen.structures.StructureInit;

public class ModBiomeTags extends TagsProvider<Biome> {

    public ModBiomeTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, YggdrasilsPlegde.MOD_ID, helper);
    }

    @Override
    protected void addTags() {
        ForgeRegistries.BIOMES.getValues().forEach(biome -> {
            tag(StructureInit.HAS_YGGDRASIL_TREE).add(biome);
        });
    }

    @Override
    public String getName() {
        return "Yggdrasils Pledge Mod Tags";
    }
}
