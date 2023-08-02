package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.items.SoulBladeItem;
import codyhuh.brokenknuckles.common.items.DwarvenArmorItem;
import codyhuh.brokenknuckles.common.items.InvisArmorItem;
import codyhuh.brokenknuckles.common.items.OceanArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<Item> SOUL_BLADE = ITEMS.register("soul_blade", () -> new SoulBladeItem(Tiers.IRON, 3, -2.55F, new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> DWARVEN_ARMOR_PARTS = ITEMS.register("dwarven_armor_parts", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MAGIC_CLOTH = ITEMS.register("magic_cloth", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> DWARVEN_STEEL_HELMET = ITEMS.register("dwarven_steel_helmet", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_CHESTPLATE = ITEMS.register("dwarven_steel_chestplate", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_LEGGINGS = ITEMS.register("dwarven_steel_leggings", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_BOOTS = ITEMS.register("dwarven_steel_boots", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> INVIS_HELMET = ITEMS.register("invis_helmet", () -> new InvisArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> INVIS_CHESTPLATE = ITEMS.register("invis_chestplate", () -> new InvisArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> INVIS_LEGGINGS = ITEMS.register("invis_leggings", () -> new InvisArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> INVIS_BOOTS = ITEMS.register("invis_boots", () -> new InvisArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> OCEAN_HELMET = ITEMS.register("ocean_helmet", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_CHESTPLATE = ITEMS.register("ocean_chestplate", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_LEGGINGS = ITEMS.register("ocean_leggings", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_BOOTS = ITEMS.register("ocean_boots", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.BOOTS, new Item.Properties()));
}
