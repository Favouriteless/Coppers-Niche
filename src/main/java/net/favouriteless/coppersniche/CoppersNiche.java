package net.favouriteless.coppersniche;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.favouriteless.coppersniche.blocks.CopperWorkbenchBlock;
import net.favouriteless.coppersniche.items.*;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoppersNiche implements ModInitializer {

	public static final String MOD_ID = "coppers_niche";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final PickaxeItem COPPER_PICKAXE = new PickaxeItem(CoppersNicheTiers.COPPER, 1, -2.8F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final AxeItem COPPER_AXE = new AxeItem(CoppersNicheTiers.COPPER, 6.0F, -3.1F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final ShovelItem COPPER_SHOVEL = new ShovelItem(CoppersNicheTiers.COPPER, 1.5F, -3.0F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final HoeItem COPPER_HOE = new CoppersNicheHoeItem(CoppersNicheTiers.COPPER, -2, -1.0F, new Properties().tab(CreativeModeTab.TAB_TOOLS));

	public static final CopperWorkbenchBlock COPPER_WORKBENCH = new CopperWorkbenchBlock(BlockBehaviour.Properties.copy(Blocks.FURNACE));

	@Override
	public void onInitialize() {
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_pickaxe"), COPPER_PICKAXE);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_axe"), COPPER_AXE);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_shovel"), COPPER_SHOVEL);
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "copper_hoe"), COPPER_HOE);
		Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "copper_workbench"), COPPER_WORKBENCH);
	}

}
