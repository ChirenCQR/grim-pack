package com.grim3212.mc.pack.cuisine.block;

import com.grim3212.mc.pack.core.block.BlockManual;
import com.grim3212.mc.pack.core.manual.IManualEntry.IManualBlock;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.cuisine.client.ManualCuisine;
import com.grim3212.mc.pack.cuisine.init.CuisineNames;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public class BlockCheese extends BlockManual implements IManualBlock {

	protected static final VoxelShape CHEESE_SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 8.0D, 15.0D);

	protected BlockCheese() {
		super(CuisineNames.CHEESE_BLOCK, Block.Properties.create(Material.CAKE).sound(SoundType.CLOTH).hardnessAndResistance(0.5f));
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
		return CHEESE_SHAPE;
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		return super.isValidPosition(state, worldIn, pos) ? this.canBlockStay(worldIn, pos) : false;
	}

	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean p_220069_6_) {
		if (!this.canBlockStay(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}

	private boolean canBlockStay(IWorldReader worldIn, BlockPos pos) {
		return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	}

	@Override
	public Page getPage(BlockState state) {
		return ManualCuisine.cheeseBlock_page;
	}
}
