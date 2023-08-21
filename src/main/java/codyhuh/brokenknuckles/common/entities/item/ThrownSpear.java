package codyhuh.brokenknuckles.common.entities.item;

import codyhuh.brokenknuckles.common.items.GrungSpearItem;
import codyhuh.brokenknuckles.common.items.GrungSpearNoBoomItem;
import codyhuh.brokenknuckles.registry.ModEntities;
import codyhuh.brokenknuckles.registry.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

public class ThrownSpear extends AbstractArrow {

    private static final EntityDataAccessor<Boolean> ID_FOIL = SynchedEntityData.defineId(ThrownSpear.class, EntityDataSerializers.BOOLEAN);
    private ItemStack tridentItem;
    private boolean dealtDamage;
    public int clientSideReturnTridentTickCount;
    private boolean exploded;

    public ThrownSpear(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public ThrownSpear(Level pLevel, LivingEntity pShooter, ItemStack pStack, Boolean pExploded, ItemStack tridentItem) {
        super(ModEntities.SPEAR.get(), pShooter, pLevel);
        if(tridentItem != null){
            this.tridentItem = tridentItem;
        } else {
            this.tridentItem = new ItemStack(ModItems.GRUNG_SPEAR.get(), 1);
        }
        this.entityData.set(ID_FOIL, pStack.hasFoil());
        exploded = pExploded;
    }

    public Item getTridentItem(){
        if(tridentItem != null){
            return this.tridentItem.getItem();
        }
        return null;
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(ID_FOIL, false);
    }
    public void tick() {
        if (this.inGroundTime > 4) {
            this.dealtDamage = true;
            if(this.getTridentItem() != null){
                if(this.getTridentItem() instanceof GrungSpearItem){
                    this.discard();
                }
            }
        }
        if (this.inGroundTime > 0){
            if(!exploded){
                if(!dealtDamage){
                    if(this.getTridentItem() != null){
                        if(this.getTridentItem() instanceof GrungSpearItem){
                            this.level().explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 1.5F, Level.ExplosionInteraction.NONE);
                        }
                    }
                }
            }
            exploded = true;
        }
        super.tick();
    }
    private boolean isAcceptibleReturnOwner() {
        Entity entity = this.getOwner();
        if (entity != null && entity.isAlive()) {
            return !(entity instanceof ServerPlayer) || !entity.isSpectator();
        } else {
            return false;
        }
    }
    public boolean isFoil() {
        return this.entityData.get(ID_FOIL);
    }
    @Nullable
    protected EntityHitResult findHitEntity(Vec3 pStartVec, Vec3 pEndVec) {
        return this.dealtDamage ? null : super.findHitEntity(pStartVec, pEndVec);
    }
    protected void onHitEntity(EntityHitResult pResult) {
        Entity entity = pResult.getEntity();
        float f = 3.0F;
        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.damageSources().trident(this, (Entity)(entity1 == null ? this : entity1));
        this.dealtDamage = true;
        SoundEvent soundevent = SoundEvents.TRIDENT_HIT;
        if (entity.hurt(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (entity instanceof LivingEntity) {
                LivingEntity livingentity1 = (LivingEntity)entity;
                if (entity1 instanceof LivingEntity) {
                    EnchantmentHelper.doPostHurtEffects(livingentity1, entity1);
                    EnchantmentHelper.doPostDamageEffects((LivingEntity)entity1, livingentity1);
                }

                this.doPostHurtEffects(livingentity1);
            }
        }

        this.setDeltaMovement(this.getDeltaMovement().multiply(-0.01D, -0.1D, -0.01D));
        float f1 = 1.0F;

        this.playSound(soundevent, f1, 1.0F);

    }


    protected boolean tryPickup(Player pPlayer) {
        return super.tryPickup(pPlayer) || this.isNoPhysics() && this.ownedBy(pPlayer) && pPlayer.getInventory().add(this.getPickupItem());
    }
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return SoundEvents.TRIDENT_HIT_GROUND;
    }

    /**
     * Called by a player entity when they collide with an entity
     */
    public void playerTouch(Player pEntity) {
        if (this.ownedBy(pEntity) || this.getOwner() == null) {
            super.playerTouch(pEntity);
        }

    }
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        if (pCompound.contains("Grung_Spear", 10)) {
            this.tridentItem = ItemStack.of(pCompound.getCompound("Grung_Spear"));
        }

        this.dealtDamage = pCompound.getBoolean("DealtDamage");
        this.exploded = true;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.put("Grung_Spear", this.tridentItem.save(new CompoundTag()));
        pCompound.putBoolean("DealtDamage", this.dealtDamage);
        pCompound.putBoolean("Exploded", this.exploded);
    }

    public void tickDespawn() {

    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    public boolean shouldRender(double pX, double pY, double pZ) {
        return true;
    }
    @Override
    protected ItemStack getPickupItem() {
        if(this.getTridentItem()!= null){
            if(this.getTridentItem() instanceof GrungSpearItem){
                return null;
            }
        }
        return this.tridentItem.copy();
    }
}
