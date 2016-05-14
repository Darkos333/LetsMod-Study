package com.darkos333.letsmodstudy.coremod;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({"com.darkos333.letsmodstudy.coremod"})
public class BrewingStandPlugin implements IFMLLoadingPlugin
{

	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] {"com.darkos333.letsmodstudy.coremod.ClassTransofrmer_TileEntityBrewingStand"};
	}

	@Override
	public String getModContainerClass()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSetupClass()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getAccessTransformerClass()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
