package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.*;

public class NoFoilHoeItem extends HoeItem {

	public NoFoilHoeItem(Tier tier, int baseAttackDamage, float baseAttackSpeed, Properties properties) {
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
