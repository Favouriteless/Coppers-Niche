package net.favouriteless.coppersniche.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.favouriteless.coppersniche.CoppersNiche;
import net.favouriteless.coppersniche.common.blockentities.CopperWorkbenchBlockEntity;
import net.favouriteless.coppersniche.common.menus.CopperWorkbenchMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CopperWorkbenchScreen extends AbstractContainerScreen<CopperWorkbenchMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(CoppersNiche.MOD_ID, "textures/gui/copper_workbench.png");

	private static final int COPPER_BAR_X = 75;
	private static final int COPPER_BAR_Y = 11;
	private static final int COPPER_BAR_WIDTH = 18;
	private static final int COPPER_BAR_HEIGHT = 4;
	private static final int COPPER_BAR_U = 176;
	private static final int COPPER_BAR_V = 35;

	private static final int INPUT_BAR_X = 38;
	private static final int INPUT_BAR_Y = 20;
	private static final int INPUT_BAR_WIDTH = 23;
	private static final int INPUT_BAR_HEIGHT = 14;
	private static final int INPUT_BAR_U = 176;
	private static final int INPUT_BAR_V = 21;

	private static final int MATERIAL_ICON_X = 85;
	private static final int MATERIAL_ICON_Y = 19;
	private static final int MATERIAL_ICON_WIDTH = 18;
	private static final int MATERIAL_ICON_U = 204;
	private static final int MATERIAL_ICON_V = 0;
	private long startGameTime = 0;
	private boolean toggleIcon = false;
	private long prevToggleTime = 0;

	private static final int FINAL_BAR_X = 109;
	private static final int FINAL_BAR_Y = 17;
	private static final int FINAL_BAR_WIDTH = 28;
	private static final int FINAL_BAR_HEIGHT = 21;
	private static final int FINAL_BAR_U = 176;
	private static final int FINAL_BAR_V = 0;


	public CopperWorkbenchScreen(CopperWorkbenchMenu container, Inventory inventory, Component component) {
		super(container, inventory, component);
		this.imageWidth = 176;
		this.imageHeight = 137;
		startGameTime = Minecraft.getInstance().level.getGameTime();
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int screenX = (this.width - this.imageWidth) / 2;
		int screenY = (this.height - this.imageHeight) / 2;
		this.blit(poseStack, screenX, screenY, 0, 0, this.imageWidth, this.imageHeight);

		if(menu.data.get(0) > 0) {
			int barCopperScaledWidth = COPPER_BAR_WIDTH * menu.data.get(0) / CopperWorkbenchBlockEntity.MAX_COPPER;
			this.blit(poseStack, screenX + COPPER_BAR_X, screenY + COPPER_BAR_Y, COPPER_BAR_U, COPPER_BAR_V, barCopperScaledWidth, COPPER_BAR_HEIGHT);
		}
		if(menu.data.get(1) > 0) {
			int barProgressScaled = INPUT_BAR_WIDTH * menu.data.get(1) / CopperWorkbenchBlockEntity.INPUT_TIME_TOTAL;
			this.blit(poseStack, screenX + INPUT_BAR_X, screenY + INPUT_BAR_Y, INPUT_BAR_U, INPUT_BAR_V, barProgressScaled, INPUT_BAR_HEIGHT);
		}

		if(menu.inputSlots.getItem(1).isEmpty()) {
			long timeSinceOpened = Minecraft.getInstance().level.getGameTime() - startGameTime;
			if(timeSinceOpened % 60 == 0 && timeSinceOpened != prevToggleTime) {
				toggleIcon = !toggleIcon;
				prevToggleTime = timeSinceOpened;
			}

			if(toggleIcon)
				this.blit(poseStack, screenX + MATERIAL_ICON_X, screenY + MATERIAL_ICON_Y, MATERIAL_ICON_U, MATERIAL_ICON_V, MATERIAL_ICON_WIDTH, MATERIAL_ICON_WIDTH);
		}

		if(menu.resultSlots.getItem(0).isEmpty()) {
			this.blit(poseStack, screenX + FINAL_BAR_X, screenY + FINAL_BAR_Y, FINAL_BAR_U, FINAL_BAR_V, FINAL_BAR_WIDTH, FINAL_BAR_HEIGHT);
		}

		this.renderTooltip(poseStack, mouseX, mouseY);
	}

	@Override
	public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTick) {
		this.renderBackground(poseStack);
		super.render(poseStack, mouseX, mouseY, partialTick);
		RenderSystem.disableBlend();
		this.renderTooltip(poseStack, mouseX, mouseY);
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
	}

}
