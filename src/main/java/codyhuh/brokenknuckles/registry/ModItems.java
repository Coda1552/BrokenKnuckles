package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.items.SoulBladeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<Item> SOUL_BLADE = ITEMS.register("soul_blade", () -> new SoulBladeItem(Tiers.IRON, 3, -2.55F, new Item.Properties().rarity(Rarity.EPIC)));
}
