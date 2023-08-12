package codyhuh.brokenknuckles.common.blocks;

import codyhuh.brokenknuckles.common.blocks.entity.DeepSlateChiseledBookshelfBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.ChiseledBookShelfBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class DeepSlateChiseledBookshelfBlock extends ChiseledBookShelfBlock {
    public DeepSlateChiseledBookshelfBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new DeepSlateChiseledBookshelfBlockEntity(pPos, pState);
    }
}
