package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.models.DwarvenSteelArmorModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class DwarvenArmorItem extends ArmorItem {
    private static final String LOC = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/dwarven_steel_armor.png").toString();
    private static final String LOC_HEAD = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/dwarven_steel_armor_head.png").toString();
    private static final String LOC_LEGS = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/dwarven_steel_armor_legs.png").toString();

    public DwarvenArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        switch (slot) {
            case HEAD:
                return LOC_HEAD;
            case LEGS:
                return LOC_LEGS;
            default:
                return LOC;
        }
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private DwarvenSteelArmorModel model;

            @Nullable
            @Override
            public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new DwarvenSteelArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(BKModelLayers.DWARVEN_STEEL_ARMOR));
                }
                return model;
            }
        });
    }
}
