package net.jolene.ninetofiveessentials.block;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.custom.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

public class ModBlocks {
	//DeferredRegister for blocks
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NineToFiveEssentials.MODID);
	
	/* BLOCK REGISTRATION
	 * excludes BlockItems */
	public static final DeferredBlock<Block> BRITNEY = BLOCKS.registerSimpleBlock("britney", BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL));
	
	public static final DeferredBlock<Block> FIVE_HUNDRED_CIGARETTES = BLOCKS.registerSimpleBlock("five_hundred_cigarettes", BlockBehaviour.Properties.ofFullCopy(Blocks.BLACK_WOOL));
	
	public static final DeferredBlock<Block> TAR_BRICKS = BLOCKS.registerSimpleBlock("tar_bricks", 
			BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops());
	public static final DeferredBlock<SlabBlock> TAR_BRICK_SLAB = BLOCKS.registerBlock("tar_brick_slab", SlabBlock::new,
			BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops());
	public static final DeferredBlock<StairBlock> TAR_BRICK_STAIRS = BLOCKS.registerBlock("tar_brick_stairs", 
			properties -> new StairBlock(TAR_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of().strength(2f).requiresCorrectToolForDrops()));
	
	public static final DeferredBlock<HempPlantBlock> HEMP_PLANT = BLOCKS.registerBlock("hemp", HempPlantBlock::new, 
			BlockBehaviour.Properties.of().noCollission().instabreak().randomTicks().pushReaction(PushReaction.DESTROY));
	public static final DeferredBlock<CoffeeBushBlock> COFFEE_BUSH = BLOCKS.registerBlock("coffee", CoffeeBushBlock::new, 
			BlockBehaviour.Properties.of().noCollission().instabreak().randomTicks().pushReaction(PushReaction.DESTROY));
	
	public static final DeferredBlock<DiceBlock> DICE = BLOCKS.registerBlock("dice", DiceBlock::new,
			BlockBehaviour.Properties.of().instabreak().sound(SoundType.BONE_BLOCK).noOcclusion().isViewBlocking((state, level, pos) -> false));
	
	//TODO slot machine
	
	//Registers the Block DeferredRegister and block type event to the bus
	public static void registerModBlocks(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Blocks for " + NineToFiveEssentials.MODID);
		BLOCKS.register(eventBus);
		eventBus.addListener(ModBlocks::registerBlockTypes);
		
	}
	
	//Registers subclass block types directly (no ancillary helper)
	private static void registerBlockTypes(RegisterEvent event) {
		event.register(BuiltInRegistries.BLOCK_TYPE.key(), registry -> {
			//inherits name from blocks
			registry.register(HEMP_PLANT.getId(), HEMP_PLANT.get().codec());
			registry.register(COFFEE_BUSH.getId(), COFFEE_BUSH.get().codec());
			registry.register(DICE.getId(), DICE.get().codec());
		});
	}
}
