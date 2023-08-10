package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import codyhuh.brokenknuckles.common.blocks.MagicBarrierBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
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
    public static final RegistryObject<Block> DWARFSTONE_BRICKS = registerBlock("dwarfstone_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEEPSLATE_BRICKS)
            .requiresCorrectToolForDrops()
            .sound(SoundType.DEEPSLATE_BRICKS)));
    public static final RegistryObject<Block> DWARVEN_STEEL_BLOCK = registerBlock("dwarven_steel_block", () -> new Block(BlockBehaviour.Properties.copy(Blocks.NETHERITE_BLOCK)
            .strength(50F, 2000F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.NETHERITE_BLOCK)));

    public static final RegistryObject<Block> MAGIC_BARRIER_BLOCK = registerBlock("magic_barrier", () -> new MagicBarrierBlock(BlockBehaviour.Properties.copy(Blocks.GLASS)
            .strength(-1.0F, 3600000F)
            .requiresCorrectToolForDrops()
            .sound(SoundType.GLASS)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        if(name.equals( "dwarven_steel_block") || name .equals( "magic_barrier")){
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
