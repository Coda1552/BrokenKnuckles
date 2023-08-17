package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.models.DwarvenSteelArmorModel;
import codyhuh.brokenknuckles.registry.ModArmorMaterials;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class DwarvenArmorItem extends ArmorItem {
    private static final String LOC = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/dwarven_steel_armor.png").toString();
    private static final String LOC_HEAD = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/dwarven_steel_armor_head.png").toString();
    private static final String LOC_LEGS = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/dwarven_steel_armor_legs.png").toString();

    public DwarvenArmorItem(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {
        super(ModArmorMaterials.DWARVEN, pType, pProperties);
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        float defense = (float)pMaterial.getDefenseForType(pType);
        float toughness = pMaterial.getToughness();
        float knockbackResistance = pMaterial.getKnockbackResistance();
        UUID uuid = ARMOR_MODIFIER_UUID_PER_SLOT[pType.getSlot().getIndex()];
        builder.put(Attributes.ARMOR, new AttributeModifier(uuid, "Armor modifier", (double)defense, AttributeModifier.Operation.ADDITION));
        builder.put(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(uuid, "Armor toughness", (double)toughness, AttributeModifier.Operation.ADDITION));
        if (knockbackResistance > 0.0F) {
            builder.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Armor knockback resistance", (double)knockbackResistance, AttributeModifier.Operation.ADDITION));
        }
        Iterator var9 = pMaterial.getAdditionalAttributesE().entrySet().iterator();

        while(var9.hasNext()) {
            Map.Entry<Attribute, AttributeModifier> modifierEntry = (Map.Entry)var9.next();
            AttributeModifier atr = (AttributeModifier)modifierEntry.getValue();
            atr = new AttributeModifier(uuid, atr.getName(), atr.getAmount(), atr.getOperation());
            builder.put((Attribute)modifierEntry.getKey(), atr);
        }

        this.ARMOR_ATTRIBUTES = builder.build();
    }
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private final Multimap<Attribute, AttributeModifier> ARMOR_ATTRIBUTES;

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
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return (Multimap)(pEquipmentSlot == this.type.getSlot() ? this.ARMOR_ATTRIBUTES : ImmutableMultimap.of());
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
