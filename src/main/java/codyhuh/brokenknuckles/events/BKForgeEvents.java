package codyhuh.brokenknuckles.events;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.items.DwarvenArmorItem;
import codyhuh.brokenknuckles.common.items.DwarvenHammerItem;
import codyhuh.brokenknuckles.registry.ModEnchantments;
import io.redspace.ironsspellbooks.damage.ISSDamageTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BKForgeEvents {

    @SubscribeEvent
    public static void registerArmorMods(LivingDamageEvent event) {
        int count = 0;
        DamageSource source = event.getSource();
        float amount = event.getAmount();
        boolean isCorrectType = checkDamageType(source);
        LivingEntity entity = event.getEntity();
        List<EquipmentSlot> slots = List.of(EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET);
        for (EquipmentSlot slot : slots) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (stack.getItem() instanceof DwarvenArmorItem) {
                if (isCorrectType) {
                    count++;
                }
            }
        }
        switch (count){
            case 1:
                event.setAmount(amount-(amount*0.1f));
                //entity.sendSystemMessage(Component.translatable("event.dwarven_bai.damagereduce1"));
                break;
            case 2:
                event.setAmount(amount-(amount*0.2f));
                //entity.sendSystemMessage(Component.translatable("event.dwarven_bai.damagereduce2"));
                break;
            case 3:
                event.setAmount(amount-(amount*0.3f));
                //entity.sendSystemMessage(Component.translatable("event.dwarven_bai.damagereduce3"));
                break;
            case 4:
                event.setAmount(amount-(amount*0.4f));
                //entity.sendSystemMessage(Component.translatable("event.dwarven_bai.damagereduce4"));
                break;
            default:
                //entity.sendSystemMessage(Component.translatable("event.dwarven_bai.damagereduce0"));
                break;
        }
    }
    public static boolean checkDamageType(DamageSource source){
        return source.is(DamageTypes.MAGIC) || source.is(DamageTypes.INDIRECT_MAGIC) || source.is(DamageTypes.LIGHTNING_BOLT)
               || source.is(DamageTypes.WITHER) || source.is(DamageTypes.WITHER_SKULL) || source.is(DamageTypes.DRAGON_BREATH)
               || source.is(ISSDamageTypes.BLOOD_MAGIC) || source.is(ISSDamageTypes.ENDER_MAGIC) || source.is(ISSDamageTypes.EVOCATION_MAGIC)
               || source.is(ISSDamageTypes.FIRE_MAGIC) ||source.is(ISSDamageTypes.CAULDRON) || source.is(ISSDamageTypes.DRAGON_BREATH_POOL)
               || source.is(ISSDamageTypes.FIRE_FIELD) || source.is(ISSDamageTypes.HEARTSTOP) || source.is(ISSDamageTypes.HOLY_MAGIC)
               || source.is(ISSDamageTypes.ICE_MAGIC) || source.is(ISSDamageTypes.LIGHTNING_MAGIC) || source.is(ISSDamageTypes.POISON_CLOUD)
               || source.is(ISSDamageTypes.POISON_MAGIC) || source.is(ISSDamageTypes.VOID_MAGIC);
    }
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();
    @SubscribeEvent
    public static void onHammerUsage(BlockEvent.BreakEvent event) {

        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();
        boolean enchanted = hasEnchantment(ModEnchantments.HOLE_DIGGER.get(), mainHandItem);
        if(mainHandItem.getItem() instanceof DwarvenHammerItem hammer && player instanceof ServerPlayer serverPlayer && !player.isCreative()) {
            BlockPos initalBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initalBlockPos)) {
                return;
            }

            if(enchanted){
                for (BlockPos pos : DwarvenHammerItem.getBlocksToBeDestroyed(2, initalBlockPos, serverPlayer)) {
                    if(pos == initalBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                        continue;
                    }

                    // Have to add them to a Set otherwise, the same code right here will get called for each block!
                    HARVESTED_BLOCKS.add(pos);
                    serverPlayer.gameMode.destroyBlock(pos);
                    HARVESTED_BLOCKS.remove(pos);
                }
            }
            if(!enchanted){
                for (BlockPos pos : DwarvenHammerItem.getBlocksToBeDestroyed(1, initalBlockPos, serverPlayer)) {
                    if(pos == initalBlockPos || !hammer.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                        continue;
                    }

                    // Have to add them to a Set otherwise, the same code right here will get called for each block!
                    HARVESTED_BLOCKS.add(pos);
                    serverPlayer.gameMode.destroyBlock(pos);
                    HARVESTED_BLOCKS.remove(pos);
                }
            }

        }
    }
    public static boolean hasEnchantment(Enchantment ench, ItemStack stack) {
        return EnchantmentHelper.getEnchantments(stack).containsKey(ench);
    }

}
