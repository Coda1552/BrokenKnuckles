package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<CreativeModeTab> TAB = CREATIVE_MODE_TABS.register("dwarven_items", () -> CreativeModeTab.builder()
            .icon(() -> new ItemStack(ModItems.DWARVEN_ARMOR_PARTS.get()))
            .title(Component.translatable("itemGroup.brokenkunckles"))
            .displayItems((pParameters, pOutput) -> {
                for (var item : ModItems.ITEMS.getEntries()) {
                    pOutput.accept(item.get());
                }
            }).build());
}
