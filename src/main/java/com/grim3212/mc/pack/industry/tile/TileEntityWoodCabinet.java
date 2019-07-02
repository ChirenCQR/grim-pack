package com.grim3212.mc.pack.industry.tile;

import net.minecraft.block.BlockState;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Blocks;

public class TileEntityWoodCabinet extends TileEntityStorage {

	@Override
	protected String getStorageName() {
		return "wood_cabinet";
	}

	@Override
	public BlockState getBreakTextureState() {
		return Blocks.LOG.getDefaultState();
	}
}
