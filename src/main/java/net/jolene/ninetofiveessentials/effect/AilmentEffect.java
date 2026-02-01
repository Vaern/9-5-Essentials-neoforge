package net.jolene.ninetofiveessentials.effect;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class AilmentEffect extends MobEffect {
	public AilmentEffect(MobEffectCategory category, int color) {
		super(category, color, ParticleTypes.ASH);
		
		this.addAttributeModifier(Attributes.MAX_HEALTH, 
				ResourceLocation.fromNamespaceAndPath(NineToFiveEssentials.MODID, "ailment"), 
				-2f, 
				AttributeModifier.Operation.ADD_VALUE);
	}
}
