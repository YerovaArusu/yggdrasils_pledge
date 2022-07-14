package yerova.yggdrasilsplegde.core.capabilities.reiryoku.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.data.ReiryokuProvider;

import java.util.function.Supplier;

public class ReiryokuManagerToServer {

    public int operations;
    public int valueChange;
    public ReiryokuManagerToServer(FriendlyByteBuf buffer) {
        this(buffer.readInt(), buffer.readInt());
    }

    public ReiryokuManagerToServer(int operations, int valueChange) {
        this.operations = operations;
        this.valueChange = valueChange;
    }
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(operations);
        buffer.writeInt(valueChange);
    }
    public boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();


            switch (operations) {
                case 0:
                    player.getCapability(ReiryokuProvider.REIRYOKU).ifPresent(reiryoku -> {
                        reiryoku.increaseReiryoku(valueChange);});
                    break;
                case 1:
                    player.getCapability(ReiryokuProvider.REIRYOKU).ifPresent(reiryoku -> {
                        reiryoku.decreaseReiryoku(valueChange);});
                    break;
                default:
                    break;
            }
        });
        return true;
    }
}
