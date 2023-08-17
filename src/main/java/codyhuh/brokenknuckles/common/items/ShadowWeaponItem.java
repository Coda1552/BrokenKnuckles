package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.client.geo.MagicWeaponRenderer;
import codyhuh.brokenknuckles.util.BKAnimations;
import com.mojang.datafixers.TypeRewriteRule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.common.Tags;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.util.RenderUtils;
import java.lang.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
//need to implement animation switching
public class ShadowWeaponItem extends Item implements GeoItem, GeoAnimatable{
    private int useNum = 0;
    private int tick = 0;
    boolean check = false;

    boolean q = false;

    boolean checkTwo = false;

    boolean effectOneOn = false;
    boolean isEffectTwoOn = false;
    public int minX = 0;

    public int minY = 0;

    public int minZ = 0;

    public int maxX = 10;

    public int maxY = 10;

    public int maxZ = 10;


    public AABB axisalignedbb = new AABB(minX, minY, minZ, maxX, maxY, maxZ);





    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public ShadowWeaponItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
       return new ItemStack(this);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
    }
    private  <T extends GeoAnimatable> PlayState predicate(AnimationState<T> animationState){
        if(checkForStop(animationState) || animationState.getController().getCurrentAnimation() == null){
            if(useNum == 0){
                if(!q){
                    if(checkForStop(animationState)){
                        playIdle(animationState);
                    }
                }
            } else if(useNum == 1){
                if(!check){
                    animationState.getController().stop();
                    q = true;
                    check = true;
                    checkTwo = false;
                } else {
                    animationState.getController().stop();
                    animationState.resetCurrentAnimation();
                    animationState.getController().forceAnimationReset();
                    animationState.getController().setAnimation(RawAnimation.begin().then("attack", Animation.LoopType.PLAY_ONCE));
                    effectOneOn = true;
                    q = false;
                    checkTwo = true;
                }
                if(checkTwo){

                    useNum = 0;

                }
            } else if (useNum == 2){
                if(!check){
                    animationState.getController().stop();
                    q = true;
                    check = true;
                    checkTwo = false;
                } else {
                    animationState.getController().stop();
                    animationState.resetCurrentAnimation();
                    animationState.getController().forceAnimationReset();
                    animationState.getController().setAnimation(RawAnimation.begin().then("special", Animation.LoopType.PLAY_ONCE));
                    isEffectTwoOn = true;
                    q = false;
                    checkTwo = true;
                }
                if(checkTwo){

                    useNum = 0;

                }
            }
        }
        //sendMessage(animationState);
        return PlayState.CONTINUE;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public void sendMessage(AnimationState animationState){
        assert Minecraft.getInstance().player != null;
        Minecraft.getInstance().player.sendSystemMessage(Component.literal(animationState.getController().getAnimationState().toString()));
    }
    public boolean checkForStop(AnimationState animationState){
        return animationState.getController().getAnimationState().equals(AnimationController.State.STOPPED);
    }

    public void playIdle(AnimationState animationState){
        animationState.getController().setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.PLAY_ONCE));
        if(checkForStop(animationState)){
            animationState.getController().setAnimation(RawAnimation.begin().then("empty", Animation.LoopType.PLAY_ONCE));
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pLevel.isClientSide()){
            if(pPlayer.isCrouching() || pPlayer.isShiftKeyDown()){
                if(!pPlayer.getCooldowns().isOnCooldown(this)){
                    useNum = 2;
                    pPlayer.getCooldowns().addCooldown(this, 500);
                }
            } else {
                if(!pPlayer.getCooldowns().isOnCooldown(this)){
                    useNum = 1;
                    pPlayer.getCooldowns().addCooldown(this, 300);
                }
            }
            check = false;
        }
        return InteractionResultHolder.pass(pPlayer.getItemInHand(pUsedHand));
    }

    private String toString2(int useNum) {
        return "" + useNum;
    }
    private String toString3(boolean useNum) {
        return "" + useNum;
    }
    @Override
    public void inventoryTick(ItemStack p_41404_, Level pLevel, Entity p_41406_, int p_41407_, boolean p_41408_) {
        if (p_41406_ instanceof Player player) {
                if(effectOneOn){
                    int q1= (int)(Math.random()*4);
                    int q2= (int)(Math.random()*9);
                    if(q1 == 0){
                        addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 2, false, false, false), player);
                    }
                    if(q2 == 0){
                        addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 400, 2, false, false, false), player);
                        addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 3, false, false, false), player);
                    }
                    addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, (int) (Math.random()*3), false, false, false), player);
                    effectOneOn = false;
                } else if(isEffectTwoOn){
                    isEffectTwoOn = false;
                    AABB aabb = (new AABB(player.blockPosition().below())).inflate(11.0D);
                    //List<LivingEntity> Entities3 = pLevel.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT.range(5).ignoreInvisibilityTesting().ignoreLineOfSight(), player, aabb);
                    List<LivingEntity>  nearbyEntities = pLevel.getEntitiesOfClass(LivingEntity.class, aabb);
                    //player.sendSystemMessage(Component.literal("These Entities Are within Range!" + nearbyEntities + ""));
                    for(LivingEntity entity : nearbyEntities){
                        if (entity.isAlive() && !entity.isRemoved()) {
                            if(entity instanceof Player player2){
                                if(!((player.getInventory().getArmor(0).getItem() instanceof CultArmorItem && player.getInventory().getArmor(1).getItem() instanceof CultArmorItem && player.getInventory().getArmor(2).getItem() instanceof CultArmorItem) || (player.getInventory().getArmor(3).getItem() instanceof CultArmorItem))){
                                    if(!player2.equals(player)){
                                        entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, (int) (Math.random()*2)));
                                        entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 2));
                                        entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 50, 0));
                                    }
                                }
                            } else {
                                entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 300, (int) (Math.random()*2)));
                                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 150, 2));
                                entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 50, 0));
                            }
                        }
                    }

                }

        }

    }



    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private MagicWeaponRenderer renderer;
            @Override
            public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                if(this.renderer == null){
                    renderer = new MagicWeaponRenderer();
                }
                return this.renderer;
            }
        });
    }
    public void addEffect(MobEffectInstance mobEffectInstance, Player player){
        player.addEffect(mobEffectInstance);
    }
}
