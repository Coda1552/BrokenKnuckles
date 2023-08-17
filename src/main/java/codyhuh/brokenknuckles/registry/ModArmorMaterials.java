package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import io.redspace.ironsspellbooks.registries.AttributeRegistry;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    DWARVEN("dwarven", 40, makeArmorMap(3,6,8,3), 18, SoundEvents.ARMOR_EQUIP_NETHERITE, 2.0F, 0.15F,
            () -> {return Ingredient.of(new ItemLike[]{(ItemLike) ModItems.DWARVEN_ARMOR_PARTS.get()});
    }, Map.of((Attribute)AttributeRegistry.SPELL_RESIST.get(), new AttributeModifier("Spell Resist", 0.025, AttributeModifier.Operation.MULTIPLY_BASE))),

    OCEAN("ocean", 37, makeArmorMap(3, 6,8, 3),12, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, () ->
    {return Ingredient.of(new ItemLike[]{(ItemLike) Items.HEART_OF_THE_SEA});}, Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 50, AttributeModifier.Operation.ADDITION))),

    INVIS("invis", 37, armorMap(), 20, SoundEvents.ARMOR_EQUIP_LEATHER, 0.0F, 0.0F,
            () -> {return Ingredient.of(new ItemLike[]{(ItemLike) ModItems.MAGIC_CLOTH.get()});
    }, Map.of((Attribute)AttributeRegistry.MAX_MANA.get(), new AttributeModifier("Max Mana", 75.0, AttributeModifier.Operation.ADDITION), (Attribute)AttributeRegistry.SPELL_POWER.get(), new AttributeModifier("Spell Power", 0.05, AttributeModifier.Operation.MULTIPLY_BASE),(Attribute)AttributeRegistry.COOLDOWN_REDUCTION.get(), new AttributeModifier("Cooldown Reduction", 0.05, AttributeModifier.Operation.MULTIPLY_BASE), (Attribute)AttributeRegistry.CAST_TIME_REDUCTION.get(), new AttributeModifier("Cast Time Reduction", 0.05, AttributeModifier.Operation.MULTIPLY_BASE)));


    private static final EnumMap<ArmorItem.Type, Integer> HEALTH_FUNCTION_FOR_TYPE = Util.make(new EnumMap<>(ArmorItem.Type.class), (p_266653_) -> {
        p_266653_.put(ArmorItem.Type.BOOTS, 13);
        p_266653_.put(ArmorItem.Type.LEGGINGS, 15);
        p_266653_.put(ArmorItem.Type.CHESTPLATE, 16);
        p_266653_.put(ArmorItem.Type.HELMET, 11);
    });
    public static EnumMap<ArmorItem.Type, Integer> armorMap() {
        return makeArmorMap(3, 7, 6, 2);
    }
    public static EnumMap<ArmorItem.Type, Integer> makeArmorMap(int helmet, int chestplate, int leggings, int boots) {
        return (EnumMap)Util.make(new EnumMap(ArmorItem.Type.class), (p_266655_) -> {
            p_266655_.put(ArmorItem.Type.BOOTS, boots);
            p_266655_.put(ArmorItem.Type.LEGGINGS, leggings);
            p_266655_.put(ArmorItem.Type.CHESTPLATE, chestplate);
            p_266655_.put(ArmorItem.Type.HELMET, helmet);
        });
    }
    private final String name;
    private final int durabilityMultiplier;
    private final EnumMap<ArmorItem.Type, Integer> protectionFunctionForType;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;
    private final Map<Attribute, AttributeModifier> additionalAttributes;

    ModArmorMaterials(String pName, int pDurabilityMultiplier, EnumMap<ArmorItem.Type, Integer> pProtectionFunctionForType, int pEnchantmentValue, SoundEvent pSound, float pToughness, float pKnockbackResistance, Supplier repairMaterial, Map additionalAttributes) {
        this.name = pName;
        this.durabilityMultiplier = pDurabilityMultiplier;
        this.protectionFunctionForType = pProtectionFunctionForType;
        this.enchantmentValue = pEnchantmentValue;
        this.sound = pSound;
        this.toughness = pToughness;
        this.knockbackResistance = pKnockbackResistance;
        this.repairIngredient = new LazyLoadedValue(repairMaterial);
        this.additionalAttributes = additionalAttributes;
    }

    public int getDurabilityForType(ArmorItem.Type pType) {
        return HEALTH_FUNCTION_FOR_TYPE.get(pType) * this.durabilityMultiplier;
    }

    public Map<Attribute, AttributeModifier> getAdditionalAttributesE() {
        return this.additionalAttributes;
    }
    public int getDefenseForType(ArmorItem.Type pType) {
        return this.protectionFunctionForType.get(pType);
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    public String getName() {
        return BrokenKnuckles.MOD_ID + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }


    public String getSerializedName() {
        return BrokenKnuckles.MOD_ID + ":" + this.name;
    }

}
