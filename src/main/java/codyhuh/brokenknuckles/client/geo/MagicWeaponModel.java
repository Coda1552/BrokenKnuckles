package codyhuh.brokenknuckles.client.geo;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.items.ShadowWeaponItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import software.bernie.geckolib.model.GeoModel;

public class MagicWeaponModel extends GeoModel<ShadowWeaponItem> {
    @Override
    public ResourceLocation getModelResource(ShadowWeaponItem shadowWeaponItem) {
        return new ResourceLocation(BrokenKnuckles.MOD_ID, "geo/item/magic_weapon_final.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ShadowWeaponItem shadowWeaponItem) {
        return new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/item/magic_weapon_model.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ShadowWeaponItem shadowWeaponItem) {
        return new ResourceLocation(BrokenKnuckles.MOD_ID, "animations/item/animated_magic_weapon_final.animation.json");
    }
}
