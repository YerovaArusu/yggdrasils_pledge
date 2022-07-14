package yerova.yggdrasilsplegde.core.abilities;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class AbilityManager{

    private int abilityAddressValue;
    public static boolean continueBerserk = false;
    public static boolean continueFullSerenity = false;
    private Thread thread = null;
    private ServerPlayer player;
    public AbilityManager(int abilityAddressValue) {
        this.abilityAddressValue = abilityAddressValue;
    }
    public AbilityManager(FriendlyByteBuf friendlyByteBuf) {
        this(friendlyByteBuf.readInt());
    }

    public void encode(FriendlyByteBuf friendlyByteBuf) {
        friendlyByteBuf.writeInt(abilityAddressValue);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context ctx = contextSupplier.get();

        ctx.enqueueWork(() ->{
            player = ctx.getSender();

            switch (abilityAddressValue) {
                case AbilitySkills.BERSERK_SKILL:
                    continueBerserk = !continueBerserk;
                    thread = new Thread(new BerserkSkill(player));
                    thread.start();
                    break;

                case AbilitySkills.FULL_SERENITY_SKILL:
                    continueFullSerenity = !continueFullSerenity;
                    thread = new Thread(new FullSerenitySkill(player));
                    thread.start();
                    break;

                case(FireballAbility.ID):
                    thread = new Thread(new FireballAbility(player));
                    thread.start();
                    break;
            }

        });
        return true;
    }


}
