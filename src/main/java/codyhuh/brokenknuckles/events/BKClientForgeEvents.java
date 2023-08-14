package codyhuh.brokenknuckles.events;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.network.packet.UpdateInvisC2SPacket;
import codyhuh.brokenknuckles.network.ModMessages;
import codyhuh.brokenknuckles.util.BKKeyBindings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class BKClientForgeEvents {
    @Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(BKKeyBindings.INVIS_KEY.consumeClick()) {
                ModMessages.sendToServer(new UpdateInvisC2SPacket());
            }
        }
    }
    @Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(BKKeyBindings.INVIS_KEY);
        }
    }
}
