package net.jolene.ninetofiveessentials.item;

import java.util.function.Supplier;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.potion.ModPotions;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionContents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
	
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, NineToFiveEssentials.MODID);
	
	public static final Supplier<CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register(
			"nine_to_five_essentials",
			() -> CreativeModeTab.builder().title(Component.translatable("itemgroup.ninetofiveessentials.ninetofiveessentials"))
					.icon(() -> ModItems.CIGARETTE.asItem().getDefaultInstance())
					.displayItems((parameters, output) -> {
						output.accept(ModItems.CIGARETTE);
						output.accept(ModItems.CIGARETTE_BUTT);
						output.accept(ModBlocks.FIVE_HUNDRED_CIGARETTES);
						output.accept(ModItems.TAR_GLOB);
						output.accept(ModItems.TAR_BRICK);
						output.accept(ModBlocks.TAR_BRICKS);
						output.accept(ModBlocks.TAR_BRICK_STAIRS);
						output.accept(ModBlocks.TAR_BRICK_SLAB);
						output.accept(ModItems.DARK_IS_THE_NIGHT_MUSIC_DISC);
						output.accept(ModItems.FUNKY_CIGARETTE);
						output.accept(ModItems.HEMP_SEEDS);
						output.accept(ModItems.HEMP_LEAVES);
						output.accept(ModItems.DRIED_HEMP);
						output.accept(ModItems.BROWNIE);
						output.accept(ModItems.FUNKY_BROWNIE);
						output.accept(ModItems.FUNKY_CIGARETTE_MUSIC_DISC);
						output.accept(ModBlocks.BRITNEY);
						output.accept(ModItems.BAGUETTE);
						output.accept(ModItems.BERET);
						output.accept(PotionContents.createItemStack(Items.POTION, ModPotions.COFFEE));
						output.accept(PotionContents.createItemStack(Items.SPLASH_POTION, ModPotions.COFFEE));
						output.accept(PotionContents.createItemStack(Items.LINGERING_POTION, ModPotions.COFFEE));
						output.accept(PotionContents.createItemStack(Items.TIPPED_ARROW, ModPotions.COFFEE));
						output.accept(ModItems.COFFEE_CHERRIES);
						output.accept(ModItems.COFFEE_BEANS);
						output.accept(ModItems.COFFEE_GUM);
						
					}).build());
	
	
	public static void registerCreativeTabs(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Creative Tabs for " + NineToFiveEssentials.MODID);
		CREATIVE_MODE_TABS.register(eventBus);
		eventBus.addListener(ModCreativeTabs::addCreativeTabs);
	}
	
	private static void addCreativeTabs(BuildCreativeModeTabContentsEvent event) {
		if(event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {
			event.accept(ModItems.CIGARETTE);
			event.accept(ModItems.CIGARETTE_BUTT);
			event.accept(ModItems.FUNKY_CIGARETTE);
			event.accept(ModItems.COFFEE_CHERRIES);
			event.accept(ModItems.COFFEE_GUM);
			event.accept(ModItems.BAGUETTE);
			event.accept(ModItems.BROWNIE);
			event.accept(ModItems.FUNKY_BROWNIE);
		}
		
		if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
			event.accept(ModItems.TAR_GLOB);
			event.accept(ModItems.TAR_BRICK);
			event.accept(ModItems.COFFEE_BEANS);
			event.accept(ModItems.HEMP_LEAVES);
			event.accept(ModItems.DRIED_HEMP);
		}
		
		if(event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
			event.accept(ModItems.HEMP_SEEDS);
		}
		
		if(event.getTabKey() == CreativeModeTabs.COMBAT) {
			event.accept(ModItems.BERET);
		}
		
		if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			event.accept(ModItems.DARK_IS_THE_NIGHT_MUSIC_DISC);
			event.accept(ModItems.FUNKY_CIGARETTE_MUSIC_DISC);
			event.accept(ModItems.COIN);
			event.accept(ModItems.WHITE_POKER_CHIP);
			event.accept(ModItems.RED_POKER_CHIP);
			event.accept(ModItems.ORANGE_POKER_CHIP);
			event.accept(ModItems.YELLOW_POKER_CHIP);
			event.accept(ModItems.GREEN_POKER_CHIP);
			event.accept(ModItems.BLACK_POKER_CHIP);
			event.accept(ModItems.PURPLE_POKER_CHIP);
			event.accept(ModItems.MAROON_POKER_CHIP);
		}
	}
}
