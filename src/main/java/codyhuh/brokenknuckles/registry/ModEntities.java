package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.entities.DandyDeer;
import codyhuh.brokenknuckles.common.entities.SeaBeak;
import codyhuh.brokenknuckles.common.entities.item.Bullet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<EntityType<Bullet>> BULLET =
            ENTITIES.register("bullet", () -> EntityType.Builder.<Bullet>of(Bullet::new, MobCategory.MISC)
                    .fireImmune()
                    .sized(0.15F, 0.15F)
                    .build("bullet"));

    public static final RegistryObject<EntityType<SeaBeak>> SEA_BEAK =
            ENTITIES.register("sea_beak", () -> EntityType.Builder.of(SeaBeak::new, MobCategory.WATER_CREATURE)
                    .sized(0.65F, 0.4F)
                    .setTrackingRange(16)
                    .updateInterval(1)
                    .build("sea_beak"));

    public static final RegistryObject<EntityType<DandyDeer>> DANDY_DEER =
            ENTITIES.register("dandy_deer", () -> EntityType.Builder.of(DandyDeer::new, MobCategory.CREATURE)
                    .sized(0.55F, 1.5F)
                    .setTrackingRange(16)
                    .updateInterval(1)
                    .build("dandy_deer"));

}
