package codyhuh.brokenknuckles.client.geo;

import codyhuh.brokenknuckles.common.entities.DandyDeer;
import codyhuh.brokenknuckles.common.entities.SeaBeak;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SimpleGeoModel<T extends LivingEntity & GeoEntity> extends DefaultedEntityGeoModel<T> {

    public SimpleGeoModel(ResourceLocation assetSubpath) {
        super(assetSubpath);
    }

    @Override
    public void setCustomAnimations(T animatable, long instanceId, AnimationState<T> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        CoreGeoBone root = getAnimationProcessor().getBone("root");

        if (animatable instanceof SeaBeak seaBeak) {
            if (!seaBeak.isInWater()) {
                root.setRotX(0.0F);
                root.setRotY(0.0F);
                root.setRotZ(1.5708F);
            }
            else {
                EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

                root.setRotX(entityData.headPitch() * ((float)Math.PI / 180F));
                root.setRotY(entityData.netHeadYaw() * ((float)Math.PI / 180F));

                root.setRotZ(0.0F);
            }
        }
    }
}