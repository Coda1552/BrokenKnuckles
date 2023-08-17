package codyhuh.brokenknuckles.client;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.models.AltarDisplayModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BKModelLayers {
    public static final ModelLayerLocation DWARVEN_STEEL_ARMOR = create("dwarven_steel_armor");
    public static final ModelLayerLocation BULLET = create("bullet");
    public static final ModelLayerLocation MAGIC_ARMOR = create("magic_armor");
    public static final ModelLayerLocation ALTAR_DISPLAY = create("altar_display");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(BrokenKnuckles.MOD_ID, name), "main");
    }
}
