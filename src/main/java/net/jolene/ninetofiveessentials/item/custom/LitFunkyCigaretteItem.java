package net.jolene.ninetofiveessentials.item.custom;

import net.jolene.ninetofiveessentials.effect.ModEffects;
import net.jolene.ninetofiveessentials.sound.ModSounds;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class LitFunkyCigaretteItem extends Item {
	
	private static final int MAX_AMPLIFIER = 4;
	private static final int DURATION = 12000;
	private static final float RETURN_PAPER_CHANCE = 0.25f;
	
	public LitFunkyCigaretteItem(Properties properties) { super(properties); }
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		
		if(!level.isClientSide()) {
			int amplifier = 0;
			MobEffectInstance effect = player.getEffect(ModEffects.SERENITY);
			if(effect != null) {
				amplifier = effect.getAmplifier() + 1;
				if(amplifier > MAX_AMPLIFIER) amplifier = MAX_AMPLIFIER;
			}
			
			player.addEffect(new MobEffectInstance(ModEffects.SERENITY, DURATION, amplifier, false, false, true));
			player.getCooldowns().addCooldown(this, 60);
			level.playSound(null, player.getX(), player.getY(), player.getZ(), 
					ModSounds.PUFF, SoundSource.MASTER, 0.5f, 1.9f / (level.getRandom().nextFloat() * 1.8f + 2f));
			
			Vec3 lookVec = player.getLookAngle();
			double x = player.getX() + lookVec.x * 0.5;
			double y = player.getY() + 1.6;
			double z = player.getZ() + lookVec.z * 0.5;
			
			((ServerLevel) level).sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 3, 0.1, 0.1, 0.1, 0.01);
			stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
			
			//Circumvents the break in hurtAndBreak
			if(stack.getDamageValue() >= stack.getMaxDamage() - 1) {
				level.playSound(null, player.getX(), player.getY(), player.getZ(), 
						ModSounds.FIZZLE, SoundSource.MASTER, 0.5f, 1.9f / (level.getRandom().nextFloat() * 1.8f + 2f));
				
				((ServerLevel) level).sendParticles(ParticleTypes.LAVA, x, y, z, 5, 0, 0, 0, 0);
				
				return InteractionResultHolder.success(level.getRandom().nextFloat() < RETURN_PAPER_CHANCE ? Items.PAPER.getDefaultInstance() : ItemStack.EMPTY);
			}
		}
		
		return InteractionResultHolder.success(stack);
	}
	
	@Override //TODO CHECK THIS
	public int getBarWidth(ItemStack stack) {
        return Math.round(13.0F - (float)stack.getDamageValue() * 13.0F / (float)(this.getMaxDamage(stack) - 1));
    }
}
