package codyhuh.brokenknuckles.common.blocks;

import codyhuh.brokenknuckles.common.items.ShadowControllerItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class MagicBarrierHubBlock extends GlassBlock {
    public MagicBarrierHubBlock(Properties pProperties) {
        super(pProperties);
    }

}
