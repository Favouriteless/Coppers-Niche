package net.favouriteless.coppersniche;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.favouriteless.coppersniche.items.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CoppersNiche implements ModInitializer {

	public static final String MOD_ID = "coppers_niche";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static final PickaxeItem COPPER_PICKAXE = new PickaxeItem(CoppersNicheTiers.COPPER, 1, -2.8F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final AxeItem COPPER_AXE = new AxeItem(CoppersNicheTiers.COPPER, 6.0F, -3.1F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final ShovelItem COPPER_SHOVEL = new ShovelItem(CoppersNicheTiers.COPPER, 1.5F, -3.0F, new Properties().tab(CreativeModeTab.TAB_TOOLS));
	public static final HoeItem COPPER_HOE = new CoppersNicheHoeItem(CoppersNicheTiers.COPPER, -2, -1.0F, new Properties().tab(CreativeModeTab.TAB_TOOLS));


	@Override
	public void onInitialize() {

	}

}
