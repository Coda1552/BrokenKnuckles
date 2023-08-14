package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.client.BKModelLayers;
import codyhuh.brokenknuckles.client.models.DwarvenSteelArmorModel;
import codyhuh.brokenknuckles.client.models.MagicArmorModel;
import codyhuh.brokenknuckles.common.capability.Invisible;
import codyhuh.brokenknuckles.common.capability.InvisibleProvider;
import codyhuh.brokenknuckles.registry.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AirItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import javax.annotation.Nullable;
import java.util.Map;
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

    public boolean on = false;
    public static Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
            .put(ModArmorMaterials.INVIS, new MobEffectInstance(MobEffects.INVISIBILITY, 100, 0, false, false)).build();


    public CultArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
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
                player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, false, false));
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
