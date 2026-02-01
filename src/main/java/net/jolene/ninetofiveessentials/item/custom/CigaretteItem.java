package net.jolene.ninetofiveessentials.item.custom;

import net.jolene.ninetofiveessentials.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CigaretteItem extends Item {

	public CigaretteItem(Properties properties) { super(properties); }
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		
		if(!level.isClientSide()) {
			InteractionHand offHand = hand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND;
			ItemStack offStack = player.getItemInHand(offHand);
			
			if(offStack.getItem() == Items.FLINT_AND_STEEL) {
				offStack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(offHand));
				level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, level.getRandom().nextFloat() * 0.4F + 0.8F);
				
				Vec3 lookVec = player.getLookAngle();
				double x = player.getX() + lookVec.x * 0.5;
				double y = player.getY() + 1.6;
				double z = player.getZ() + lookVec.z * 0.5;
				
				((ServerLevel) level).sendParticles(ParticleTypes.LAVA, x, y, z, 5, 0, 0, 0, 0);
				//doesn't hand swing but does increment stat + use automatically replaces held item
				return InteractionResultHolder.consume(ModItems.LIT_CIGARETTE.asItem().getDefaultInstance());
			}
		}
		
		return InteractionResultHolder.pass(stack);
	}
	
}
