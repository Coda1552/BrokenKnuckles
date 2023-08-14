package codyhuh.brokenknuckles.network.packet;

import codyhuh.brokenknuckles.common.capability.InvisibleProvider;
import codyhuh.brokenknuckles.network.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateInvisC2SPacket {
    public UpdateInvisC2SPacket() {

    }

    public UpdateInvisC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            assert player != null;
            player.getCapability(InvisibleProvider.INVISIBLE).ifPresent(invisible -> {
                invisible.toggleInvisible();
                ModMessages.sendToPlayer(new InvisibleDataSyncS2CPacket(invisible.getInvisibilityState()), player);
            });

        });
        return true;
    }
}
