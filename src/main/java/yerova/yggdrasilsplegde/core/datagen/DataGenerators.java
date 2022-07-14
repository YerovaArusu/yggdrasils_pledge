package yerova.yggdrasilsplegde.core.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;

import java.io.IOException;

@Mod.EventBusSubscriber(modid = YggdrasilsPlegde.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(final GatherDataEvent event) throws IOException {
        YggdrasilsPlegde.LOGGER.info("Start Gathering Data!");
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        //generator.addProvider(new ModRecipeProvider(generator));
        generator.addProvider(new ModBiomeTags(generator,existingFileHelper));
        generator.addProvider(new ModStructureSetTags(generator, existingFileHelper));

        generator.run();
        YggdrasilsPlegde.LOGGER.info("Done gathering Data!");
    }
}
