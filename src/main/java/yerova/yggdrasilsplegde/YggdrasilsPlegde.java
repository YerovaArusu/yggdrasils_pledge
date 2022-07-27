package yerova.yggdrasilsplegde;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.client.gui.OverlayRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import yerova.yggdrasilsplegde.core.ClientServerCommunication;
import yerova.yggdrasilsplegde.core.abilities.AbilityInit;
import yerova.yggdrasilsplegde.core.blocks.BlockInit;
import yerova.yggdrasilsplegde.core.datagen.DataGenerators;
import yerova.yggdrasilsplegde.core.entities.EntityInit;
import yerova.yggdrasilsplegde.core.entities.client.MariaArusuRenderer;
import yerova.yggdrasilsplegde.core.events.ReiryokuEvents;
import yerova.yggdrasilsplegde.core.gui.ReiryokuOverlay;
import yerova.yggdrasilsplegde.core.items.ItemInit;
import yerova.yggdrasilsplegde.core.keybindings.KeyBindsInit;
import yerova.yggdrasilsplegde.core.worldgen.dimension.DimensionInit;
import yerova.yggdrasilsplegde.core.worldgen.structures.StructureInit;

import static net.minecraftforge.client.gui.ForgeIngameGui.HOTBAR_ELEMENT;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(YggdrasilsPlegde.MOD_ID)
public class YggdrasilsPlegde {

    public static final Logger LOGGER = LogUtils.getLogger();
    public static final String MOD_ID = "yggdrasils_pledge";

    public YggdrasilsPlegde() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();


        ItemInit.ITEMS.register(bus);
        EntityInit.ENTITY.register(bus);
        BlockInit.BLOCKS.register(bus);
        StructureInit.STRUCTURES.register(bus);




        bus.addListener(this::ClientSetup);
        bus.addListener(this::CommonSetup);

        IEventBus eventBus = MinecraftForge.EVENT_BUS;

        eventBus.addGenericListener(Entity.class, ReiryokuEvents::onAttachCapabilitiesPlayer);
        eventBus.addListener(ReiryokuEvents::onPlayerCloned);
        eventBus.addListener(ReiryokuEvents::onRegisterCapabilities);
        eventBus.addListener(ReiryokuEvents::onWorldTick);
        eventBus.addListener(ReiryokuEvents::checkYggdrasilItems);





        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(DataGenerators.class);


        GeckoLib.initialize();
    }

    @SubscribeEvent
    public void ClientSetup(FMLClientSetupEvent event) {
        KeyBindsInit.register(event);
        OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ReiryokuOverlay.HUD_REIRYOKU);
        EntityRenderers.register(EntityInit.MARIA_ARUSU.get(), MariaArusuRenderer::new);
    }

    @SubscribeEvent
    public void CommonSetup(FMLCommonSetupEvent event) {
        DimensionInit.register();
        ClientServerCommunication.register();
        AbilityInit.register();
    }
}
