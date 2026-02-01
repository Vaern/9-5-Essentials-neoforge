package net.jolene.ninetofiveessentials.sound;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
	
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, NineToFiveEssentials.MODID);
	
	public static final Holder<SoundEvent> PUFF = registerSoundEvent("puff");
	public static final Holder<SoundEvent> FIZZLE = registerSoundEvent("fizzle");
	public static final Holder<SoundEvent> RESULT = registerSoundEvent("result");
	public static final Holder<SoundEvent> WHEEL = registerSoundEvent("wheel");
	public static final Holder<SoundEvent> COUGH = registerSoundEvent("cough");
	
	public static final Holder<SoundEvent> DARK_IS_THE_NIGHT = registerSoundEvent("dark_is_the_night");
	public static final ResourceKey<JukeboxSong> DARK_IS_THE_NIGHT_KEY = registerJukeboxSong("dark_is_the_night");
	public static final Holder<SoundEvent> FUNKY_CIGARETTE = registerSoundEvent("funky_cigarette");
	public static final ResourceKey<JukeboxSong> FUNKY_CIGARETTE_KEY = registerJukeboxSong("funky_cigarette");
	
	private static Holder<SoundEvent> registerSoundEvent(String id) {
		return SOUND_EVENTS.register(id, name -> SoundEvent.createVariableRangeEvent(name));
	}
	
	private static ResourceKey<JukeboxSong> registerJukeboxSong(String id) {
		return ResourceKey.create(Registries.JUKEBOX_SONG, ResourceLocation.fromNamespaceAndPath(NineToFiveEssentials.MODID, id));
	}
	
	public static void registerSoundEvents(IEventBus eventBus) {
		NineToFiveEssentials.LOGGER.info("Registering Sounds for " + NineToFiveEssentials.MODID);
		SOUND_EVENTS.register(eventBus);
	}
}
