package codyhuh.brokenknuckles.network.packet;

import codyhuh.brokenknuckles.client.data.ClientInvisibleData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import org.apache.commons.compress.archivers.sevenz.CLI;

import java.util.function.Supplier;

public class InvisibleDataSyncS2CPacket {
    private final boolean invisible;
    public InvisibleDataSyncS2CPacket(boolean invisible) {
        this.invisible = invisible;
    }

    public InvisibleDataSyncS2CPacket(FriendlyByteBuf buf) {
        this.invisible = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(invisible);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        // HERE WE ARE ON THE CLIENT!
        context.enqueueWork(ClientInvisibleData::toggleInvisible);
        return true;
    }
}
