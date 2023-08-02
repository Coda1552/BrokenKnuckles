package codyhuh.brokenknuckles.registry;

import codyhuh.brokenknuckles.BrokenKnuckles;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static final TagKey<Block> NEEDS_DWARVEN_TOOL = blockTag("needs_dwarven_tool");

    private static TagKey<Block> blockTag(String name){
        return BlockTags.create(new ResourceLocation(BrokenKnuckles.MOD_ID, name));
    }

    private static TagKey<Item> itemTag(String name){
        return ItemTags.create(new ResourceLocation(BrokenKnuckles.MOD_ID, name));
    }

}
