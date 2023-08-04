package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.entities.item.Bullet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<EntityType<Bullet>> BULLET = ENTITIES.register("bullet", () -> EntityType.Builder.<Bullet>of(Bullet::new, MobCategory.MISC).fireImmune().sized(0.15F, 0.15F).build("bullet"));
}
