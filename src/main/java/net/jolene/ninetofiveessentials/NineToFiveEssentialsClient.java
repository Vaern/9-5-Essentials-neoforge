package net.jolene.ninetofiveessentials;

import net.jolene.ninetofiveessentials.model.UnboundElementsLoader;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.ModelEvent;

@Mod(value = NineToFiveEssentials.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = NineToFiveEssentials.MODID, value = Dist.CLIENT)
public class NineToFiveEssentialsClient {
	public NineToFiveEssentialsClient(ModContainer container) { }

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		//Create stack size override for coin-likes; we simply convert from int to float
		event.enqueueWork(() -> {
			ItemProperties.registerGeneric(ResourceLocation.fromNamespaceAndPath(NineToFiveEssentials.MODID, "count"), 
				(stack, level, player, seed) -> stack.getCount());
		});
	}
	
	/*@SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
		IClientItemExtensions coinlikes = new IClientItemExtensions() {
			public boolean shouldSpreadAsEntity(ItemStack stack) { // doesn't prevent multiple from rendering, oh well
				return false;
			}
		};
		
		event.registerItem(coinlikes, 
				ModItems.COIN,
				ModItems.WHITE_POKER_CHIP,
				ModItems.RED_POKER_CHIP,
				ModItems.ORANGE_POKER_CHIP,
				ModItems.YELLOW_POKER_CHIP,
				ModItems.GREEN_POKER_CHIP,
				ModItems.BLACK_POKER_CHIP,
				ModItems.PURPLE_POKER_CHIP,
				ModItems.MAROON_POKER_CHIP);
	}*/
	
	@SubscribeEvent
	public static void registerCustomLoader(ModelEvent.RegisterGeometryLoaders event) {
		event.register(UnboundElementsLoader.ID, UnboundElementsLoader.LOADER);
	}
}
