package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.enchantments.HoleDiggerEnchantment;
import codyhuh.brokenknuckles.common.items.DwarvenHammerItem;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments {
    public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, BrokenKnuckles.MOD_ID);

    public static final EnchantmentCategory HAMMER = EnchantmentCategory.create("hammer", (item -> item instanceof DwarvenHammerItem));

    public static final RegistryObject<Enchantment> HOLE_DIGGER =
            ENCHANTMENTS.register("hole_digger",
                    () -> new HoleDiggerEnchantment(Enchantment.Rarity.RARE, HAMMER, EquipmentSlot.MAINHAND));

    public static void register(IEventBus eventBus) {
        ENCHANTMENTS.register(eventBus);
    }
}
