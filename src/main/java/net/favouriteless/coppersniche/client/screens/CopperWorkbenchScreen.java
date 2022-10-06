package net.favouriteless.coppersniche.client.screens;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.favouriteless.coppersniche.CoppersNiche;
import net.favouriteless.coppersniche.common.menus.CopperWorkbenchMenu;
import net.minecraft.ResourceLocationException;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ItemCombinerMenu;
import org.intellij.lang.annotations.Identifier;

public class CopperWorkbenchScreen extends AbstractContainerScreen<CopperWorkbenchMenu> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(CoppersNiche.MOD_ID, "textures/gui/copper_workbench.png");

	public CopperWorkbenchScreen(CopperWorkbenchMenu container, Inventory inventory, Component component) {
		super(container, inventory, component);
		this.imageWidth = 176;
		this.imageHeight = 137;
	}

	@Override
	protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, TEXTURE);
		int screenX = (this.width - this.imageWidth) / 2;
		int screenY = (this.height - this.imageHeight) / 2;
		this.blit(poseStack, screenX, screenY, 0, 0, this.imageWidth, this.imageHeight);

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
