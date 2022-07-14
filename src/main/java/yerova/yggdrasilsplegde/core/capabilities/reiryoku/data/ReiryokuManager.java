package yerova.yggdrasilsplegde.core.capabilities.reiryoku.data;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import yerova.yggdrasilsplegde.core.ClientServerCommunication;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.network.ReiryokuManagerToClient;

import javax.annotation.Nonnull;

public class ReiryokuManager extends SavedData{

    int counter= 0;
    public ReiryokuManager() {

    }
    public ReiryokuManager(CompoundTag tag) {

    }
    @Nonnull
    public static ReiryokuManager get(Level level) {
        if (level.isClientSide) {
            throw new RuntimeException("Don't access this client-side!");
        }
        DimensionDataStorage storage = ((ServerLevel)level).getDataStorage();

        return storage.computeIfAbsent(ReiryokuManager::new, ReiryokuManager::new, "reiryokumanager");
    }
    @Override
    public CompoundTag save(CompoundTag compoundTag) {
        return compoundTag;
    }

    public void tick(Level level) {
        counter--;
        if (counter <= 0) {
            counter = 10;
            level.players().forEach(player -> {
                if (player instanceof ServerPlayer serverPlayer) {

                    //TODO: change to reiryoku
                    int reiryoku = serverPlayer.getCapability(ReiryokuProvider.REIRYOKU)
                            .map(Reiryoku::getReiryoku)
                            .orElse(-1);

                    int maxReiryoku = serverPlayer.getCapability(ReiryokuProvider.REIRYOKU)
                            .map(Reiryoku::getMaxReiryoku)
                            .orElse(-1);
                    ClientServerCommunication.sendToPlayer(new ReiryokuManagerToClient(reiryoku, maxReiryoku), serverPlayer);
                }
            });
        }

    }
}