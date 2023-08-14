package codyhuh.brokenknuckles.network;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.network.packet.InvisibleDataSyncS2CPacket;
import codyhuh.brokenknuckles.network.packet.UpdateInvisC2SPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }
    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(BrokenKnuckles.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(UpdateInvisC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(UpdateInvisC2SPacket::new)
                .encoder(UpdateInvisC2SPacket::toBytes)
                .consumerMainThread(UpdateInvisC2SPacket::handle)
                .add();

        net.messageBuilder(InvisibleDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(InvisibleDataSyncS2CPacket::new)
                .encoder(InvisibleDataSyncS2CPacket::toBytes)
                .consumerMainThread(InvisibleDataSyncS2CPacket::handle)
                .add();
    }
    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
