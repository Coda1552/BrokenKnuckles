package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.blocks.DeepSlateChiseledBookshelfBlock;
import codyhuh.brokenknuckles.common.blocks.MagicBarrierBlock;
import codyhuh.brokenknuckles.common.blocks.SimpleDirectionalBlock;
import codyhuh.brokenknuckles.common.blocks.MagicBarrierHubBlock;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;


public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, BrokenKnuckles.MOD_ID);

    public static final RegistryObject<Block> DWARFSTONE = registerBlock("dwarfstone", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DWARFSTONE_STAIRS = registerBlock("dwarfstone_stairs", () -> new StairBlock(DWARFSTONE.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DWARFSTONE_SLAB = registerBlock("dwarfstone_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DWARFSTONE_WALL = registerBlock("dwarfstone_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));
    public static final RegistryObject<Block> DWARFSTONE_BRICKS = registerBlock("dwarfstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE_BRICKS)));
    public static final RegistryObject<Block> DWARFSTONE_BRICK_STAIRS = registerBlock("dwarfstone_brick_stairs", () -> new StairBlock(DWARFSTONE_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICK_STAIRS)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DWARFSTONE_BRICK_SLAB = registerBlock("dwarfstone_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICK_SLAB)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DWARFSTONE_BRICK_WALL = registerBlock("dwarfstone_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICK_WALL)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE)));

    public static final RegistryObject<Block> DWARVEN_STEEL_BLOCK = registerBlock("dwarven_steel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
            .strength(50F, 2000F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> MAGIC_BARRIER_BLOCK = registerBlock("magic_barrier", () -> new MagicBarrierBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
            .strength(-1.0F, 3600000F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.GLASS)));

    public static final RegistryObject<Block> MAGIC_BARRIER_HUB_BLOCK = registerBlock("magic_barrier_hub", () -> new MagicBarrierHubBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
            .strength(2.5F, 3600000F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.GLASS)));

    public static final RegistryObject<Block> DEEPSLATE_CHISELED_BOOKSHELF = registerBlock("deepslate_chiseled_bookshelf", () -> new DeepSlateChiseledBookshelfBlock(BlockBehaviour.Properties.copy(Blocks.CHISELED_BOOKSHELF)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .sound(SoundType.CHISELED_BOOKSHELF)));

    public static final RegistryObject<Block> ARCHIVE_SIDING = registerBlock("archive_siding", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> ARCHIVE_TILE_ONE = registerBlock("archive_tile_one", () -> new SimpleDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> ARCHIVE_TILE_TWO = registerBlock("archive_tile_two", () -> new SimpleDirectionalBlock(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_TILES)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE_TILES)));

    public static final RegistryObject<Block> FUZZILION = registerBlock("fuzzilion", () -> new FlowerBlock(() -> MobEffects.REGENERATION, 120, BlockBehaviour.Properties.copy(Blocks.DANDELION)
            .sound(SoundType.FLOWERING_AZALEA)));

    public static final RegistryObject<Block> FUZZILION_STEM = registerBlock("humongous_fuzzilion_stem", () -> new RotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_LOG)));
    public static final RegistryObject<Block> FUZZILION_CAP = registerBlock("humongous_fuzzilion_cap", () -> new Block(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS)));
    public static final RegistryObject<Block> FUZZILION_FUZZ = registerBlock("humongous_fuzzilion_fuzz", () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.WHITE_WOOL)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if(name.equals("dwarven_steel_block") || name .equals("magic_barrier")) {
            registerBlockItemWithFireRes(name, toReturn);
        } else {
            registerBlockItem(name, toReturn);
        }
        return toReturn;
    }

   private static <T extends Block>RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
    private static <T extends Block>RegistryObject<Item> registerBlockItemWithFireRes(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().fireResistant()));
    }
}
