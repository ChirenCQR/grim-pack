package com.grim3212.mc.pack.industry;

import com.grim3212.mc.pack.core.GrimCore;
import com.grim3212.mc.pack.core.manual.IManualPart;
import com.grim3212.mc.pack.core.network.PacketDispatcher;
import com.grim3212.mc.pack.core.part.GrimPart;
import com.grim3212.mc.pack.core.util.Utils;
import com.grim3212.mc.pack.industry.block.IndustryBlocks;
import com.grim3212.mc.pack.industry.client.ManualIndustry;
import com.grim3212.mc.pack.industry.config.IndustryConfig;
import com.grim3212.mc.pack.industry.entity.IndustryEntities;
import com.grim3212.mc.pack.industry.item.IndustryItems;
import com.grim3212.mc.pack.industry.network.MessageExtruderDirection;
import com.grim3212.mc.pack.industry.network.MessageSaveFan;
import com.grim3212.mc.pack.industry.network.MessageSensorChangeMode;
import com.grim3212.mc.pack.industry.network.MessageSensorSetBox;
import com.grim3212.mc.pack.industry.network.MessageSensorSetEntity;
import com.grim3212.mc.pack.industry.network.MessageSensorSetItem;
import com.grim3212.mc.pack.industry.network.MessageSensorSetPlayer;
import com.grim3212.mc.pack.industry.network.MessageSensorSetPos;
import com.grim3212.mc.pack.industry.network.MessageSensorSetRender;
import com.grim3212.mc.pack.industry.tile.IndustryTileEntities;
import com.grim3212.mc.pack.industry.world.IndustryGenerate;

import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GrimIndustry extends GrimPart {

	public static GrimIndustry INSTANCE = new GrimIndustry();

	@SidedProxy(clientSide = "com.grim3212.mc.pack.industry.client.IndustryClientProxy", serverSide = "com.grim3212.mc.pack.industry.IndustryCommonProxy")
	public static IndustryCommonProxy proxy;

	public static final String partId = "industry";
	public static final String partName = "Grim Industry";

	public static SoundEvent spikeDeploySound;
	public static SoundEvent spikeCloseSound;

	public static Achievement INDUSTRY_START;

	public GrimIndustry() {
		super(GrimIndustry.partId, GrimIndustry.partName, new IndustryConfig());
		addItem(new IndustryBlocks());
		addItem(new IndustryItems());
		addEntity(new IndustryEntities());
		addTileEntity(new IndustryTileEntities());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		PacketDispatcher.registerMessage(MessageSensorChangeMode.class);
		PacketDispatcher.registerMessage(MessageSensorSetEntity.class);
		PacketDispatcher.registerMessage(MessageSensorSetItem.class);
		PacketDispatcher.registerMessage(MessageSensorSetPlayer.class);
		PacketDispatcher.registerMessage(MessageSensorSetPos.class);
		PacketDispatcher.registerMessage(MessageSensorSetRender.class);
		PacketDispatcher.registerMessage(MessageSensorSetBox.class);
		PacketDispatcher.registerMessage(MessageSaveFan.class);
		PacketDispatcher.registerMessage(MessageExtruderDirection.class);
		GameRegistry.registerWorldGenerator(new IndustryGenerate(), 10);

		spikeDeploySound = Utils.registerSound("spikeDeploy");
		spikeCloseSound = Utils.registerSound("spikeClose");

		INDUSTRY_START = Utils.addAchievement("achievement.industry_start", "industry_start", -5, 5, new ItemStack(IndustryBlocks.refinery), GrimCore.OPEN_MANUAL);

		proxy.preInit();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
		proxy.initColors();
	}

	@Override
	protected ItemStack getCreativeTabIcon() {
		return new ItemStack(IndustryBlocks.togglerack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IManualPart getManual() {
		return ManualIndustry.INSTANCE;
	}
}