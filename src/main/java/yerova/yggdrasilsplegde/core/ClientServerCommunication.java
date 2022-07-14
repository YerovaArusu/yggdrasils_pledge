package yerova.yggdrasilsplegde.core;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import yerova.yggdrasilsplegde.YggdrasilsPlegde;
import yerova.yggdrasilsplegde.core.abilities.AbilityManager;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.network.ReiryokuManagerToClient;
import yerova.yggdrasilsplegde.core.capabilities.reiryoku.network.ReiryokuManagerToServer;

public class ClientServerCommunication {

    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() { return  packetId++;};

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(YggdrasilsPlegde.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ReiryokuManagerToServer.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ReiryokuManagerToServer::new)
                .encoder(ReiryokuManagerToServer::encode)
                .consumer(ReiryokuManagerToServer::handle)
                .add();


        net.messageBuilder(ReiryokuManagerToClient.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ReiryokuManagerToClient::new)
                .encoder(ReiryokuManagerToClient::encode)
                .consumer(ReiryokuManagerToClient::handle)
                .add();

        net.messageBuilder(AbilityManager.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(AbilityManager::new)
                .encoder(AbilityManager::encode)
                .consumer(AbilityManager::handle)
                .add();

    }


    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }


    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
