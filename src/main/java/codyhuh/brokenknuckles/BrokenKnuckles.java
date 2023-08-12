package codyhuh.brokenknuckles;

import codyhuh.brokenknuckles.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BrokenKnuckles.MOD_ID)
public class BrokenKnuckles {
    public static final String MOD_ID = "brokenknuckles";

    public BrokenKnuckles() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(bus);
        ModEnchantments.ENCHANTMENTS.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModBlockEntities.register(bus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
