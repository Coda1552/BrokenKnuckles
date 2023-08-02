package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

public class ModToolTiers {
    public static final Tier DWARVEN_STEEL = TierSortingRegistry.registerTier(
            new ForgeTier(5, 2500, 8.5F, 5.0F, 25,
                    ModTags.NEEDS_DWARVEN_TOOL, () -> Ingredient.of(ModItems.DWARVEN_ARMOR_PARTS.get())),
            new ResourceLocation(BrokenKnuckles.MOD_ID, "dwarven_steel"), List.of(Tiers.NETHERITE), List.of());

    public static final Tier DWARVEN_STEEL_HAMMER = TierSortingRegistry.registerTier(
            new ForgeTier(5, 25000, 6.0F, 5.0F, 25,
                    ModTags.NEEDS_DWARVEN_TOOL, () -> Ingredient.of(ModItems.DWARVEN_ARMOR_PARTS.get())),
            new ResourceLocation(BrokenKnuckles.MOD_ID, "dwarven_steel_hammer"), List.of(Tiers.NETHERITE), List.of());
}
