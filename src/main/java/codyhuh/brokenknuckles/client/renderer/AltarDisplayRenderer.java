package codyhuh.brokenknuckles.client.renderer;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.models.AltarDisplayModel;
import codyhuh.brokenknuckles.common.blocks.entity.AltarDisplayBlockEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.Level;

public class AltarDisplayRenderer<T extends AltarDisplayBlockEntity> implements BlockEntityRenderer<T> {
    public static final ResourceLocation TEXTURE = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/block/altar_display.png");
    private final ItemRenderer itemRenderer;
    private final EntityRenderDispatcher entityRenderer;
    private static AltarDisplayModel altarDisplay;

    public AltarDisplayRenderer(BlockEntityRendererProvider.Context context) {
        this.itemRenderer = context.getItemRenderer();
        this.entityRenderer = context.getEntityRenderer();
        altarDisplay = new AltarDisplayModel(context.bakeLayer(BKModelLayers.ALTAR_DISPLAY));
    }

    @Override
    public void render(T pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        Level world = pBlockEntity.getLevel();
        assert world != null;
        long gameTime = world.getGameTime();
        float offsetY = (float)Math.sin((float)gameTime / 8.0F) * 0.025F;
        float altarBlockEntity = AltarDisplayBlockEntity.tick;
        float tick = altarBlockEntity / 10.0F;
        pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));
        altarDisplay.renderToBuffer(pPoseStack, pBuffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE)), pPackedLight, pPackedOverlay  , 1.0F, 1.0F, 1.0F, 1.0F);
        altarDisplay.altarDisplay.setPos(8.0F, 0.0F, -8.0F);
        if (pBlockEntity.hasLevel() && !pBlockEntity.isEmpty()) {
            pPoseStack.pushPose();
            pPoseStack.translate(0.5, (double)offsetY - 1.0, -0.5);
            pPoseStack.scale(0.5F, 0.5F, 0.5F);
            pPoseStack.mulPose(Axis.YP.rotationDegrees(tick % 360.0F));
            pPoseStack.mulPose(Axis.XP.rotationDegrees(-180.0F));

            this.itemRenderer.renderStatic(pBlockEntity.getItem(0), ItemDisplayContext.FIXED, pPackedLight, OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, pBlockEntity.getLevel(), pPackedOverlay);
            pPoseStack.popPose();
        }
    }
}
