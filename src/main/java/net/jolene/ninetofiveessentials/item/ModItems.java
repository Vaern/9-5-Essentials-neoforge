package net.jolene.ninetofiveessentials.item;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.item.custom.BeretItem;
import net.jolene.ninetofiveessentials.item.custom.CigaretteItem;
import net.jolene.ninetofiveessentials.item.custom.FunkyCigaretteItem;
import net.jolene.ninetofiveessentials.item.custom.LitCigaretteItem;
import net.jolene.ninetofiveessentials.item.custom.LitFunkyCigaretteItem;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
	//DeferredRegister for items
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NineToFiveEssentials.MODID);
	
	/* ITEM REGISTRATION */
	public static final DeferredItem<Item> CIGARETTE = ITEMS.registerItem("cigarette", CigaretteItem::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> LIT_CIGARETTE = ITEMS.registerItem("lit_cigarette", LitCigaretteItem::new, new Item.Properties().stacksTo(1).durability(6));
	
	public static final DeferredItem<Item> FUNKY_CIGARETTE = ITEMS.registerItem("funky_cigarette", FunkyCigaretteItem::new, new Item.Properties().stacksTo(1));
	public static final DeferredItem<Item> LIT_FUNKY_CIGARETTE = ITEMS.registerItem("lit_funky_cigarette", LitFunkyCigaretteItem::new, new Item.Properties().stacksTo(1).durability(6));
	
	public static final DeferredItem<Item> CIGARETTE_BUTT =  ITEMS.registerSimpleItem("cigarette_butt", new Item.Properties().stacksTo(16));
	public static final DeferredItem<Item> TAR_GLOB = ITEMS.registerSimpleItem("tar_glob");
	public static final DeferredItem<Item> TAR_BRICK = ITEMS.registerSimpleItem("tar_brick");
	public static final DeferredItem<Item> BAGUETTE = ITEMS.registerSimpleItem("baguette", new Item.Properties().food(ModFoodComponents.BAGUETTE));
	
	public static final DeferredItem<BlockItem> HEMP_SEEDS = ITEMS.registerItem("hemp_seeds", properties -> new ItemNameBlockItem(ModBlocks.HEMP_PLANT.get(), properties));
	public static final DeferredItem<Item> HEMP_LEAVES = ITEMS.registerSimpleItem("hemp_leaves");
	public static final DeferredItem<Item> DRIED_HEMP = ITEMS.registerSimpleItem("dried_hemp");
	
	public static final DeferredItem<Item> BROWNIE = ITEMS.registerSimpleItem("brownie", new Item.Properties().food(ModFoodComponents.BROWNIE));
	public static final DeferredItem<Item> FUNKY_BROWNIE = ITEMS.registerSimpleItem("funky_brownie", new Item.Properties().food(ModFoodComponents.FUNKY_BROWNIE));
	
	public static final DeferredItem<BlockItem> COFFEE_CHERRIES = ITEMS.registerItem("coffee_cherries", properties -> new ItemNameBlockItem(ModBlocks.COFFEE_BUSH.get(), properties), 
			new Item.Properties().food(ModFoodComponents.COFFEE_CHERRIES));
	public static final DeferredItem<Item> COFFEE_BEANS = ITEMS.registerSimpleItem("coffee_beans");
	public static final DeferredItem<Item> COFFEE_GUM = ITEMS.registerSimpleItem("coffee_gum", new Item.Properties().food(ModFoodComponents.COFFEE_GUM));
	
	public static final DeferredItem<Item> DARK_IS_THE_NIGHT_MUSIC_DISC = ITEMS.registerSimpleItem("dark_is_the_night_music_disc", 
			new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.DARK_IS_THE_NIGHT_KEY));
	public static final DeferredItem<Item> FUNKY_CIGARETTE_MUSIC_DISC = ITEMS.registerSimpleItem("funky_cigarette_music_disc", 
			new Item.Properties().stacksTo(1).jukeboxPlayable(ModSounds.FUNKY_CIGARETTE_KEY));
	public static final DeferredItem<Item> BERET = ITEMS.registerItem("beret", BeretItem::new, new Item.Properties().stacksTo(1));
	
	public static final DeferredItem<Item> COIN = ITEMS.registerSimpleItem("coin");
	
	public static final DeferredItem<Item> WHITE_POKER_CHIP = ITEMS.registerSimpleItem("white_poker_chip");
	public static final DeferredItem<Item> RED_POKER_CHIP = ITEMS.registerSimpleItem("red_poker_chip");
	public static final DeferredItem<Item> ORANGE_POKER_CHIP = ITEMS.registerSimpleItem("orange_poker_chip");
	public static final DeferredItem<Item> YELLOW_POKER_CHIP = ITEMS.registerSimpleItem("yellow_poker_chip");
	public static final DeferredItem<Item> GREEN_POKER_CHIP = ITEMS.registerSimpleItem("green_poker_chip");
	public static final DeferredItem<Item> BLACK_POKER_CHIP = ITEMS.registerSimpleItem("black_poker_chip");
	public static final DeferredItem<Item> PURPLE_POKER_CHIP = ITEMS.registerSimpleItem("purple_poker_chip");
	public static final DeferredItem<Item> MAROON_POKER_CHIP = ITEMS.registerSimpleItem("maroon_poker_chip");
	
	/* SIMPLE BLOCKITEM REGISTRATION */
	public static final DeferredItem<BlockItem> BRITNEY = registerBlock(ModBlocks.BRITNEY);
	public static final DeferredItem<BlockItem> FIVE_HUNDRED_CIGARETTES = registerBlock(ModBlocks.FIVE_HUNDRED_CIGARETTES);
	
	public static final DeferredItem<BlockItem> TAR_BRICKS = registerBlock(ModBlocks.TAR_BRICKS);
	public static final DeferredItem<BlockItem> TAR_BRICK_SLAB = registerBlock(ModBlocks.TAR_BRICK_SLAB);
	public static final DeferredItem<BlockItem> TAR_BRICK_STAIRS = registerBlock(ModBlocks.TAR_BRICK_STAIRS);
	
	public static final DeferredItem<BlockItem> DICE = registerBlock(ModBlocks.DICE);
	
	private static DeferredItem<BlockItem> registerBlock(Holder<Block> block) {
		return ITEMS.registerSimpleBlockItem(block);
	}
	
	public static void registerModItems(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Items for " + NineToFiveEssentials.MODID);
		ITEMS.register(eventBus);
		
	}
}
