package net.jolene.ninetofiveessentials.model;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import net.jolene.ninetofiveessentials.NineToFiveEssentials;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.model.ElementsModel;
import net.neoforged.neoforge.client.model.geometry.IGeometryLoader;

/** 
 * Custom geometry loader for {@link ElementsModel}, without arbitrary transformation restrictions for {@link BlockElement}.<p>
 * The {@code elements} key must be rewritten to {@code elements_unbound}; this is due to Neoforge's
 * {@link net.neoforged.neoforge.client.model.ExtendedBlockModelDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext) deserialize method} 
 * calling its super method before checking for {@linkplain IGeometryLoader}s, which throws for {@code BlockElements} with out-of-bounds transformations.
 * */
public class UnboundElementsLoader implements IGeometryLoader<ElementsModel> {
	
	public static final UnboundElementsLoader LOADER = new UnboundElementsLoader();
	
	public static final ResourceLocation ID = ResourceLocation.fromNamespaceAndPath(NineToFiveEssentials.MODID, "unbound_elements_loader");
	public static final UnboundDeserializer INSTANCE = new UnboundDeserializer();
	
	private UnboundElementsLoader() {}
	
	@Override
	public ElementsModel read(JsonObject jsonObject, JsonDeserializationContext deserializationContext) throws JsonParseException {
		if (!jsonObject.has("elements_unbound"))
			throw new JsonParseException("An element model must have an \"elements_unbound\" member.");
		
		List<BlockElement> elements = new ArrayList<>();
		for(JsonElement element : GsonHelper.getAsJsonArray(jsonObject, "elements_unbound")) {
			elements.add(INSTANCE.deserialize(element, ElementsModel.class, deserializationContext));
		}
		//note: rescale will not work properly outside of 22.5 intervals, since ElementsModel uses the FaceBakery
		return new ElementsModel(elements);
	}
	
	@OnlyIn(Dist.CLIENT)
	public static class UnboundDeserializer extends BlockElement.Deserializer {
		@Override
		protected float getAngle(JsonObject json) {
			return GsonHelper.getAsFloat(json, "angle");
		}
		//TODO: potentially unbound box bounds
	}
}
