package codyhuh.brokenknuckles.client.models;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.LayerDefinitions;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

public class DwarvenSteelArmorModel extends HumanoidModel<LivingEntity> {

	public DwarvenSteelArmorModel(ModelPart root) {
		super(root);
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

		PartDefinition armorHead = head.addOrReplaceChild("armorHead", CubeListBuilder.create().texOffs(54, 54).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.5F))
				.texOffs(78, 59).addBox(0.0F, -15.0F, 0.0F, 0.0F, 7.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorBody = body.addOrReplaceChild("armorBody", CubeListBuilder.create().texOffs(82, 82).addBox(-4.0F, -24.0F, -2.0F, 8.0F, 13.0F, 4.0F, new CubeDeformation(0.26F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition armorRightArm = rightArm.addOrReplaceChild("armorRightArm", CubeListBuilder.create().texOffs(74, 99).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(1.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = armorRightArm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(102, 54).addBox(-8.0F, -25.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 22.0F, 0.0F, 0.0F, 0.0F, -0.0436F));

		PartDefinition armorLeftArm = leftArm.addOrReplaceChild("armorLeftArm", CubeListBuilder.create().texOffs(86, 54).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(-1.0F, 0.0F, 0.0F));

		PartDefinition cube_r2 = armorLeftArm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(106, 98).addBox(3.0F, -25.0F, -3.0F, 5.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 22.0F, 0.0F, 0.0F, 0.0F, 0.0436F));

		PartDefinition armorLeftLeg = leftLeg.addOrReplaceChild("armorLeftLeg", CubeListBuilder.create().texOffs(106, 106).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorLeftBoot = leftLeg.addOrReplaceChild("armorLeftBoot", CubeListBuilder.create().texOffs(112, 62).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorRightLeg = rightLeg.addOrReplaceChild("armorRightLeg", CubeListBuilder.create().texOffs(66, 115).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.24F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition armorRightBoot = rightLeg.addOrReplaceChild("armorRightBoot", CubeListBuilder.create().texOffs(82, 115).addBox(-2.0F, 8.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

}