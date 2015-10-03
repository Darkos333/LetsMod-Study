package com.darkos333.letsmodstudy;

import com.darkos333.letsmodstudy.reference.Reference;
import com.darkos333.letsmodstudy.proxy.*;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;

@Mod(modid = Reference.ModID, name = Reference.ModName, version = Reference.Version)
public class LetsModStudy
{
    @Mod.Instance(Reference.ModID)
    public static LetsModStudy instance;
    
    @SidedProxy(clientSide = Reference.ClientProxyClass , serverSide = Reference.ServerProxyClass)
    public static IProxy proxy;
    
    @EventHandler
    public void preinit(FMLPreInitializationEvent event)
    {
    	
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		
    }
    
    @EventHandler
    public void postinit(FMLPostInitializationEvent event)
    {
    	
    }
}
