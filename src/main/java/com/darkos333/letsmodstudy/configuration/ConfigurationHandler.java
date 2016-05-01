package com.darkos333.letsmodstudy.configuration;

import java.io.File;

import com.darkos333.letsmodstudy.utility.LogHelper;

import cpw.mods.fml.common.event.*;
import net.minecraftforge.common.config.*;

public class ConfigurationHandler
{
	private static int testConfigVal = 0;
	
	public static void loadConfig(FMLPreInitializationEvent event)
	{
		File configFile = event.getSuggestedConfigurationFile();
		Configuration configuration = new Configuration(configFile);
		try
		{
			configuration.load();
			
			testConfigVal = configuration.get("Test category", "testConfigVal", 1, "This is an example config value").getInt();
			
			
		}
		catch(Exception e)
		{
			LogHelper.error("An error occured while loading config file:\n" + e.getLocalizedMessage());
		}
		finally
		{
			configuration.save();
		}
	}
	
	public static void applyConfig()
	{
		LogHelper.debug("testConfigVal is set to: " + testConfigVal);
	}

}
