package com.darkos333.letsmodstudy.coremod;

import java.util.Arrays;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;

import net.minecraft.launchwrapper.IClassTransformer;

import com.darkos333.letsmodstudy.utility.LogHelper;


public class ClassTransofrmer_TileEntityBrewingStand implements IClassTransformer
{
	private static final String classToTransform = "net.minecraft.tileentity.TileEntityBrewingStand";
	private static final String methodToTransform = "canBrew";

	private static boolean isClassObfuscated = false;


	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		//name - may be obfuscated or not (its runtime name)
		//tranformedName - is always unobfuscated (as seen in forgeSrc)
		
		if(classToTransform.equals(transformedName))
		{
			isClassObfuscated = (name.equals(transformedName) ? false : true);
			return transformTileEntityBrewingStand(basicClass, isClassObfuscated);
		}
		
		return basicClass;
	}
	
	private static byte[] transformTileEntityBrewingStand(byte[] basicClass, boolean isObfuscated)
	{
		System.out.println("Performing ASM transformation of: " + classToTransform);
		try
		{
			ClassNode classNode = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept(classNode, 0);
			
			
			
			
			
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			classNode.accept(classWriter);
			System.out.println("Necessary code successfully injected into: " + classToTransform + "." + methodToTransform + "()");
			return classWriter.toByteArray();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return basicClass;
	}

}
