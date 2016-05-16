package com.darkos333.letsmodstudy.coremod;

import net.minecraft.item.ItemStack;

public class Tmp
{
	ItemStack[] brewingItemStacks = new ItemStack[4];
	public boolean foo()
	{
		if(com.darkos333.letsmodstudy.coremod.DummyBrewingRecipesMaker.matchesAnyRecipe(brewingItemStacks))
			return true;
		return false;
	}

}
