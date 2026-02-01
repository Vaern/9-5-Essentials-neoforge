package net.jolene.ninetofiveessentials.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Enemy;

public class SerenityEffect extends MobEffect {

	protected SerenityEffect(MobEffectCategory category, int color) {
		super(category, color);
	}
	
	@Override
	public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
		return true;
	}
	
	@Override
	public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		if(entity.level().isClientSide()) return true;
		
		if(entity instanceof Enemy && entity instanceof Mob mob) {
			mob.setTarget(null); //TODO: kinda ineffective at preventing attacks
			mob.setAggressive(false);
		}
		
		return true;
	}
}
