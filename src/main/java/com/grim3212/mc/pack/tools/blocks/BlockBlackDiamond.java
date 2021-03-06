package com.grim3212.mc.pack.tools.blocks;

import com.grim3212.mc.pack.core.block.BlockManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.tools.client.ManualTools;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockBlackDiamond extends BlockManual {

	public BlockBlackDiamond(Material material, SoundType type) {
		super(material == Material.ROCK ? "black_diamond_ore" : "black_diamond_block", material, type);
		setHardness(2.0F);
		setResistance(5F);
		setCreativeTab(GrimCreativeTabs.GRIM_TOOLS);
	}

	@Override
	public Page getPage(IBlockState state) {
		if (state.getBlock() == ToolsBlocks.black_diamond_ore)
			return ManualTools.blackOre_page;

		return ManualTools.blackBlocks_page;
	}

}
