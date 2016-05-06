package com.darkos333.letsmodstudy.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.darkos333.letsmodstudy.configuration.ConfigurationHandler;
import com.darkos333.letsmodstudy.reference.Config;
import com.darkos333.letsmodstudy.reference.Reference;
import com.darkos333.letsmodstudy.utility.LogHelper;

import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class ModGuiConfig extends GuiConfig
{
	private static Configuration config = ConfigurationHandler.getConfiguration();
	private static String configPath = getAbridgedConfigPath(config.toString());
	
	private static List<IConfigElement> makeConfigElements()
	{
		List<IConfigElement> configElements = new ArrayList<IConfigElement>();
		
		ConfigCategory cat1 = config.getCategory("test_category");
		cat1.setComment("This is an example GUI comment");
		cat1.setLanguageKey("configgui.test_category.name");
		configElements.add(new ConfigElement(cat1));
		
		Property val1 = cat1.get("testConfigValue1");
		val1.comment = "This is an example GUI comment for testConfigValue1";
		val1.setLanguageKey("configgui.test_category.testConfigValue1.name");
		
		Property val2 = cat1.get("testConfigValue2");
		val2.comment = "This is an example GUI comment for testConfigValue2";
		val2.setLanguageKey("configgui.test_category.testConfigValue2.name");
		
		
		//configElements.add(new ConfigElement(config.getCategory("test_category")));
		//configElements.addAll(new ConfigElement(config.getCategory("test_category")).getChildElements());
		
		return configElements;
	}
	
	public ModGuiConfig(GuiScreen guiScreen)
	{
		super(guiScreen, makeConfigElements(), Reference.ModID, false, false, configPath);
	}
}


