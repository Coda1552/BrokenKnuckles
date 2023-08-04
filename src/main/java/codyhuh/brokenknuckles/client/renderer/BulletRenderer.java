package codyhuh.brokenknuckles.client.renderer;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.models.BulletModel;
import codyhuh.brokenknuckles.common.entities.item.Bullet;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class BulletRenderer<T extends Bullet> extends EntityRenderer<T> {
   private static final ResourceLocation LOC = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/entity/bullet.png");
   protected final BulletModel<T> model;

   public BulletRenderer(EntityRendererProvider.Context pContext) {
      super(pContext);
      this.shadowRadius = 0.7F;
      this.model = new BulletModel<>(pContext.bakeLayer(BKModelLayers.BULLET));
   }

   public void render(T pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
      super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);

      pMatrixStack.pushPose();

      pMatrixStack.scale(-1.0F, -1.0F, 1.0F);
      this.model.setupAnim(pEntity, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
      VertexConsumer vertexconsumer = pBuffer.getBuffer(this.model.renderType(this.getTextureLocation(pEntity)));
      this.model.renderToBuffer(pMatrixStack, vertexconsumer, pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

      pMatrixStack.popPose();
   }

   public ResourceLocation getTextureLocation(T pEntity) {
      return LOC;
   }

}