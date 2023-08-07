package codyhuh.brokenknuckles.events;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.entities.DandyDeer;
import codyhuh.brokenknuckles.common.entities.SeaBeak;
import codyhuh.brokenknuckles.registry.ModEntities;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BrokenKnuckles.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BKModEvents {

    @SubscribeEvent
    public static void registerAttributes(final EntityAttributeCreationEvent e) {
        e.put(ModEntities.SEA_BEAK.get(), SeaBeak.createAttributes().build());
        e.put(ModEntities.DANDY_DEER.get(), DandyDeer.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerSpawnPlacements(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.SEA_BEAK.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SeaBeak::canSpawn, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.DANDY_DEER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }
}
