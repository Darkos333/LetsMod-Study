package com.darkos333.letsmodstudy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

import com.darkos333.letsmodstudy.reference.Reference;
import com.darkos333.letsmodstudy.proxy.*;
import com.darkos333.letsmodstudy.utility.LogHelper;
import com.darkos333.letsmodstudy.configuration.ConfigurationHandler;

@Mod(modid = Reference.ModID, name = Reference.ModName, version = Reference.Version, guiFactory = Reference.GuiFactoryClass)
public class LetsModStudy
{
    @Mod.Instance(Reference.ModID)
    public static LetsModStudy instance;
    
    @SidedProxy(clientSide = Reference.ClientProxyClass , serverSide = Reference.ServerProxyClass)
    public static IProxy proxy;	
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	ConfigurationHandler.init(event);
    	FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
    	LogHelper.info("Configuration succesfully loaded.");
    	ConfigurationHandler.applyConfig();
    	LogHelper.info("Configuration succesfully applied.");
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	//LogHelper.all("Init");
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	//LogHelper.all("PostInit");
    }
}
