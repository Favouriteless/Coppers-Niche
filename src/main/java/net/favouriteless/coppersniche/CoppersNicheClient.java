package net.favouriteless.coppersniche;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.favouriteless.coppersniche.client.screens.CopperWorkbenchScreen;
import net.favouriteless.coppersniche.common.blockentities.CopperWorkbenchBlockEntity;
import net.favouriteless.coppersniche.common.blocks.CopperWorkbenchBlock;
import net.favouriteless.coppersniche.common.items.CoppersNicheHoeItem;
import net.favouriteless.coppersniche.common.items.CoppersNicheTiers;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraft.world.item.Item.Properties;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Environment(EnvType.CLIENT)
public class CoppersNicheClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MenuScreens.register(CoppersNiche.COPPER_WORKBENCH_MENU, CopperWorkbenchScreen::new);
	}
}
