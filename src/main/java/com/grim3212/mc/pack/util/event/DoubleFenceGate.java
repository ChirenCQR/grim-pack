package com.grim3212.mc.pack.util.event;

import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class DoubleFenceGate {

	private static boolean isFenceGate(World world, BlockPos pos, IBlockState state) {
		if (world.getBlockState(pos).getBlock() == state.getBlock()) {
			if (world.getBlockState(pos).getValue(BlockFenceGate.FACING) == state.getValue(BlockFenceGate.FACING)) {
				return true;
			}
		}
		return false;
	}

	public static void updateFenceGate(World world, BlockPos pos, IBlockState state, EntityPlayer entityPlayer, EnumHand enumHand, EnumFacing facing, Vec3d vec3d) {
		// Get all fence gates above the block
		int upBlocks = 1;
		while (isFenceGate(world, pos.up(upBlocks), state)) {
			state.getBlock().onBlockActivated(world, pos.up(upBlocks), state, entityPlayer, enumHand, facing, (float) vec3d.x, (float) vec3d.y, (float) vec3d.z);
			upBlocks++;
		}

		// Get all fence gates below the block
		int downBlocks = 1;
		while (isFenceGate(world, pos.down(downBlocks), state)) {
			state.getBlock().onBlockActivated(world, pos.down(downBlocks), state, entityPlayer, enumHand, facing, (float) vec3d.x, (float) vec3d.y, (float) vec3d.z);
			downBlocks++;
		}
	}

	public static void setFenceGate(World world, BlockPos pos, IBlockState state) {
		// Get all fence gates above the block
		int upBlocks = 1;
		while (isFenceGate(world, pos.up(upBlocks), state)) {
			IBlockState neighborState = world.getBlockState(pos.up(upBlocks));

			world.setBlockState(pos.up(upBlocks), neighborState.withProperty(BlockFenceGate.OPEN, state.getValue(BlockFenceGate.OPEN)));
			upBlocks++;
		}

		// Get all fence gates below the block
		int downBlocks = 1;
		while (isFenceGate(world, pos.down(downBlocks), state)) {
			IBlockState neighborState = world.getBlockState(pos.down(downBlocks));

			world.setBlockState(pos.down(downBlocks), neighborState.withProperty(BlockFenceGate.OPEN, state.getValue(BlockFenceGate.OPEN)));
			downBlocks++;
		}
	}
}
