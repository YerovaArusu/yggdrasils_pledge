package yerova.yggdrasilsplegde.core.datagen;

import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.data.ExistingFileHelper;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;

public class ModStructureSetTags extends TagsProvider<StructureSet> {
    public ModStructureSetTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.STRUCTURE_SETS, YggdrasilsPlegde.MOD_ID, helper);
    }

    @Override
    protected void addTags() {


    }

    @Override
    public String getName() {
        return "Yggdrasils Pledge Mod Tags";
    }
}
