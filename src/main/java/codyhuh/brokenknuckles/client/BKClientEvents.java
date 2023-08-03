package codyhuh.brokenknuckles.client;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.models.DwarvenSteelArmorModel;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BKClientEvents {

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BKModelLayers.DWARVEN_STEEL_ARMOR, DwarvenSteelArmorModel::createBodyLayer);
    }

}
