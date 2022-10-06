package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;

public class NoFoilHoeItem extends HoeItem {

	public NoFoilHoeItem(Tier tier, int baseAttackDamage, float baseAttackSpeed, Properties properties) {
		super(tier, baseAttackDamage, baseAttackSpeed, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}
}
