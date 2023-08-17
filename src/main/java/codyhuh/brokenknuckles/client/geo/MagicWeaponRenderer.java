package codyhuh.brokenknuckles.client.geo;

import codyhuh.brokenknuckles.common.items.ShadowWeaponItem;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class MagicWeaponRenderer extends GeoItemRenderer<ShadowWeaponItem> {
    public MagicWeaponRenderer() {
        super(new MagicWeaponModel());
    }
}
