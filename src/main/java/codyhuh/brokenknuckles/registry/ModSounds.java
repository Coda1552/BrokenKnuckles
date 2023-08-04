package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<SoundEvent> FLINTKNOCK_SHOOT = create("flintknock.shoot");
    public static final RegistryObject<SoundEvent> FLINTKNOCK_RELOAD = create("flintknock.reload");

    private static RegistryObject<SoundEvent> create(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(BrokenKnuckles.MOD_ID, name)));
    }
}
