package com.darkos333.letsmodstudy.coremod;

import com.darkos333.letsmodstudy.utility.LogHelper;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;

public class DummyBrewingRecipesMaker
{
	private static ItemStack ironIngot = new ItemStack(Item.getItemById(265));
	
	public static boolean matchesAnyRecipe(ItemStack[] brewingItemStacks)
	{
		//LogHelper.warn("Custom canBrew handler called!");
		if(brewingItemStacks[3] != null && brewingItemStacks[3].isItemEqual(ironIngot) &&  brewingItemStacks[3].stackSize == 1) return true;
		return false;
	}

}
