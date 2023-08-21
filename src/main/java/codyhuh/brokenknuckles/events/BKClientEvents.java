package codyhuh.brokenknuckles.events;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.geo.SimpleGeoModel;
import codyhuh.brokenknuckles.client.geo.SimpleGeoRenderer;
import codyhuh.brokenknuckles.client.models.*;
import codyhuh.brokenknuckles.client.renderer.AltarDisplayRenderer;
import codyhuh.brokenknuckles.client.renderer.BulletRenderer;
import codyhuh.brokenknuckles.client.renderer.GrungSpearExplodingRenderer;
import codyhuh.brokenknuckles.network.ModMessages;
import codyhuh.brokenknuckles.network.packet.UpdateInvisC2SPacket;
import codyhuh.brokenknuckles.registry.ModBlockEntities;
import codyhuh.brokenknuckles.registry.ModEntities;
import codyhuh.brokenknuckles.registry.ModItems;
import codyhuh.brokenknuckles.util.BKKeyBindings;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BKClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        ItemProperties.register(ModItems.FLINTKNOCK_PISTOL.get(), new ResourceLocation("charged"), (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F);

    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers e) {
        // "Vanilla" format models
        e.registerEntityRenderer(ModEntities.BULLET.get(), BulletRenderer::new);
        e.registerEntityRenderer(ModEntities.SPEAR.get(), GrungSpearExplodingRenderer::new);
        // Block entity models
        BlockEntityRenderers.register(ModBlockEntities.ALTAR_DISPLAY_BE.get(), AltarDisplayRenderer::new);
        // Geckolib models
        e.registerEntityRenderer(ModEntities.DANDY_DEER.get(), mgr -> new SimpleGeoRenderer<>(mgr, new SimpleGeoModel<>(new ResourceLocation(BrokenKnuckles.MOD_ID, "dandy_deer"))));
        e.registerEntityRenderer(ModEntities.SEA_BEAK.get(), mgr -> new SimpleGeoRenderer<>(mgr, new SimpleGeoModel<>(new ResourceLocation(BrokenKnuckles.MOD_ID, "sea_beak"))));
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(BKModelLayers.DWARVEN_STEEL_ARMOR, DwarvenSteelArmorModel::createBodyLayer);
        e.registerLayerDefinition(BKModelLayers.BULLET, BulletModel::createBodyLayer);
        e.registerLayerDefinition(BKModelLayers.MAGIC_ARMOR, MagicArmorModel::createBodyLayer);
        e.registerLayerDefinition(BKModelLayers.ALTAR_DISPLAY, AltarDisplayModel::createBodyLayer);
        e.registerLayerDefinition(BKModelLayers.GRUNG_SPEAR, GrungSpearModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event) {
        event.register(BKKeyBindings.INVIS_KEY);
    }

    @Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(BKKeyBindings.INVIS_KEY.consumeClick()) {
                ModMessages.sendToServer(new UpdateInvisC2SPacket());
            }
        }
    }
}

