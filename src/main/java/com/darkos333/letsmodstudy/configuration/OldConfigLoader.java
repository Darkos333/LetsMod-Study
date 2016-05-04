package com.darkos333.letsmodstudy.configuration;

//Class designed to load and preserve some useful values from old versions of configuration files, eg:
//when config vars has been renamed or its type has changed, but values are still valid in new config

import net.minecraftforge.common.config.Configuration;

public class OldConfigLoader extends ConfigurationHandler
{
	public static boolean load(Configuration configuration, int configVersion)
	{
		switch(configVersion)
		{
		case 0:
			{
				loadconfigv0(configuration);
				return true;
			}
		default:
			return false;
		}
	}
	public static void loadconfigv0(Configuration configuration)
	{
		//load values for configration file v0
	}
}
