package net.favouriteless.coppersniche.common.blockentities;

import net.favouriteless.coppersniche.CoppersNiche;
import net.favouriteless.coppersniche.common.menus.CopperWorkbenchMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.StackedContentsCompatible;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Iterator;

public class CopperWorkbenchBlockEntity extends BaseContainerBlockEntity implements StackedContentsCompatible {

	public static final int SLOT_INPUT_COPPER = 0;
	public static final int SLOT_INPUT_TOOL = 1;
	public static final int SLOT_INPUT_MATERIAL = 2;
	public static final int SLOT_RESULT = 3;

	private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);


	public CopperWorkbenchBlockEntity(BlockPos pos, BlockState state) {
		this(CoppersNiche.COPPER_WORKBENCH_BLOCK_ENTITY, pos, state);
	}

	public CopperWorkbenchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		items.set(1, new ItemStack(Items.DIAMOND, 3));
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new CopperWorkbenchMenu(id, inventory, this);
	}

	@Override
	protected Component getDefaultName() {
		return Component.translatable("coppers_niche.container.copper_workbench");
	}

	@Override
	public int getContainerSize() {
		return items.size();
	}

	@Override
	public boolean isEmpty() {
		Iterator iterator = this.items.iterator();

		ItemStack itemStack;
		do {
			if (!iterator.hasNext()) {
				return true;
			}

			itemStack = (ItemStack)iterator.next();
		} while(itemStack.isEmpty());

		return false;
	}

	@Override
	public ItemStack getItem(int slot) {
		return items.get(slot);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		return ContainerHelper.removeItem(this.items, slot, amount);
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		return ContainerHelper.takeItem(this.items, slot);
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		items.set(slot, stack);
		if (stack.getCount() > getMaxStackSize()) {
			stack.setCount(getMaxStackSize());
		}
	}

	@Override
	public boolean stillValid(Player player) {
		if (level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return player.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	@Override
	public void clearContent() {
		this.items.clear();
	}

	@Override
	public void fillStackedContents(StackedContents helper) {
		Iterator var2 = this.items.iterator();

		while(var2.hasNext()) {
			ItemStack itemStack = (ItemStack)var2.next();
			helper.accountStack(itemStack);
		}

	}
}
