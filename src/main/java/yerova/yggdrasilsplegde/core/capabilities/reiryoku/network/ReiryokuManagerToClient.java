package yerova.yggdrasilsplegde.core.capabilities.reiryoku.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.client.ClientReiryoku;

import java.util.function.Supplier;

public class ReiryokuManagerToClient {

    private final int reiryoku, maxReiryoku;
    public ReiryokuManagerToClient(int reiryoku, int maxReiryoku) {
        this.reiryoku = reiryoku;
        this.maxReiryoku = maxReiryoku;
    }

    public ReiryokuManagerToClient(FriendlyByteBuf buffer) {
        this.reiryoku = buffer.readInt();
        this.maxReiryoku = buffer.readInt();
    }
    public void encode(FriendlyByteBuf buffer) {
        buffer.writeInt(reiryoku);
        buffer.writeInt(maxReiryoku);
    }
    public boolean handle(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        ctx.enqueueWork(() -> {

            ClientReiryoku.set(reiryoku, maxReiryoku);
        });
        return true;
    }

}