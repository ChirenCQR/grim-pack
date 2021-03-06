package com.grim3212.mc.pack.industry.block.storage;

import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.industry.client.ManualIndustry;
import com.grim3212.mc.pack.industry.tile.TileEntityGlassCabinet;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGlassCabinet extends BlockStorage {

	public BlockGlassCabinet() {
		super("glass_cabinet", Material.WOOD, SoundType.WOOD);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		return new TileEntityGlassCabinet();
	}

	@Override
	public Page getPage(IBlockState state) {
		return ManualIndustry.cabinets_page;
	}

}
