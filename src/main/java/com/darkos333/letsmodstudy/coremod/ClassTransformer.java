package com.darkos333.letsmodstudy.coremod;

import java.util.Arrays;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.*;
import static org.objectweb.asm.Opcodes.*;

import net.minecraft.launchwrapper.IClassTransformer;

import com.darkos333.letsmodstudy.utility.LogHelper;


public class ClassTransformer implements IClassTransformer
{
	private static final String classToTransform = "net.minecraft.tileentity.TileEntityBrewingStand";
	private static final String methodToTransform = ".canBrew()";

	private static boolean isClassObfuscated = false;

	@Override
	public byte[] transform(String name, String transformedName, byte[] basicClass)
	{
		//name - may be obfuscated or not (its runtime name)
		//tranformedName - is always unobfuscated (as seen in forgeSrc)
		
		if(classToTransform.equals(transformedName))
		{
			isClassObfuscated = (name.equals(transformedName) ? false : true);
			return transformTEBS(basicClass, isClassObfuscated);
		}
		
		return basicClass;
	}
	
	private static byte[] transformTEBS(byte[] basicClass, boolean isObfuscated)
	{
		/*
		 * This transforms TileEntityBrewingStand.canBrew()
		 * It wraps original canBrew code in this manner:
		 * 
		 * 	ALOAD 0
		 * 	GETFIELD brewingItemStacks
		 * 	INVOKESTATIC DummyBrewingRecipesMaker.matchesAnyRecipe(...)
		 * 	IFNE JUMP LABEL
		 * 	
		 * 	[original canBrew() code]
		 * 
		 * 	LABEL
		 * 	ICONST_1
		 * 	IRETURN
		 */
		System.out.println("Performing ASM transformation of: " + classToTransform);
		try
		{
			ClassNode TEBSClass = new ClassNode();
			ClassReader classReader = new ClassReader(basicClass);
			classReader.accept(TEBSClass, 0);
			
			//Startup (obfuscated) names
			String CAN_BREW = isClassObfuscated ? "k" : "canBrew";
			String CAN_BREW_DESC = "()Z";
			
			//Runtime (SRG) names
			String TEBS_PATH = "net/minecraft/tileentity/TileEntityBrewingStand";
			String BREWING_ITEM_STACKS = isClassObfuscated ? "field_145945_j" : "brewingItemStacks";
			String BREWING_ITEM_STACKS_DESC = "[Lnet/minecraft/item/ItemStack;";
			String MATCHES_ANY_RECIPE_DESC = "([Lnet/minecraft/item/ItemStack;)Z";
			
			for(MethodNode method : TEBSClass.methods)
			{
				if(method.name.equals(CAN_BREW) && method.desc.equals(CAN_BREW_DESC))
				{
					//canBrew() mthod found, performing transformation
					AbstractInsnNode targetNode1 = method.instructions.getFirst();
					AbstractInsnNode targetNode2 = method.instructions.getLast();
					
					LabelNode ifTrue = new LabelNode();
					
					InsnList toInsert1 = new InsnList();
					toInsert1.add(new VarInsnNode(ALOAD, 0));
					toInsert1.add(new FieldInsnNode(GETFIELD, TEBS_PATH, BREWING_ITEM_STACKS, BREWING_ITEM_STACKS_DESC));
					toInsert1.add(new MethodInsnNode(INVOKESTATIC, Type.getInternalName(DummyBrewingRecipesMaker.class), "matchesAnyRecipe", MATCHES_ANY_RECIPE_DESC, false));
					toInsert1.add(new JumpInsnNode(IFNE, ifTrue));
					
					InsnList toInsert2 = new InsnList();
					toInsert2.add(ifTrue);
					toInsert2.add(new InsnNode(ICONST_1));
					toInsert2.add(new InsnNode(IRETURN));
					
					method.instructions.insertBefore(targetNode1, toInsert1);
					method.instructions.insert(targetNode2, toInsert2);
				}
			}
			
			ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
			TEBSClass.accept(classWriter);
			System.out.println("Necessary code successfully injected into: " + classToTransform + methodToTransform);
			return classWriter.toByteArray();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return basicClass;
	}
}
