package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.models.DwarvenSteelArmorModel;
import codyhuh.brokenknuckles.client.models.MagicArmorModel;
import codyhuh.brokenknuckles.common.capability.Invisible;
import codyhuh.brokenknuckles.common.capability.InvisibleProvider;
import codyhuh.brokenknuckles.registry.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import io.redspace.ironsspellbooks.effect.TrueInvisibilityEffect;
import io.redspace.ironsspellbooks.registries.MobEffectRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;


//TO BE IMPLEMENTED
/*
    My armor set
    Armor set ideas:
        Last-chance invincibility ability(5 or 10 seconds of no damage taken and super high damage boost but garunteed death after it ends, can only be activated at 2 or less hearts)
        Magic buffs
        Custom attack ability
        one of these idk
        -Deno

 */



public class CultArmorItem extends ArmorItem {

    private static final String LOC = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/magic_armor.png").toString();
    private static final String LOC_HEAD = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/magic_armor_head.png").toString();
    private static final String LOC_LEGS = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/magic_armor_legs.png").toString();

    private static final String LOC_BOOT = new ResourceLocation(BrokenKnuckles.MOD_ID, "textures/models/armor/magic_armor_boots.png").toString();

    public boolean on = false;
    private static final UUID[] ARMOR_MODIFIER_UUID_PER_SLOT = new UUID[]{UUID.fromString("845DB27C-C624-495F-8C9F-6020A9A58B6B"), UUID.fromString("D8499B04-0E66-4726-AB29-64469D734E0D"), UUID.fromString("9F3D476D-C118-4544-8365-64846904B48E"), UUID.fromString("2AD3F246-FEE1-4E67-B886-69FD380BB150")};

    private final Multimap<Attribute, AttributeModifier> ARMOR_ATTRIBUTES;

    public static Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
            .put(ModArmorMaterials.INVIS, new MobEffectInstance(MobEffects.INVISIBILITY, 100, 0, false, false)).build();


    public CultArmorItem(ModArmorMaterials pMaterial, Type pType, Properties pProperties) {

        super(ModArmorMaterials.INVIS, pType, pProperties);
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
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot pEquipmentSlot) {
        return (Multimap)(pEquipmentSlot == this.type.getSlot() ? this.ARMOR_ATTRIBUTES : ImmutableMultimap.of());
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide() && hasRequiredArmor(player)){
            //if(player.isCrouching()) {
            evaluateArmor(player);
            //} else {
            //on = false;
            //}
        }

    }

    private boolean hasPlayerCorrectArmor(ArmorMaterial mapArmorMaterial, Player player){
        ArmorItem boots = null;
        ArmorItem leggings = null;
        ArmorItem chestplate = null;
        int count = 0;
        if(!(player.getInventory().getArmor(0).getItem() instanceof AirItem)){
            boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
            count++;
        }
        if(!(player.getInventory().getArmor(1).getItem() instanceof AirItem)){
            leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
            count++;
        }
        if(!(player.getInventory().getArmor(2).getItem() instanceof AirItem)){
            chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
            count++;
        }
        if(count == 3){
            return boots.getMaterial() == mapArmorMaterial && leggings.getMaterial() == mapArmorMaterial && chestplate.getMaterial() == mapArmorMaterial;
        } else {
            return false;
        }
    }
    private boolean hasRequiredArmor(Player player){
        ItemStack chest = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !leggings.isEmpty() && !chest.isEmpty() && !boots.isEmpty();
    }
    private void evaluateArmor(Player player){
        for(Map.Entry<ArmorMaterial, MobEffectInstance> entry: MATERIAL_TO_EFFECT_MAP.entrySet()){
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapEffectInstance = entry.getValue();
            player.getCapability(InvisibleProvider.INVISIBLE).ifPresent(invisible -> {
                on = invisible.getInvisibilityState();
            });
            if(on && hasPlayerCorrectArmor(mapArmorMaterial, player)){
                player.addEffect(new MobEffectInstance(MobEffectRegistry.TRUE_INVISIBILITY.get(), 2, 0, false, false));
            }
        }
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {

        if(entity instanceof Player player) {
            if(on && hasPlayerCorrectArmor(ModArmorMaterials.INVIS, player)){
                return "brokenknuckles:textures/models/armor/invis_layer_2_active.png";
            } else {
                switch (slot) {
                    case HEAD:
                        return LOC_HEAD;
                    case LEGS:
                        return LOC_LEGS;
                    case FEET:
                        return LOC_BOOT;
                    default:
                        return LOC;
                }
            }
        }
        switch (slot) {
            case HEAD:
                return LOC_HEAD;
            case LEGS:
                return LOC_LEGS;
            case FEET:
                return LOC_BOOT;
            default:
                return LOC;
        }

    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private MagicArmorModel model;

            @Nullable
            @Override
            public net.minecraft.client.model.HumanoidModel<?> getHumanoidArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlot armorSlot, HumanoidModel<?> _default) {
                if (null == model) {
                    model = new MagicArmorModel(Minecraft.getInstance().getEntityModels().bakeLayer(BKModelLayers.MAGIC_ARMOR));
                }
                return model;
            }
        });
    }
}
