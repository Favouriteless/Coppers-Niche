package net.favouriteless.coppersniche;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.favouriteless.coppersniche.client.screens.CopperWorkbenchScreen;
import net.minecraft.client.gui.screens.MenuScreens;

@Environment(EnvType.CLIENT)
public class CoppersNicheClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MenuScreens.register(CoppersNiche.COPPER_WORKBENCH_MENU, CopperWorkbenchScreen::new);
	}
}
