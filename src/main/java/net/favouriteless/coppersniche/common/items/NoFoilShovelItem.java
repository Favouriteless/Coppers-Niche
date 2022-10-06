package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.*;

public class NoFoilShovelItem extends ShovelItem {

	public NoFoilShovelItem(Tier tier, float baseAttackDamage, float baseAttackSpeed, Properties properties) {
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
