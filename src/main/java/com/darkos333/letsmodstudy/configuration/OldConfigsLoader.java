package com.darkos333.letsmodstudy.configuration;

import com.darkos333.letsmodstudy.utility.LogHelper;

//Class designed to load and preserve some useful values from old versions of configuration files, eg:
//when config vars has been renamed or its type has changed, but values are still valid in new config

import net.minecraftforge.common.config.Configuration;

public class OldConfigsLoader extends ConfigurationHandler
{
	public static void load()
	{
		boolean success = true;
		LogHelper.warn("Newest config version and current confing version differs. Attempting config update.");
		
		switch(configVersion)
		{
		case 0:
				loadconfigv0(configuration);
				break;
		default:
			success = false;
			
		}
		
		configuration.save();
		configFile.delete();
		configuration = new Configuration(configFile);
		configuration.load();
		
		if(success)
		{
			saveRecreatedConfig();
			configuration.save();
			LogHelper.warn("Config successfully updated.");
			configuration = new Configuration(configFile);
			configuration.load();
			loadConfig();
			
		}
		else
		{
			LogHelper.warn("Config update failed. Forcing new default config.");
			loadConfig();
			
		}
	}
	
	private static void loadconfigv0(Configuration configuration)
	{
		//load values for configration file v0
	}
}
