package net.jolene.ninetofiveessentials.item;

import java.util.Arrays;
import java.util.List;

import com.google.common.collect.Lists;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.level.ItemLike;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

public class ModItemTooltips {
	
	public static void modifyItemComponents(ModifyDefaultComponentsEvent event) {
		addTooltip(event, ModItems.CIGARETTE, "cigarette");
		addTooltip(event, ModItems.LIT_CIGARETTE, "lit_cigarette");
		addTooltip(event, ModItems.FUNKY_CIGARETTE, "funky_cigarette");
		addTooltip(event, ModItems.LIT_FUNKY_CIGARETTE, "lit_funky_cigarette");
		addTooltip(event, ModItems.COFFEE_GUM, "coffee_gum");
		addTooltip(event, ModItems.FUNKY_BROWNIE, "funky_brownie");
		addTooltip(event, ModItems.CIGARETTE, "cigarette");
		addTooltip(event, ModBlocks.FIVE_HUNDRED_CIGARETTES, "five_hundred_cigarettes");
		addTooltip(event, ModItems.BERET, "beret");
		addTooltips(event, ModItems.COIN, "coin", "gambling");
		addTooltip(event, ModItems.DICE, "gambling");
		addTooltips(event, ModItems.WHITE_POKER_CHIP, "white_poker_chip", "gambling");
		addTooltips(event, ModItems.RED_POKER_CHIP, "red_poker_chip", "gambling");
		addTooltips(event, ModItems.ORANGE_POKER_CHIP, "orange_poker_chip", "gambling");
		addTooltips(event, ModItems.YELLOW_POKER_CHIP, "yellow_poker_chip", "gambling");
		addTooltips(event, ModItems.GREEN_POKER_CHIP, "green_poker_chip", "gambling");
		addTooltips(event, ModItems.BLACK_POKER_CHIP, "black_poker_chip", "gambling");
		addTooltips(event, ModItems.PURPLE_POKER_CHIP, "purple_poker_chip", "gambling");
		addTooltips(event, ModItems.MAROON_POKER_CHIP, "maroon_poker_chip", "gambling");
	}
	
	/** Adds a single tooltip, adhering to the format {@code tooltip.modid.name} */
	private static void addTooltip(ModifyDefaultComponentsEvent event, ItemLike item, String name) {
		addCustomTooltips(event, item, "tooltip." + NineToFiveEssentials.MODID + "." + name);
	}
	
	private static void addTooltips(ModifyDefaultComponentsEvent event, ItemLike item, String...strings) {
		List<Component> comps = Lists.transform(Arrays.asList(strings), name -> Component.translatable("tooltip." + NineToFiveEssentials.MODID + "." + name));
		event.modify(item, builder -> builder.set(DataComponents.LORE, new ItemLore(comps)));
	}
	
	/** Adds a tooltip of up to 255 lines. Must provide entire key per line */
	private static void addCustomTooltips(ModifyDefaultComponentsEvent event, ItemLike item, String...strings) {
		List<Component> comps = Lists.transform(Arrays.asList(strings), Component::translatable);
		event.modify(item, builder -> builder.set(DataComponents.LORE, new ItemLore(comps)));
	}
	
	public static void registerModItemTooltips(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Tooltips for " + NineToFiveEssentials.MODID);
		eventBus.addListener(ModItemTooltips::modifyItemComponents);
	}
}
