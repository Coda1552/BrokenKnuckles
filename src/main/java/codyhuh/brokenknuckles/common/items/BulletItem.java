package codyhuh.brokenknuckles.common.items;

import codyhuh.brokenknuckles.common.entities.item.Bullet;
import codyhuh.brokenknuckles.registry.ModEntities;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BulletItem extends Item {

    public BulletItem(Properties pProperties) {
        super(pProperties);
    }

    public Bullet createBullet(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new Bullet(ModEntities.BULLET.get(), pLevel, pShooter);
    }
}
