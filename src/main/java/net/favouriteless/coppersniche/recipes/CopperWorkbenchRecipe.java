package net.favouriteless.coppersniche.recipes;

import com.google.gson.JsonObject;
import net.favouriteless.coppersniche.CoppersNiche;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;

import java.io.Serial;

public class CopperWorkbenchRecipe extends SingleItemRecipe {

	public static class Type implements RecipeType<CopperWorkbenchRecipe> {
		private Type() {}
		public static final Type INSTANCE = new Type();

		public static final String ID = "coppers_niche:copper_workbench_recipe";
	}

	public CopperWorkbenchRecipe(ResourceLocation id, String group, Ingredient ingredient, ItemStack result) {
		super(Type.INSTANCE, Serializer.INSTANCE, id, group, ingredient, result);
	}

	@Override
	public boolean matches(Container container, Level level) {
		return ingredient.test(container.getItem(0));
	}

	@Override
	public boolean isSpecial() {
		return true;
	}

	public static class Serializer implements RecipeSerializer<CopperWorkbenchRecipe> {

		public static final Serializer INSTANCE = new Serializer();

		public CopperWorkbenchRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
			String group = GsonHelper.getAsString(json, "group", "");
			Ingredient ingredient;
			if (GsonHelper.isArrayNode(json, "ingredient")) {
				ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "ingredient"));
			} else {
				ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
			}

			String string2 = GsonHelper.getAsString(json, "result");
			int i = GsonHelper.getAsInt(json, "count");
			ItemStack itemStack = new ItemStack(Registry.ITEM.get(new ResourceLocation(string2)), i);
			return new CopperWorkbenchRecipe(recipeId, group, ingredient, itemStack);
		}

		public CopperWorkbenchRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
			String group = buffer.readUtf();
			Ingredient ingredient = Ingredient.fromNetwork(buffer);
			ItemStack itemStack = buffer.readItem();
			return new CopperWorkbenchRecipe(recipeId, group, ingredient, itemStack);
		}

		public void toNetwork(FriendlyByteBuf buffer, CopperWorkbenchRecipe recipe) {
			buffer.writeUtf(recipe.group);
			recipe.ingredient.toNetwork(buffer);
			buffer.writeItem(recipe.result);
		}

	}
}
