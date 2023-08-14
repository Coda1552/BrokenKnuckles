package codyhuh.brokenknuckles.client;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.geo.SimpleGeoModel;
import codyhuh.brokenknuckles.client.geo.SimpleGeoRenderer;
import codyhuh.brokenknuckles.client.models.BulletModel;
import codyhuh.brokenknuckles.client.models.DwarvenSteelArmorModel;
import codyhuh.brokenknuckles.client.models.MagicArmorModel;
import codyhuh.brokenknuckles.client.renderer.BulletRenderer;
import codyhuh.brokenknuckles.common.entities.DandyDeer;
import codyhuh.brokenknuckles.registry.ModBlocks;
import codyhuh.brokenknuckles.registry.ModEntities;
import codyhuh.brokenknuckles.registry.ModItems;
import codyhuh.brokenknuckles.util.BKKeyBindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BKClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent e) {
        ItemProperties.register(ModItems.FLINTKNOCK_PISTOL.get(), new ResourceLocation("charged"), (p_275891_, p_275892_, p_275893_, p_275894_) -> CrossbowItem.isCharged(p_275891_) ? 1.0F : 0.0F);
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGIC_BARRIER_BLOCK.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.MAGIC_BARRIER_HUB_BLOCK.get(), RenderType.translucent());
    }

    @SubscribeEvent
    public static void registerRenders(EntityRenderersEvent.RegisterRenderers e) {
        // "Vanilla" format models
        e.registerEntityRenderer(ModEntities.BULLET.get(), BulletRenderer::new);

        // Geckolib models
        e.registerEntityRenderer(ModEntities.DANDY_DEER.get(), mgr -> new SimpleGeoRenderer<>(mgr, new SimpleGeoModel<>(new ResourceLocation(BrokenKnuckles.MOD_ID, "dandy_deer"))));
        e.registerEntityRenderer(ModEntities.SEA_BEAK.get(), mgr -> new SimpleGeoRenderer<>(mgr, new SimpleGeoModel<>(new ResourceLocation(BrokenKnuckles.MOD_ID, "sea_beak"))));
    }

    @SubscribeEvent
    public static void registerModelLayers(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(BKModelLayers.DWARVEN_STEEL_ARMOR, DwarvenSteelArmorModel::createBodyLayer);
        e.registerLayerDefinition(BKModelLayers.BULLET, BulletModel::createBodyLayer);
        e.registerLayerDefinition(BKModelLayers.MAGIC_ARMOR, MagicArmorModel::createBodyLayer);
    }
}

