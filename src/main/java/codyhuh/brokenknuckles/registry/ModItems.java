package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.items.*;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<Item> SOUL_BLADE = ITEMS.register("soul_blade", () -> new SoulBladeItem(Tiers.IRON, 3, -2.55F, new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FLINTKNOCK_PISTOL = ITEMS.register("flintknock_pistol", () -> new FlintknockPistolItem(new Item.Properties().rarity(Rarity.UNCOMMON).durability(180)));
    public static final RegistryObject<Item> BULLET = ITEMS.register("bullet", () -> new BulletItem(new Item.Properties()));

    public static final RegistryObject<Item> SEA_BEAK_SPAWN_EGG = ITEMS.register("sea_beak_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.SEA_BEAK, 0x354443, 0x57f5a1, new Item.Properties()));
    public static final RegistryObject<Item> DANDY_DEER_SPAWN_EGG = ITEMS.register("dandy_deer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntities.DANDY_DEER, 0xa6ef71, 0xcfece1, new Item.Properties()));

    public static final RegistryObject<Item> DWARVEN_ARMOR_PARTS = ITEMS.register("dwarven_armor_parts", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MAGIC_CLOTH = ITEMS.register("magic_cloth", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DWARVEN_STEEL_NUGGET = ITEMS.register("dwarven_steel_nugget", () -> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_UPGRADE_TEMPLATE = ITEMS.register("dwarven_template", ()-> new Item(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_HELMET = ITEMS.register("dwarven_steel_helmet", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.HELMET, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_CHESTPLATE = ITEMS.register("dwarven_steel_chestplate", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.CHESTPLATE, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_LEGGINGS = ITEMS.register("dwarven_steel_leggings", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.LEGGINGS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_BOOTS = ITEMS.register("dwarven_steel_boots", () -> new DwarvenArmorItem(ModArmorMaterials.DWARVEN, ArmorItem.Type.BOOTS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_AXE = ITEMS.register("dwarven_steel_axe", () -> new DwarvenAxeItem(ModToolTiers.DWARVEN_STEEL, 3, -3, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> DWARVEN_STEEL_HAMMER = ITEMS.register("dwarven_steel_hammer", () -> new DwarvenHammerItem(5, -3.5F, ModToolTiers.DWARVEN_STEEL_HAMMER, new Item.Properties().fireResistant()));

    public static final RegistryObject<Item> OCEAN_HELMET = ITEMS.register("ocean_helmet", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_CHESTPLATE = ITEMS.register("ocean_chestplate", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_LEGGINGS = ITEMS.register("ocean_leggings", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> OCEAN_BOOTS = ITEMS.register("ocean_boots", () -> new OceanArmorItem(ModArmorMaterials.OCEAN, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> MAGIC_CROWN = ITEMS.register("magic_crown", () -> new CultArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_CLOAK = ITEMS.register("magic_cloak", () -> new CultArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_PANTS = ITEMS.register("magic_pants", () -> new CultArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> MAGIC_BOOTS = ITEMS.register("magic_boots", () -> new CultArmorItem(ModArmorMaterials.INVIS, ArmorItem.Type.BOOTS, new Item.Properties()));
    public static final RegistryObject<Item> SHADOW_CONTROLLER = ITEMS.register("shadow_controller_permanent", () -> new ShadowControllerItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TEMP_SHADOW_CONTROLLER = ITEMS.register("shadow_controller_temp", () -> new TempShadowControllerItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> BARRIER_SETTINGS_WAND = ITEMS.register("barrier_settings_wand", () -> new SettingsWandItem(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> MAGIC_WEAPON = ITEMS.register("magic_weapon", () -> new ShadowWeaponItem(new Item.Properties().fireResistant().stacksTo(1)));
}
