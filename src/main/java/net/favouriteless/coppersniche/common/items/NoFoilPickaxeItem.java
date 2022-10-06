package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tier;

public class NoFoilPickaxeItem extends PickaxeItem {

	public NoFoilPickaxeItem(Tier tier, int baseAttackDamage, float baseAttackSpeed, Properties properties) {
		super(tier, baseAttackDamage, baseAttackSpeed, properties);
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return false;
	}

	@Override
	public Rarity getRarity(ItemStack stack) {
		return Rarity.COMMON;
	}
}
