package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.registry.ModArmorMaterials;
import com.google.common.collect.ImmutableMap;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Map;

public class OceanArmorItem extends ArmorItem {
    public static Map<ArmorMaterial, MobEffectInstance> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, MobEffectInstance>())
                    .put(ModArmorMaterials.OCEAN, new MobEffectInstance(MobEffects.CONDUIT_POWER, 100, 0, false, false))
                    .build();

    public OceanArmorItem(ArmorMaterial pMaterial, Type pType, Properties pProperties) {
        super(pMaterial, pType, pProperties);
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
    public void onArmorTick(ItemStack stack, Level level, Player player) {
        if (!level.isClientSide() && hasFullArmor(player)) {
            evaluateArmor(player);
        }
    }

    private boolean hasFullArmor(Player player) {
        ItemStack helmet = player.getInventory().getArmor(3);
        ItemStack chest = player.getInventory().getArmor(2);
        ItemStack leggings = player.getInventory().getArmor(1);
        ItemStack boots = player.getInventory().getArmor(0);

        return !helmet.isEmpty() && !leggings.isEmpty() && !chest.isEmpty() && !boots.isEmpty();
    }

    private void evaluateArmor(Player player) {
        for(Map.Entry<ArmorMaterial, MobEffectInstance> entry: MATERIAL_TO_EFFECT_MAP.entrySet()){
            ArmorMaterial mapArmorMaterial = entry.getKey();
            if (player.isSwimming()) {
                if (hasPlayerCorrectArmor(mapArmorMaterial, player)) {
                    player.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 200, 0, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 200, 0, false, false));
                    player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 100, 0, false, false));
                }
            }
            
        }
    }
}
