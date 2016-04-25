package com.grim3212.mc.world.client;

import com.grim3212.mc.core.client.RenderHelper;
import com.grim3212.mc.core.proxy.ClientProxy;
import com.grim3212.mc.world.blocks.BlockFungusBuilding;
import com.grim3212.mc.world.blocks.BlockFungusGrowing;
import com.grim3212.mc.world.blocks.BlockFungusKilling;
import com.grim3212.mc.world.blocks.BlockGlowstoneSeed;
import com.grim3212.mc.world.blocks.BlockGunpowderReed;
import com.grim3212.mc.world.blocks.WorldBlocks;
import com.grim3212.mc.world.client.entity.RenderIceCube.RenderIceCubeFactory;
import com.grim3212.mc.world.client.entity.RenderIcePixie.RenderIcePixieFactory;
import com.grim3212.mc.world.client.entity.RenderTreasureMob.RenderTreasureMobFactory;
import com.grim3212.mc.world.entity.EntityIceCube;
import com.grim3212.mc.world.entity.EntityIcePixie;
import com.grim3212.mc.world.entity.EntityTreasureMob;
import com.grim3212.mc.world.items.WorldItems;

import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class WorldClientProxy extends ClientProxy {

	@Override
	public void registerModels() {
		ModelLoader.setCustomStateMapper(WorldBlocks.gunpowder_reed_block, new StateMap.Builder().ignore(BlockGunpowderReed.AGE).build());
		ModelLoader.setCustomStateMapper(WorldBlocks.glowstone_seeds, new StateMap.Builder().ignore(BlockGlowstoneSeed.STEP).build());
		ModelLoader.setCustomStateMapper(WorldBlocks.fungus_building, new StateMap.Builder().ignore(BlockFungusBuilding.TYPE).build());
		ModelLoader.setCustomStateMapper(WorldBlocks.fungus_growing, new StateMap.Builder().ignore(BlockFungusGrowing.TYPE).build());
		ModelLoader.setCustomStateMapper(WorldBlocks.fungus_killing, new StateMap.Builder().ignore(BlockFungusKilling.TYPE).build());

		RenderHelper.renderBlock(WorldBlocks.randomite);
		RenderHelper.renderBlock(WorldBlocks.gunpowder_reed_block);
		RenderHelper.renderBlock(WorldBlocks.glowstone_seeds);
		RenderHelper.renderBlock(WorldBlocks.corruption_block);
		RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_building, 16);
		RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_growing, 16);
		RenderHelper.renderBlockWithMetaInInventory(WorldBlocks.fungus_killing, 16);

		RenderHelper.renderItem(WorldItems.gunpowder_reed_item);
		RenderHelper.renderItem(WorldItems.fungicide);

		// Entities
		RenderingRegistry.registerEntityRenderingHandler(EntityIcePixie.class, new RenderIcePixieFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityIceCube.class, new RenderIceCubeFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityTreasureMob.class, new RenderTreasureMobFactory());
	}

}
