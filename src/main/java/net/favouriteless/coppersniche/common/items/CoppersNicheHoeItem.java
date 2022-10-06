package net.favouriteless.coppersniche.common.items;

import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Tier;

public class CoppersNicheHoeItem extends HoeItem {

	// For some god damn reason HoeItem's constructor is protected
	public CoppersNicheHoeItem(Tier tier, int i, float f, Properties properties) {
		super(tier, i, f, properties);
	}
}
