package net.favouriteless.coppersniche;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.favouriteless.coppersniche.common.blockentities.CopperWorkbenchBlockEntity;
import net.favouriteless.coppersniche.common.blocks.CopperWorkbenchBlock;
import net.favouriteless.coppersniche.common.items.*;
import net.favouriteless.coppersniche.common.menus.CopperWorkbenchMenu;
import net.favouriteless.coppersniche.recipes.CopperWorkbenchRecipe;
import net.favouriteless.coppersniche.recipes.CopperWorkbenchRecipe.Type;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SingleItemRecipe;
import net.minecraft.world.item.crafting.SingleItemRecipe.Serializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoppersNiche implements ModInitializer {

	public static final String MOD_ID = "coppers_niche";

	public static final PickaxeItem COPPER_PICKAXE = new PickaxeItem(CoppersNicheTiers.COPPER, 1, -2.8F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final AxeItem COPPER_AXE = new AxeItem(CoppersNicheTiers.COPPER, 6.0F, -3.1F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final ShovelItem COPPER_SHOVEL = new ShovelItem(CoppersNicheTiers.COPPER, 1.5F, -3.0F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final HoeItem COPPER_HOE = new CoppersNicheHoeItem(CoppersNicheTiers.COPPER, -2, -1.0F, new Properties().tab(CreativeModeTab.TAB_TOOLS));

	public static final CopperWorkbenchBlock COPPER_WORKBENCH = new CopperWorkbenchBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(3.5F));
	public static final BlockItem COPPER_WORKBENCH_ITEM = new BlockItem(COPPER_WORKBENCH, new Properties().tab(CreativeModeTab.TAB_DECORATIONS));

	public static final BlockEntityType<CopperWorkbenchBlockEntity> COPPER_WORKBENCH_BLOCK_ENTITY = FabricBlockEntityTypeBuilder.create(CopperWorkbenchBlockEntity::new, COPPER_WORKBENCH).build();
	public static final MenuType<CopperWorkbenchMenu> COPPER_WORKBENCH_MENU = new MenuType<>(CopperWorkbenchMenu::new);

	public static final RecipeType<CopperWorkbenchRecipe> RECIPE_TYPE = Type.INSTANCE;
	public static final CopperWorkbenchRecipe.Serializer RECIPE_SERIALIZER = CopperWorkbenchRecipe.Serializer.INSTANCE;

	public void onInitialize() {
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_pickaxe"), COPPER_PICKAXE);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_axe"), COPPER_AXE);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_shovel"), COPPER_SHOVEL);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_hoe"), COPPER_HOE);

		Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "copper_workbench"), COPPER_WORKBENCH);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_workbench"), COPPER_WORKBENCH_ITEM);
		Registry.register(Registry.BLOCK_ENTITY_TYPE, new ResourceLocation(MOD_ID, "copper_workbench"), COPPER_WORKBENCH_BLOCK_ENTITY);
		Registry.register(Registry.MENU, new ResourceLocation(MOD_ID, "copper_workbench"), COPPER_WORKBENCH_MENU);

		Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(MOD_ID, "copper_workbench"), RECIPE_TYPE);
		Registry.register(Registry.RECIPE_SERIALIZER, new ResourceLocation(MOD_ID, "copper_workbench"), RECIPE_SERIALIZER);
	}

}
