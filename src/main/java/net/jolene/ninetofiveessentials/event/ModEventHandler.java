package net.jolene.ninetofiveessentials.event;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.jolene.ninetofiveessentials.item.ModItems;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;

@EventBusSubscriber(modid = NineToFiveEssentials.MODID)
public class ModEventHandler {
	
	@SubscribeEvent
	public static void onPlayerInteractionEntity(PlayerInteractEvent.EntityInteract event) {
		//in order to circumvent annoying vanilla logic, we cancel here
		Level level = event.getLevel();
		
		if(event.getTarget() instanceof Wolf wolf) {
			Player player = event.getEntity();
			ItemStack stack = player.getItemInHand(event.getHand());
			
			if(stack.is(ModItems.COFFEE_GUM) && wolf.isTame() && wolf.getHealth() < wolf.getMaxHealth()) {
				//client- and server-sided
				level.playSound(player, wolf.blockPosition(), SoundEvents.GENERIC_EAT, SoundSource.NEUTRAL, 1.0f, 1.0f);
				
				if(!level.isClientSide()) {
					wolf.heal(1.0f);
					
					double offsetX = (level.getRandom().nextDouble() - 0.5) * 0.3;
					double offsetY = level.getRandom().nextDouble() * 0.4 + 0.4;
					double offsetZ = (level.getRandom().nextDouble() - 0.5) * 0.3;
					
					((ServerLevel) level).sendParticles(new ItemParticleOption(ParticleTypes.ITEM, ModItems.COFFEE_GUM.toStack()),
							wolf.getX() + offsetX, 
							wolf.getY() + offsetY + 0.5, 
							wolf.getZ() + offsetZ, 
							1, 0, 0, 0, 0.01);
				}
				//does not decrement if player in creative
				stack.consume(1, player);
				
				//TODO: check if we need an empty stack check + this.setItemInHand(hand, ItemStack.EMPTY); here
				
				event.setCancellationResult(InteractionResult.sidedSuccess(level.isClientSide()));
				event.setCanceled(true);
			}
		}
		
	}
	
}
