package net.favouriteless.coppersniche.common.menus;

import net.favouriteless.coppersniche.CoppersNiche;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class CopperWorkbenchMenu extends AbstractContainerMenu {

	private final Container container;

	public CopperWorkbenchMenu(int id, Inventory inventory) {
		this(id, inventory, new SimpleContainer(4));
	}

	public CopperWorkbenchMenu(int id, Inventory inventory, Container container) {
		super(CoppersNiche.COPPER_WORKBENCH_MENU, id);
		checkContainerSize(container, 4);
		this.container = container;

		// Add container slots
		this.addSlot(new SlotCopperInput(container, 0, 17, 20));
		this.addSlot(new Slot(container, 1, 66, 20));
		this.addSlot(new SlotMaterialInput(container, 2, 86, 20));
		this.addSlot(new SlotOutput(container, 3, 143, 20));

		// Add player inventory slots
		int invStartX = 8;
		int hotbarY = 113;
		for(int i = 0; i < 9; i++) {
			this.addSlot(new Slot(inventory, i, invStartX + i * 18, hotbarY));
		}
		int invStartY = 55;
		for(int i = 0; i < 27; i++) {
			this.addSlot(new Slot(inventory, i + 9, invStartX + (i % 9) * 18, invStartY + (i / 9) * 18));
		}

	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	public static class SlotCopperInput extends Slot {

		public SlotCopperInput(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return stack.getItem() == Items.COPPER_BLOCK;
		}
	}

	public static class SlotMaterialInput extends Slot {

		public SlotMaterialInput(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return stack.getItem() == Items.AMETHYST_SHARD
					|| stack.getItem() == Items.NETHERITE_SCRAP;
		}
	}

	public static class SlotOutput extends Slot {

		public SlotOutput(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}
	}
}
