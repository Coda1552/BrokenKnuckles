package codyhuh.brokenknuckles;

import codyhuh.brokenknuckles.registry.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BrokenKnuckles.MOD_ID)
public class BrokenKnuckles {
    public static final String MOD_ID = "brokenknuckles";

    public BrokenKnuckles() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(bus);
    }
}
