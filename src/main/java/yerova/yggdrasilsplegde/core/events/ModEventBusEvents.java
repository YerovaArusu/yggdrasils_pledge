package yerova.yggdrasilsplegde.core.events;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.entities.EntityInit;
import yerova.yggdrasilsplegde.core.entities.MariaArusuEntity;

@Mod.EventBusSubscriber(modid = YggdrasilsPlegde.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(EntityInit.MARIA_ARUSU.get(), MariaArusuEntity.setAttributes());
    }
}
