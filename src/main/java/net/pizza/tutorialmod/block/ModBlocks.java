package net.pizza.tutorialmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.LightBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.pizza.tutorialmod.TutorialMod;
import net.pizza.tutorialmod.block.custom.StopamLamp;
import net.pizza.tutorialmod.item.ModCreativeModeTab;
import net.pizza.tutorialmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);

    public static final RegistryObject<Block> STOPAM_BLOCK = registerBlock("stopam_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(7f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> STOPAM_ORE = registerBlock("stopam_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4f).requiresCorrectToolForDrops(),
                    UniformInt.of(11, 15)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> DEEPSLATE_STOPAM_ORE = registerBlock("deepslate_stopam_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops(),
                    UniformInt.of(16, 20)), ModCreativeModeTab.TUTORIAL_TAB);

    public static final RegistryObject<Block> STOPAM_LAMP = registerBlock("stopam_lamp",
            () -> new StopamLamp(BlockBehaviour.Properties.of(Material.AMETHYST)
                    .strength(1f)
                //    .requiresCorrectToolForDrops()
                    .lightLevel(state -> state.getValue(StopamLamp.LIT) ? 15 : 0)
                    .sound(SoundType.AMETHYST)), ModCreativeModeTab.TUTORIAL_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
