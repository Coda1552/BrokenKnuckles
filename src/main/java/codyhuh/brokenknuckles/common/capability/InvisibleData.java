package codyhuh.brokenknuckles.common.capability;

import net.minecraft.nbt.CompoundTag;

public class InvisibleData {
    private boolean invisible;

    public boolean getInvisibilityState() {
        return invisible;
    }

    public void toggleInvisible() {
        invisible = !this.invisible;
    }

    public void copyFrom(InvisibleData source) {
        this.invisible = source.invisible;
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putBoolean("invisible", invisible);
    }

    public void loadNBTData(CompoundTag nbt) {
        invisible = nbt.getBoolean("invisible");
    }
}
