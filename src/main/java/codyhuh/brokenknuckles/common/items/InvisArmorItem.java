package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.registry.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Map;

public class InvisArmorItem extends ArmorItem {
    public boolean on = false;
    public static Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP = (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
            .put(ModArmorMaterials.INVIS, new MobEffectInstance(MobEffects.INVISIBILITY, 100, 0, false, false)).build();

    public InvisArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
    }

    @Override
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if(!level.isClientSide() && hasFullArmor(player)){
            //if(player.isCrouching()) {
            evaluateArmor(player);
            //} else {
            //on = false;
            //}
        }
    }

    private boolean hasFullArmor(Player player){
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chest = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !helmet.isEmpty() && !leggings.isEmpty() && !chest.isEmpty() && !boots.isEmpty();
    }

    private void evaluateArmor(Player player){
        for(Map.Entry<ArmorMaterial, MobEffectInstance> entry: MATERIAL_TO_EFFECT_MAP.entrySet()){
            ArmorMaterial mapArmorMaterial = entry.getKey();
            MobEffectInstance mapEffectInstance = entry.getValue();

            if (player.isCrouching()) {
                if (hasPlayerCorrectArmor(mapArmorMaterial, player) && !on) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, false, false));
                    on = true;
                } else if (hasPlayerCorrectArmor(mapArmorMaterial, player) && on) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 2, 0, false, false));
                }
            } else {
                if (on && hasPlayerCorrectArmor(mapArmorMaterial, player)) {
                    player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 1, 0, false, false));
                }
                on = false;
            }

        }
    }

    private boolean hasPlayerCorrectArmor(ArmorMaterial mapArmorMaterial, Player player){
        for (ItemStack armorStack : player.getArmorSlots()) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem boots = ((ArmorItem) player.getInventory().getArmor(0).getItem());
        ArmorItem leggings = ((ArmorItem) player.getInventory().getArmor(1).getItem());
        ArmorItem chestplate = ((ArmorItem) player.getInventory().getArmor(2).getItem());
        ArmorItem helmet = ((ArmorItem) player.getInventory().getArmor(3).getItem());

        return boots.getMaterial() == mapArmorMaterial && leggings.getMaterial() == mapArmorMaterial && chestplate.getMaterial() == mapArmorMaterial && helmet.getMaterial() == mapArmorMaterial;
    }

    @Override
    public @Nullable String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        boolean flag = slot.equals(EquipmentSlot.CHEST) || slot.equals(EquipmentSlot.HEAD);
        if (on && flag) {
            return "dwarven_bai:textures/models/armor/invis_layer_1_active.png";
        } else if (!on && flag) {
            return "dwarven_bai:textures/models/armor/invis_layer_1.png";
        } else if (!on && (slot.equals(EquipmentSlot.LEGS))) {
            return "dwarven_bai:textures/models/armor/invis_layer_2.png";
        } else if (on && (slot.equals(EquipmentSlot.LEGS) || slot.equals(EquipmentSlot.FEET))) {
            return "dwarven_bai:textures/models/armor/invis_layer_2_active.png";
        } else if (!on && (slot.equals(EquipmentSlot.FEET))) {
            return super.getArmorTexture(stack, entity, slot, type);
        }

        return super.getArmorTexture(stack, entity, slot, type);
    }
}
