package com.grim3212.mc.pack.tools.items;

import com.grim3212.mc.pack.core.item.ItemManual;
import com.grim3212.mc.pack.core.manual.pages.Page;
import com.grim3212.mc.pack.core.part.GrimCreativeTabs;
import com.grim3212.mc.pack.tools.client.ManualTools;
import com.grim3212.mc.pack.tools.util.EnumPelletType;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;

public class ItemSlingPellet extends ItemManual {

	public ItemSlingPellet() {
		super("sling_pellet");
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		setCreativeTab(GrimCreativeTabs.GRIM_TOOLS);
	}

	@Override
	public Page getPage(ItemStack stack) {
		return ManualTools.pellets_page;
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int i = MathHelper.clamp(stack.getItemDamage(), 0, EnumPelletType.values.length);
		return super.getUnlocalizedName() + "." + EnumPelletType.values[i].getUnlocalized();
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab))
			for (int j = 0; j < EnumPelletType.values.length; ++j) {
				items.add(new ItemStack(this, 1, j));
			}
	}

	public static EnumPelletType getPelletType(ItemStack stack) {
		if (stack.getItem() == ToolsItems.sling_pellet) {
			switch (stack.getMetadata()) {
			case 1:
				return EnumPelletType.IRON;
			case 2:
				return EnumPelletType.NETHERRACK;
			case 3:
				return EnumPelletType.LIGHT;
			case 4:
				return EnumPelletType.FIRE;
			case 5:
				return EnumPelletType.EXPLOSION;
			default:
				return EnumPelletType.STONE;
			}
		}

		return EnumPelletType.STONE;
	}
}
