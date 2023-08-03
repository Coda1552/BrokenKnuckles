package codyhuh.brokenknuckles.client;

import codyhuh.brokenknuckles.BrokenKnuckles;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class BKModelLayers {
    public static final ModelLayerLocation DWARVEN_STEEL_ARMOR = create("dwarven_steel_armor");

    private static ModelLayerLocation create(String name) {
        return new ModelLayerLocation(new ResourceLocation(BrokenKnuckles.MOD_ID, name), "main");
    }
}
