package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class NoFoilAxeItem extends AxeItem {

	public NoFoilAxeItem(Tier tier, float baseAttackDamage, float baseAttackSpeed, Properties properties) {
		super(tier, baseAttackDamage, baseAttackSpeed, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}
}
