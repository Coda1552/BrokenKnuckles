package codyhuh.brokenknuckles.common.items;

import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SoulBladeItem extends SwordItem {

    public SoulBladeItem(Tier tier, int damage, float speed, Properties properties) {
        super(tier, damage, speed, properties);
    }

    @Override
    public void inventoryTick(ItemStack p_41404_, Level p_41405_, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if (p_41406_ instanceof Player player) {

            if (player.getCooldowns().getCooldownPercent(this, 1.0F) >= 0.8F) {
                for (int j = 0; j < 40; j++) {
                    p_41405_.addParticle(new DustParticleOptions(Vec3.fromRGB24(0x1ac1e8).toVector3f(), 0.5F), player.getRandomX(1.0D), player.getRandomY(), player.getRandomZ(1.0D), 0.0D, 0.0D, 0.0D);
                }
            }

            if (player.getCooldowns().getCooldownPercent(this, 1.0F) > 0.35F) {
                player.resetFallDistance();
            }
        }

    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int time) {
        if (user instanceof Player player) {
            int i = this.getUseDuration(stack) - time;

            if (i < 0) return;

            float f = getPowerForTime(i);

            if (!(f < 0.1F)) {

                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_FRAME_FILL, SoundSource.PLAYERS, 1.0F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                stack.hurtAndBreak(1, player, p -> user.broadcastBreakEvent(player.getUsedItemHand()));

                Vec3 view = player.getViewVector(1.0F);

                player.getCooldowns().addCooldown(this, 100);

                double power = Mth.clamp(f, 0.15D,1.0D) * 4.0D;
                player.setDeltaMovement(view.multiply(power, power * 0.8D, power));
                player.awardStat(Stats.ITEM_USED.get(this));
                player.startAutoSpinAttack(20);
                player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 25, 0, false, false));
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level p_40672_, Player p_40673_, InteractionHand p_40674_) {
        ItemStack itemstack = p_40673_.getItemInHand(p_40674_);
        p_40673_.startUsingItem(p_40674_);
        return InteractionResultHolder.consume(itemstack);
    }

    public UseAnim getUseAnimation(ItemStack p_43105_) {
        return UseAnim.BOW;
    }

    public static float getPowerForTime(int power) {
        float f = (float)power / 20.0F;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 72000;
    }

}
