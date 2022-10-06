package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;

public class NoFoilShovelItem extends ShovelItem {

	public NoFoilShovelItem(Tier tier, float baseAttackDamage, float baseAttackSpeed, Properties properties) {
		super(tier, baseAttackDamage, baseAttackSpeed, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}
}
