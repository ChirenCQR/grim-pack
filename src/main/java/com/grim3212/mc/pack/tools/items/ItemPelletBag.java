package com.grim3212.mc.pack.tools.items;

import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.core.client.gui.PackGuiHandler;
import com.grim3212.mc.pack.core.inventory.InventoryCapability;
import com.grim3212.mc.pack.core.item.ItemManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.tools.client.ManualTools;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ItemPelletBag extends ItemManual {

	public static final String[] colorNumbers = new String[] { "454554", "BE3030", "667F33", "704425", "3366CC", "B266E5", "4C99B2", "999999", "4C4C4C", "F2B2CC", "7FCC19", "E5E533", "99B2F2", "E57FD8", "F4B33F", "FFFFFF" };

	public ItemPelletBag() {
		super("pellet_bag");
		this.maxStackSize = 1;
		this.setCreativeTab(GrimCreativeTabs.GRIM_TOOLS);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		return new InventoryCapability(new ItemStackHandler(9) {
			@Override
			public ItemStack insertItem(int slot, ItemStack toInsert, boolean simulate) {
				if (!toInsert.isEmpty() && toInsert.getItem() == ToolsItems.sling_pellet)
					return super.insertItem(slot, toInsert, simulate);
				else
					return toInsert;
			}
		});
	}

	@Override
	public Page getPage(ItemStack stack) {
		return ManualTools.pelletBag_page;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab))
			for (int i = 0; i < 17; i++) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setInteger("color", i - 1);

				ItemStack pelletbag = new ItemStack(ToolsItems.pellet_bag);
				pelletbag.setTagCompound(nbt);

				items.add(pelletbag);
			}
	}

	@Override
	public String getUnlocalizedName(ItemStack par1ItemStack) {
		String colors[] = { "black", "red", "green", "brown", "blue", "purple", "cyan", "silver", "gray", "pink", "lime", "yellow", "light_blue", "magenta", "orange", "white" };
		if (par1ItemStack.hasTagCompound()) {
			NBTTagCompound nbttagcompound = par1ItemStack.getTagCompound();
			if (nbttagcompound != null) {
				if (nbttagcompound.hasKey("color") && nbttagcompound.getInteger("color") != -1) {
					return super.getUnlocalizedName() + "_" + colors[nbttagcompound.getInteger("color")];
				}
			}
		}
		return super.getUnlocalizedName() + "_" + "leather";
	}

	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int indexInventory, boolean isCurrentItem) {
		if (itemStack.getTagCompound() != null && itemStack.getTagCompound().hasKey("Items")) {
			NBTTagList oldData = itemStack.getTagCompound().getTagList("Items", Constants.NBT.TAG_COMPOUND);
			IItemHandler newInv = itemStack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(newInv, null, oldData);

			itemStack.getTagCompound().removeTag("Items");

			if (itemStack.getTagCompound().getSize() == 0)
				itemStack.setTagCompound(null);
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand) {
		if (!worldIn.isRemote) {
			playerIn.openGui(GrimPack.INSTANCE, hand == EnumHand.MAIN_HAND ? PackGuiHandler.PELLET_BAG_MAIN_GUI_ID : PackGuiHandler.PELLET_BAG_OFF_GUI_ID, playerIn.world, (int) playerIn.posX, (int) playerIn.posY, (int) playerIn.posZ);
		}

		return ActionResult.newResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(hand));
	}

	public static int getColor(ItemStack itemStack) {
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();

		if (nbtTagCompound == null) {
			return -1;
		}
		if (nbtTagCompound.hasKey("color")) {
			return nbtTagCompound.getInteger("color");
		} else {
			return -1;
		}
	}

	public static ItemStack setColor(ItemStack itemStack, int color) {
		NBTTagCompound nbtTagCompound = itemStack.getTagCompound();
		if (nbtTagCompound == null) {
			nbtTagCompound = new NBTTagCompound();
			itemStack.setTagCompound(nbtTagCompound);
		}
		nbtTagCompound.setInteger("color", color);

		return itemStack;
	}
}
