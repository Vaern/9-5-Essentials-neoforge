package net.jolene.ninetofiveessentials;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.jolene.ninetofiveessentials.block.ModBlocks;
import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.item.ModCreativeTabs;
import net.jolene.ninetofiveessentials.item.ModItemTooltips;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.jolene.ninetofiveessentials.potion.ModPotions;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

@Mod(NineToFiveEssentials.MODID)
public class NineToFiveEssentials {
	public static final String MODID = "ninetofiveessentials";
	public static final Logger LOGGER = LogUtils.getLogger();

	public NineToFiveEssentials(IEventBus modEventBus, ModContainer modContainer) {
		
		//Since we are only registering deferred registers/event listeners, order shouldn't matters
		ModBlocks.registerModBlocks(modEventBus);
		ModItems.registerModItems(modEventBus);
		ModItemTooltips.registerModItemTooltips(modEventBus);
		ModEffects.registerMobEffects(modEventBus);
		ModPotions.registerModPotions(modEventBus);
		ModSounds.registerSoundEvents(modEventBus);
		ModCreativeTabs.registerCreativeTabs(modEventBus);
	}
}
