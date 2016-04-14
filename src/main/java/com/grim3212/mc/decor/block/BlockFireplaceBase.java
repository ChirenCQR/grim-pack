package com.grim3212.mc.decor.block;

import java.util.LinkedHashMap;
import java.util.List;

import com.grim3212.mc.core.network.PacketDispatcher;
import com.grim3212.mc.core.property.UnlistedPropertyBoolean;
import com.grim3212.mc.core.util.NBTHelper;
import com.grim3212.mc.decor.GrimDecor;
import com.grim3212.mc.decor.network.MessageExtinguish;
import com.grim3212.mc.decor.network.MessageUpdateFireplace;
import com.grim3212.mc.decor.tile.TileEntityFireplace;
import com.grim3212.mc.decor.util.BlockHelper;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;

public class BlockFireplaceBase extends BlockTextured {

	public static final UnlistedPropertyBoolean ACTIVE = UnlistedPropertyBoolean.create("active");

	protected BlockFireplaceBase() {
		setCreativeTab(GrimDecor.INSTANCE.getCreativeTab());
	}

	@Override
	protected BlockState createBlockState() {
		return new ExtendedBlockState(this, new IProperty[] {}, new IUnlistedProperty[] { BLOCKID, BLOCKMETA, ACTIVE });
	}

	@Override
	public void onBlockClicked(World worldIn, BlockPos pos, EntityPlayer playerIn) {
		TileEntityFireplace tef = (TileEntityFireplace) worldIn.getTileEntity(pos);
		if (worldIn.getBlockState(pos).getBlock() != DecorBlocks.chimney) {
			if (tef.isActive()) {
				if (!worldIn.isRemote) {
					PacketDispatcher.sendToDimension(new MessageExtinguish(pos), playerIn.dimension);
					tef.setActive(false);
				}
				worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "random.fizz", 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
			}
		}
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		if (worldIn.isRemote)
			return true;

		TileEntityFireplace tef = (TileEntityFireplace) worldIn.getTileEntity(pos);
		ItemStack stack = playerIn.inventory.getStackInSlot(playerIn.inventory.currentItem);

		if (state.getBlock() != DecorBlocks.chimney) {
			if ((stack != null) && ((stack.getItem() == Items.flint_and_steel) || (stack.getItem() == Items.fire_charge))) {
				if (!tef.isActive()) {
					stack.damageItem(1, playerIn);
					worldIn.playSoundEffect(pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, "fire.ignite", 1.0F, worldIn.rand.nextFloat() * 0.4F + 0.8F);
					PacketDispatcher.sendToDimension(new MessageUpdateFireplace(pos, true), playerIn.dimension);
					tef.setActive(true);
				}

				return true;
			}
		}
		return false;
	}

	@Override
	public int getLightValue(IBlockAccess world, BlockPos pos) {
		TileEntityFireplace tef = (TileEntityFireplace) world.getTileEntity(pos);

		if (tef != null && world.getBlockState(pos).getBlock() != DecorBlocks.chimney) {
			return tef.getLightValue();
		} else {
			return super.getLightValue(world, pos);
		}
	}

	@Override
	public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileEntityFireplace && state instanceof IExtendedBlockState) {
			IExtendedBlockState blockState = (IExtendedBlockState) state;
			TileEntityFireplace tef = (TileEntityFireplace) te;
			return blockState.withProperty(BLOCKID, tef.getBlockID()).withProperty(BLOCKMETA, tef.getBlockMeta()).withProperty(ACTIVE, tef.isActive());
		}
		return state;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		LinkedHashMap<Block, Integer> loadedBlocks = BlockHelper.getBlocks();
		Block[] blocks = loadedBlocks.keySet().toArray(new Block[loadedBlocks.keySet().size()]);

		for (int i = 0; i < blocks.length; i++) {
			if (blocks[i].getMaterial() == Material.wood || blocks[i].getMaterial() == Material.cloth || blocks[i].getMaterial() == Material.gourd || blocks[i].getMaterial() == Material.ice || blocks[i].getMaterial() == Material.glass || blocks[i].getMaterial() == Material.packedIce || blocks[i].getMaterial() == Material.sponge || blocks[i].getMaterial() == Material.grass || blocks[i].getMaterial() == Material.ground) {
				continue;
			}

			if (loadedBlocks.get(blocks[i]) == 0) {
				ItemStack stack = new ItemStack(itemIn, 1);
				NBTHelper.setInteger(stack, "blockID", Block.getIdFromBlock(blocks[i]));
				NBTHelper.setInteger(stack, "blockMeta", 0);
				list.add(stack);
			} else {
				for (int j = 0; j < loadedBlocks.get(blocks[i]); j++) {
					ItemStack stack = new ItemStack(itemIn, 1);
					NBTHelper.setInteger(stack, "blockID", Block.getIdFromBlock(blocks[i]));
					NBTHelper.setInteger(stack, "blockMeta", j);
					list.add(stack);
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityFireplace();
	}
}