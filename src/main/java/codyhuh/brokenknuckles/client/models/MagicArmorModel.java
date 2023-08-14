package codyhuh.brokenknuckles.client.models;// Made with Blockbench 4.8.1
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class MagicArmorModel extends HumanoidModel<LivingEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

	

	public MagicArmorModel (ModelPart root) {
		super((root));
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = HumanoidModel.createMesh(LayerDefinitions.INNER_ARMOR_DEFORMATION, 0.0F);;
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.ZERO);
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.ZERO);
		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 16).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.ZERO);
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, LayerDefinitions.INNER_ARMOR_DEFORMATION), PartPose.ZERO);

		PartDefinition armorHead = head.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(0, 12).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorBody = body.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(0, 28).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.26F))
		.texOffs(0, 0).addBox(-5.0F, -27.0F, -3.0F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition armorRightArm = rightArm.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(40, 36).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.0F, 0.0F, 0.0F));
		//PartDefinition armorRightArm = rightArm.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(74, 99).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.0F, 0.0F, 0.0F));
		PartDefinition armorLeftArm = leftArm.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(24, 36).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition armorLeftLeg = leftLeg.addOrReplaceChild("armorLeftLeg", CubeListBuilder.create().texOffs(56, 12).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorLeftBoot = leftLeg.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create().texOffs(24, 12).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		//PartDefinition armorLeftBoot = leftLeg.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create().texOffs(24, 12).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorRightLeg = rightLeg.addOrReplaceChild("armorRightLeg", CubeListBuilder.create().texOffs(56, 25).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.24F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorRightBoot = rightLeg.addOrReplaceChild("armorRightBoot", CubeListBuilder.create().texOffs(56, 38).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		//PartDefinition armorRightBoot = rightLeg.addOrReplaceChild("armorRightBoot", CubeListBuilder.create().texOffs(56, 38).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


}