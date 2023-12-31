package codyhuh.brokenknuckles.common.blocks;

import codyhuh.brokenknuckles.common.items.SettingsWandItem;
import codyhuh.brokenknuckles.common.items.ShadowControllerItem;
import codyhuh.brokenknuckles.registry.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class MagicBarrierBlock extends GlassBlock {
    public MagicBarrierBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {

            if(pPlayer.getMainHandItem().getItem() instanceof SettingsWandItem){
                pLevel.destroyBlock(pPos, true);
                //pPlayer.addItem(new ItemStack(ModBlocks.MAGIC_BARRIER_BLOCK.get()));
                pPlayer.drop(new ItemStack(ModBlocks.MAGIC_BARRIER_BLOCK.get()), false);
                return InteractionResult.SUCCESS;
            }

        return InteractionResult.FAIL;
    }

    @Override
    public @Nullable PushReaction getPistonPushReaction(BlockState state) {
        return PushReaction.DESTROY;
    }
}
