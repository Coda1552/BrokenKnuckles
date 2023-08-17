package codyhuh.brokenknuckles.client.models;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.RenderType;

public class AltarDisplayModel extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	//public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "altar_display_converted"), "main");
	public final ModelPart group;
	public final ModelPart altarDisplay;



	public AltarDisplayModel(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.group = root;
		this.altarDisplay = group.getChild("altar_display_sector");

	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition altar_display_sector = partdefinition.addOrReplaceChild("altar_display_sector", CubeListBuilder.create(), PartPose.offset(0.0F, 16.0F, 0.0F));
		PartDefinition altar_pedestal = altar_display_sector.addOrReplaceChild("altar_pedestal", CubeListBuilder.create().texOffs(16, 0).addBox(-8.0F, -2.0F, -8.0F, 16.0F, 10.0F, 16.0F, new CubeDeformation(0.0F)),PartPose.offset(0.0F, -8.0F, 0.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j, float f, float g, float h, float k) {
		group.render(poseStack, vertexConsumer, i, j, f, g, h, k);
	}
}