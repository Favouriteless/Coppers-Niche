package net.favouriteless.coppersniche.common.blockentities;

import net.favouriteless.coppersniche.CoppersNiche;
import net.favouriteless.coppersniche.common.menus.CopperWorkbenchMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.lwjgl.system.windows.INPUT;

import java.util.Iterator;

public class CopperWorkbenchBlockEntity extends BaseContainerBlockEntity implements StackedContentsCompatible {

	public static final int MAX_COPPER = 4;
	public static final int INPUT_TIME_TOTAL = 40;
	private int currentCopper = 0;
	private int inputTime = 0;

	private final NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
	public final ContainerData containerData;


	public CopperWorkbenchBlockEntity(BlockPos pos, BlockState state) {
		this(CoppersNiche.COPPER_WORKBENCH_BLOCK_ENTITY, pos, state);
	}

	public CopperWorkbenchBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		this.containerData = new ContainerData() {

			@Override
			public int get(int index) {
				return switch(index) {
					case 0 -> currentCopper;
					case 1 -> inputTime;
					default -> 0;
				};
			}

			@Override
			public void set(int index, int value) {
				switch(index) {
					case 0:
						currentCopper = value;
					case 1:
						inputTime = value;
				}
			}

			@Override
			public int getCount() {
				return 2;
			}
		};
	}


	public static <T extends BlockEntity> void tick(Level level, BlockPos blockPos, BlockState blockState, T t) {
		if(level.isClientSide)
			return;

		CopperWorkbenchBlockEntity blockEntity = (CopperWorkbenchBlockEntity)t;

		if(blockEntity.currentCopper < MAX_COPPER) {
			if(!blockEntity.getItem(0).isEmpty() && blockEntity.getItem(0).getItem() == Items.OXIDIZED_COPPER) {
				if(blockEntity.inputTime == INPUT_TIME_TOTAL) {
					blockEntity.inputTime = 0;
					blockEntity.currentCopper++;
					blockEntity.getItem(0).shrink(1);
				}
				else
					blockEntity.inputTime++;
			}
			else {
				blockEntity.inputTime = 0;
			}
		}
	}

	@Override
	protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
		return new CopperWorkbenchMenu(id, inventory, this, this.containerData);
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

	@Override
	protected void saveAdditional(CompoundTag nbt) {
		super.saveAdditional(nbt);
		nbt.putInt("copper", currentCopper);
		ContainerHelper.saveAllItems(nbt, this.items);
	}

	@Override
	public void load(CompoundTag nbt) {
		super.load(nbt);
		this.currentCopper = nbt.getInt("copper");
		ContainerHelper.loadAllItems(nbt, items);
	}
}
