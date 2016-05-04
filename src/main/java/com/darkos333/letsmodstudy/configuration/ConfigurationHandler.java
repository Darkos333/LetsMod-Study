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
	private static Configuration configuration;
	private static int configVersion = Config.configVersion;
	protected static int testConfigValue1 = Config.testConfigValue1;
	protected static int testConfigValue2 = Config.testConfigValue2;
	
	public static void init(FMLPreInitializationEvent event)
	{
		File configFile = event.getSuggestedConfigurationFile();
		configuration = new Configuration(configFile);
		try
		{
			configuration.load();
			configVersion = configuration.get("config_versioning", "ConfigVersion", Config.configVersion, "DO NOT MODIFY THIS").getInt();
			if(configVersion != Config.configVersion)
			{
				LogHelper.warn("Newest config version and current confing version differs. Attempting config update.");
				boolean success = OldConfigLoader.load(configuration, configVersion);
				configuration.save();
				configFile.delete();
				configuration = new Configuration(configFile);
				configuration.load();
				if(success)
				{
					reloadConfig();
					configuration.save();
					LogHelper.warn("Config successfully updated.");
					configuration = new Configuration(configFile);
					configuration.load();
					loadConfig();
					
				}
				else
				{
					loadConfig();
					LogHelper.warn("Config update failed. Forcing new default config.");
				}
				
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
			if(configuration.hasChanged())	configuration.save();
		}
	}
	
	private static void loadConfig()
	{
		configuration.setCategoryLanguageKey("test_category","Test Category");
		testConfigValue1 = configuration.get("test_category", "testConfigValue1", Config.testConfigValue1, "This is an example config value").getInt();
		testConfigValue2 = configuration.get("test_category", "testConfigValue2", Config.testConfigValue2, "This is an example config value").getInt();
	}
	
	private static void reloadConfig()
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
				if(event.isWorldRunning)	applyConfigFromGUIRuntime();
				else						applyConfigFromGUI();
			}
		}
	}
	
	public static Configuration getConfiguration()
	{
		return configuration;
	}

}
