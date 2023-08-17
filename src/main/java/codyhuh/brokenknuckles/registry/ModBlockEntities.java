package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.blocks.DeepSlateChiseledBookshelfBlock;
import codyhuh.brokenknuckles.common.blocks.entity.AltarDisplayBlockEntity;
import codyhuh.brokenknuckles.common.blocks.entity.DeepSlateChiseledBookshelfBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.ChiseledBookShelfBlockEntity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<BlockEntityType<ChiseledBookShelfBlockEntity>> GEM_EMPOWERING_STATION_BE =
            BLOCK_ENTITIES.register("deepslate_chiseled_bookshelf_block_entity", () ->
                    BlockEntityType.Builder.of(ChiseledBookShelfBlockEntity::new,
                            ModBlocks.DEEPSLATE_CHISELED_BOOKSHELF.get()).build(null));

    public static final RegistryObject<BlockEntityType<AltarDisplayBlockEntity>> ALTAR_DISPLAY_BE =
            BLOCK_ENTITIES.register("altar_display_block_entity", () ->
                    BlockEntityType.Builder.of(AltarDisplayBlockEntity::new,
                            ModBlocks.ALTAR_DISPLAY.get()).build(null));
}
