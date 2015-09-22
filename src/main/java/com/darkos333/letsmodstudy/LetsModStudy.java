package com.darkos333.letsmodstudy;

import net.minecraft.init.Blocks;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.*;

@Mod(modid = LetsModStudy.MODID, name = LetsModStudy.NAME, version = LetsModStudy.VERSION)
public class LetsModStudy
{
    public static final String MODID = "letsmodstudy";
    public static final String VERSION = "1.7.10-1.0";
    public static final String NAME = "Lets Mod Study";
    
    @Mod.Instance(LetsModStudy.MODID)
    public static LetsModStudy instance;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	
    }
}