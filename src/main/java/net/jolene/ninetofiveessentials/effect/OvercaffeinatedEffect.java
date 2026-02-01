package net.jolene.ninetofiveessentials.effect;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class OvercaffeinatedEffect extends MobEffect {

	protected OvercaffeinatedEffect(MobEffectCategory category, int color) {
		super(category, color);
		
		this.addAttributeModifier(Attributes.MOVEMENT_SPEED, 
				ResourceLocation.fromNamespaceAndPath(NineToFiveEssentials.MODID, "overcaffeinated"), 
				0.25f, 
				AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
		
		this.addAttributeModifier(Attributes.ATTACK_SPEED, 
				ResourceLocation.fromNamespaceAndPath(NineToFiveEssentials.MODID, "overcaffeinated"), 
				0.75f, 
				AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
	}

}
