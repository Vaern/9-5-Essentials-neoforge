package net.jolene.ninetofiveessentials.item;

import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

public class ModFoodComponents {
	public static final FoodProperties BAGUETTE = new FoodProperties.Builder().nutrition(18).saturationModifier(0.6f).build();
	public static final FoodProperties COFFEE_CHERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(0.1f).build();
	public static final FoodProperties COFFEE_GUM = new FoodProperties.Builder().nutrition(0).saturationModifier(0f)
			.effect(() -> new MobEffectInstance(ModEffects.OVERCAFFEINATED, 1200, 2), 1.0f).build(); //Consumable comp. doesn't exist in 1.21.1	
	public static final FoodProperties BROWNIE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f).build();
	public static final FoodProperties FUNKY_BROWNIE = new FoodProperties.Builder().nutrition(5).saturationModifier(0.6f)
			.effect(() -> new MobEffectInstance(ModEffects.SERENITY, 1200, 0), 1.0f).build();
}
