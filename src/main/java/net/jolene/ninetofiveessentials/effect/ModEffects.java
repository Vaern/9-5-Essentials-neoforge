package net.jolene.ninetofiveessentials.effect;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModEffects {
	
	public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(BuiltInRegistries.MOB_EFFECT, NineToFiveEssentials.MODID);
	
	public static final DeferredHolder<MobEffect, MobEffect> AILMENT = MOB_EFFECTS.register("ailment", () -> new AilmentEffect(MobEffectCategory.HARMFUL, 0x36EBAB));
	public static final DeferredHolder<MobEffect, MobEffect> SERENITY = MOB_EFFECTS.register("serenity", () -> new SerenityEffect(MobEffectCategory.BENEFICIAL, 0x5B8EEB));
	public static final DeferredHolder<MobEffect, MobEffect> OVERCAFFEINATED = MOB_EFFECTS.register("overcaffeinated", () -> new OvercaffeinatedEffect(MobEffectCategory.BENEFICIAL, 0x783F04));
	
	public static void registerMobEffects(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Effects for" + NineToFiveEssentials.MODID);
		MOB_EFFECTS.register(eventBus);
	}
}
