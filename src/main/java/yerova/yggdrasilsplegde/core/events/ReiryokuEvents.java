package yerova.yggdrasilsplegde.core.events;

import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.Reiryoku;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.ReiryokuManager;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.ReiryokuProvider;

public class ReiryokuEvents {

    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> entity){
        if (entity.getObject() instanceof Player) {
            if (!entity.getObject().getCapability(ReiryokuProvider.REIRYOKU).isPresent()) {
                entity.addCapability(new ResourceLocation(YggdrasilsPlegde.MOD_ID, "reiryoku"), new ReiryokuProvider());
            }
        }
    }

    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            event.getOriginal().getCapability(ReiryokuProvider.REIRYOKU).ifPresent(oldStore -> {
                event.getPlayer().getCapability(ReiryokuProvider.REIRYOKU).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }

    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(Reiryoku.class);
    }

    public static void onWorldTick(TickEvent.WorldTickEvent event) {
        // Don't do anything client side
        if (event.world.isClientSide) {
            return;
        }
        if (event.phase == TickEvent.Phase.START) {

            //Passive Regen
            for (Player player: event.world.players()) {

                if(event.world.getGameTime() %20 == 0) {
                    player.getCapability(ReiryokuProvider.REIRYOKU).ifPresent(reiryoku -> {
                        if(reiryoku.getMaxReiryoku() > reiryoku.getReiryoku()) {
                            reiryoku.increaseReiryoku(16);
                        }
                    });
                }


            }

            return;
        }
        ReiryokuManager manager = ReiryokuManager.get(event.world);
        manager.tick(event.world);
    }
}
