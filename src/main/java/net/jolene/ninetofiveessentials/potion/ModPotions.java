package net.jolene.ninetofiveessentials.potion;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModPotions {
	
	public static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(BuiltInRegistries.POTION, NineToFiveEssentials.MODID);
	
	public static final Holder<Potion> COFFEE = POTIONS.register("coffee", () -> new Potion(new MobEffectInstance(ModEffects.OVERCAFFEINATED, 600, 0)));
	
	public static void registerModPotions(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Potions for " + NineToFiveEssentials.MODID);
		POTIONS.register(eventBus);
		NeoForge.EVENT_BUS.addListener(ModPotions::registerBrewingRecipes);
	}
	
	private static void registerBrewingRecipes(RegisterBrewingRecipesEvent event) {
		event.getBuilder().addMix(Potions.WATER, ModItems.COFFEE_BEANS.asItem(), ModPotions.COFFEE);
	}
}
