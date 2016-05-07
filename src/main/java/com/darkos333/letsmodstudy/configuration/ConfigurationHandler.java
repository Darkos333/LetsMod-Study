package com.darkos333.letsmodstudy.configuration;

import java.io.File;

import com.darkos333.letsmodstudy.reference.Config;
import com.darkos333.letsmodstudy.reference.Reference;
import com.darkos333.letsmodstudy.utility.LogHelper;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.*;

public class ConfigurationHandler
{
	protected static File configFile = null;
	protected static Configuration configuration;
	
	protected static int configVersion = Config.configVersion;
	
	protected static int testConfigValue1 = Config.testConfigValue1;
	protected static int testConfigValue2 = Config.testConfigValue2;
	
	public static void init(FMLPreInitializationEvent event)
	{
		configFile = event.getSuggestedConfigurationFile();
		configuration = new Configuration(configFile);
		try
		{
			configuration.load();
			configVersion = configuration.get("config_versioning", "ConfigVersion", Config.configVersion, "DO NOT MODIFY THIS").getInt();
			if(configVersion != Config.configVersion)
			{
				OldConfigsLoader.load();
			}
			else
			{
				loadConfig();
			}
		}
		catch(Exception e)
		{
			LogHelper.error("An error occured while loading config file:\n" + e.getLocalizedMessage());
		}
		finally
		{
			if(configuration.hasChanged())
				configuration.save();
		}
	}
	
	protected static void loadConfig()
	{
		testConfigValue1 = configuration.get("test_category", "testConfigValue1", Config.testConfigValue1, "This is an example config value").getInt();
		testConfigValue2 = configuration.get("test_category", "testConfigValue2", Config.testConfigValue2, "This is an example config value").getInt();
	}
	
	protected static void saveRecreatedConfig()
	{
		testConfigValue1 = configuration.get("test_category", "testConfigValue1", testConfigValue1, "This is an example config value").getInt();
		testConfigValue2 = configuration.get("test_category", "testConfigValue2", testConfigValue2, "This is an example config value").getInt();
	}
	
	public static void applyConfig() //aply config settings during mod loading
	{
		LogHelper.debug("testConfigVal1 is set to: " + testConfigValue1);
		LogHelper.debug("testConfigVal2 is set to: " + testConfigValue2);
		LogHelper.debug("Does config object contain \"test_category\" ? :" + configuration.hasCategory("test_category"));
	}
	
	public static void applyConfigFromGUI() //apply instanteniously applicible config settings after mod has been loaded
	{
		
	}
	
	public static void applyConfigFromGUIRuntime() ///apply instanteniously applicible config settings when game is running
	{
		
	}
	
	@SubscribeEvent
	public static void onConfigurationChange(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equalsIgnoreCase(Reference.ModID))
		{
			loadConfig(); //synchronize config file with config GUI setup
			if(configuration.hasChanged())
			{
				configuration.save();
				if(event.isWorldRunning)
					applyConfigFromGUIRuntime();
				else
					applyConfigFromGUI();
			}
		}
	}
	
	public static Configuration getConfiguration()
	{
		return configuration;
	}

}
