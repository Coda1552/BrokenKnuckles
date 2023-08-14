package codyhuh.brokenknuckles.common.capability;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class InvisibleProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    public static Capability<Invisible> INVISIBLE = CapabilityManager.get(new CapabilityToken<Invisible>() { });
    private Invisible invisible = null;
    private final LazyOptional<Invisible> optional = LazyOptional.of(this::createInvisible);

    private Invisible createInvisible() {
        if(this.invisible == null) {
            this.invisible = new Invisible();
        }

        return this.invisible;
    }
    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == INVISIBLE) {
            return optional.cast();
        }

        return LazyOptional.empty();
    }
    @Override
    public CompoundTag serializeNBT() {
        CompoundTag nbt = new CompoundTag();
        createInvisible().saveNBTData(nbt);
        return nbt;
    }
    @Override
    public void deserializeNBT(CompoundTag nbt) {
        createInvisible().loadNBTData(nbt);
    }


}
