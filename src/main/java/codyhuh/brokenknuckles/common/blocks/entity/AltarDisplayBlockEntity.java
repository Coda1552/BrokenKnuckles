package codyhuh.brokenknuckles.common.blocks.entity;

import codyhuh.brokenknuckles.registry.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class AltarDisplayBlockEntity extends BlockEntity implements WorldlyContainer {
    public static float tick = 0;
    ItemStack item;
    public AltarDisplayBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.ALTAR_DISPLAY_BE.get(), pPos, pBlockState);
        this.item = ItemStack.EMPTY;
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        return new int[0];
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return false;
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return false;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return this.item.isEmpty();
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return pSlot == 0 ? this.item : ItemStack.EMPTY;
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        ItemStack item = this.removeItemNoUpdate(pSlot);
        this.update(pAmount);
        return item;
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        if (pSlot == 0) {
            ItemStack display = this.item;
            this.item = ItemStack.EMPTY;
            return display;
        } else {
            return ItemStack.EMPTY;
        }
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        if (pSlot == 0) {
            this.item = pStack;
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.worldPosition.distSqr(pPlayer.blockPosition()) <= 16.0;
    }

    @Override
    public void clearContent() {
        this.item = ItemStack.EMPTY;
    }
    public void update(int j) {
        this.setChanged();
        if (this.getLevel() != null) {
            this.getLevel().sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), j);
        }

    }
    public void load(@NotNull CompoundTag compoundTag) {
        super.load(compoundTag);
        if (compoundTag.contains("item")) {
            this.item = ItemStack.of(compoundTag.getCompound("item"));
        }

    }
    protected void saveAdditional(@NotNull CompoundTag compoundTag) {
        super.saveAdditional(compoundTag);
        if (!this.item.isEmpty()) {
            compoundTag.put("item", this.item.save(new CompoundTag()));
        }
    }
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return tag;
    }

    public int getMaxStackSize() {
        return 64;
    }
}
