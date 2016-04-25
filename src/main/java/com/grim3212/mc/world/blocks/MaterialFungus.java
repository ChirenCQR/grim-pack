package com.grim3212.mc.world.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialFungus extends Material {

	public MaterialFungus() {
		super(MapColor.airColor);
	}

	@Override
	public boolean isToolNotRequired() {
		return true;
	}

	@Override
	public boolean getCanBurn() {
		return true;
	}

	@Override
	public boolean blocksLight() {
		return false;
	}

	@Override
	public boolean isOpaque() {
		return false;
	}
}
