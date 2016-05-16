package com.darkos333.letsmodstudy.potions;

import com.darkos333.letsmodstudy.utility.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.brewing.PotionBrewEvent;


public class BrewingStandHandler
{
	
	private static ItemStack ironIngot = new ItemStack(Item.getItemById(265).setPotionEffect("x"));
	private static ItemStack goldIngot = new ItemStack(Item.getItemById(266));
	
	
	@SubscribeEvent
	public void onBrewAttempt(PotionBrewEvent.Pre event)
	{
		
		if(event.getItem(3) != null && event.getItem(3).isItemEqual(ironIngot))
		{
			LogHelper.info("Transmuting iron into gold!");
			event.setItem(3, goldIngot);
			event.setCanceled(true);
		}
		
		System.out.println("Pre Brewing Event.");
		System.out.println("Ingredient: " + (event.getItem(3) == null ? "Empty" : event.getItem(3).getUnlocalizedName()));
		System.out.println("Slot1: " + (event.getItem(0) == null ? "Empty" : event.getItem(0).getUnlocalizedName()));
		System.out.println("Slot2: " + (event.getItem(1) == null ? "Empty" : event.getItem(1).getUnlocalizedName()));
		System.out.println("Slot3: " + (event.getItem(2) == null ? "Empty" : event.getItem(2).getUnlocalizedName()));
		
	}
	
	@SubscribeEvent
	public void onBrewingDone(PotionBrewEvent.Post event)
	{
		System.out.println("Post Brewing Event.");
		System.out.println("Ingredient: " + (event.getItem(3) == null ? "Empty" : event.getItem(3).getUnlocalizedName()));
		System.out.println("Slot1: " + (event.getItem(0) == null ? "Empty" : event.getItem(0).getUnlocalizedName()));
		System.out.println("Slot2: " + (event.getItem(1) == null ? "Empty" : event.getItem(1).getUnlocalizedName()));
		System.out.println("Slot3: " + (event.getItem(2) == null ? "Empty" : event.getItem(2).getUnlocalizedName()));
	}
}





