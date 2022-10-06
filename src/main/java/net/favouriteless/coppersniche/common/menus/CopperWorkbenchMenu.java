package net.favouriteless.coppersniche.common.menus;

import net.favouriteless.coppersniche.CoppersNiche;
import net.favouriteless.coppersniche.common.blockentities.CopperWorkbenchBlockEntity;
import net.favouriteless.coppersniche.recipes.CopperWorkbenchRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;

public class CopperWorkbenchMenu extends AbstractContainerMenu {

	public final Container container;
	public final ContainerData data;
	public final Container inputSlots = new SimpleContainer(2) {
		@Override
		public void setChanged() {
			super.setChanged();
			CopperWorkbenchMenu.this.slotsChanged(this);
		}
	};
	public final ResultContainer resultSlots = new ResultContainer();
	protected final ContainerLevelAccess access;

	public CopperWorkbenchMenu(int id, Inventory inventory) {
		this(id, inventory, new SimpleContainer(1), new SimpleContainerData(2), ContainerLevelAccess.NULL);
	}

	public CopperWorkbenchMenu(int id, Inventory inventory, Container container, ContainerData data, ContainerLevelAccess containerLevelAccess) {
		super(CoppersNiche.COPPER_WORKBENCH_MENU, id);
		checkContainerSize(container, 1);
		this.access = containerLevelAccess;
		this.container = container;
		this.data = data;


		// Add container slots
		this.addSlot(new SlotCopperInput(container, 0, 17, 20));
		this.addSlot(new CopperWorkbenchSlot(inputSlots, 0, 66, 20));
		this.addSlot(new SlotMaterialInput(inputSlots, 1, 86, 20));
		this.addSlot(new SlotOutput(resultSlots, 0, 143, 20));

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

		this.addDataSlots(data);

	}

	public void createResult() {
		if(data.get(0) == CopperWorkbenchBlockEntity.MAX_COPPER && !inputSlots.getItem(1).isEmpty()) {
			CopperWorkbenchRecipe recipe = getRecipeMatch();

			if(recipe != null) {
				ItemStack resultItem = recipe.getResultItem().copy();

				if(inputSlots.getItem(1).getItem() == Items.AMETHYST_SHARD)
					resultItem.enchant(Enchantments.SILK_TOUCH, 1);
				if(inputSlots.getItem(1).getItem()  == Items.NETHERITE_SCRAP)
					resultItem.enchant(Enchantments.BLOCK_FORTUNE, 2);

				resultSlots.setItem(0, resultItem);
				return;
			}
		}
		resultSlots.setItem(0, ItemStack.EMPTY);
	}

	public void onResultTaken() {
		slots.get(1).getItem().shrink(1);
		slots.get(2).getItem().shrink(1);
		data.set(0, 0);

		access.execute((level, pos) -> {
			level.playSound(null, pos, SoundEvents.ANVIL_USE, SoundSource.NEUTRAL, 1.0F, 1.0F);
		});

		createResult();
	}

	@Override
	public void slotsChanged(Container container) {
		createResult();
		super.slotsChanged(container);
	}

	public CopperWorkbenchRecipe getRecipeMatch() {
		if(container instanceof CopperWorkbenchBlockEntity blockEntity) {
			if(blockEntity.getLevel() != null) {
				return blockEntity.getLevel().getRecipeManager()
						.getRecipes()
						.stream()
						.filter(recipe -> recipe instanceof CopperWorkbenchRecipe)
						.map(recipe -> (CopperWorkbenchRecipe) recipe)
						.filter(recipe -> recipe.matches(inputSlots, blockEntity.getLevel()))
						.findFirst()
						.orElse(null);
			}
		}
		return null;
	}

	@Override
	public void removed(Player player) {
		super.removed(player);
		this.access.execute((level, blockPos) -> this.clearContainer(player, this.inputSlots));
	}

	@Override
	public boolean stillValid(Player player) {
		return this.access.evaluate((level, blockPos) ->
				level.getBlockState(blockPos).is(CoppersNiche.COPPER_WORKBENCH) && player.distanceToSqr(blockPos.getX() + 0.5D, blockPos.getY() + 0.5D, blockPos.getZ() + 0.5D) <= 64.0D, true);
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		ItemStack itemStack = ItemStack.EMPTY;
		Slot slot = slots.get(index);

		if (slot.hasItem()) {
			ItemStack itemStack2 = slot.getItem();
			itemStack = itemStack2.copy();

			if(index < 4) {
				if (!this.moveItemStackTo(itemStack2, 4, 40, false)) {
					return ItemStack.EMPTY;
				}
			}
			else {
				if(getSlot(0).mayPlace(itemStack)) {
					if (!this.moveItemStackTo(itemStack2, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				}
				else if(getSlot(2).mayPlace(itemStack)) {
					if (!this.moveItemStackTo(itemStack2, 2, 3, false)) {
						return ItemStack.EMPTY;
					}
				}
				else {
					if (!this.moveItemStackTo(itemStack2, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				}
			}

			if (itemStack2.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}

			if (itemStack2.getCount() == itemStack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(player, itemStack2);
		}

		return itemStack;
	}

	private class CopperWorkbenchSlot extends Slot {

		public CopperWorkbenchSlot(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			CopperWorkbenchMenu.this.createResult();
		}

	}

	private class SlotCopperInput extends CopperWorkbenchSlot {

		public SlotCopperInput(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return stack.getItem() == Items.OXIDIZED_COPPER;
		}
	}

	private class SlotMaterialInput extends CopperWorkbenchSlot {

		public SlotMaterialInput(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return stack.getItem() == Items.AMETHYST_SHARD
					|| stack.getItem() == Items.NETHERITE_SCRAP;
		}
	}

	private class SlotOutput extends CopperWorkbenchSlot {

		public SlotOutput(Container container, int index, int x, int y) {
			super(container, index, x, y);
		}

		@Override
		public boolean mayPlace(ItemStack stack) {
			return false;
		}

		@Override
		public void onTake(Player player, ItemStack stack) {
			CopperWorkbenchMenu.this.onResultTaken();
		}
	}
}
