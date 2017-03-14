package com.grim3212.mc.pack.util;

import com.grim3212.mc.pack.core.GrimCore;
import com.grim3212.mc.pack.core.manual.IManualPart;
import com.grim3212.mc.pack.core.network.PacketDispatcher;
import com.grim3212.mc.pack.core.part.GrimPart;
import com.grim3212.mc.pack.core.proxy.CommonProxy;
import com.grim3212.mc.pack.core.util.GrimLog;
import com.grim3212.mc.pack.core.util.Utils;
import com.grim3212.mc.pack.util.client.ManualUtil;
import com.grim3212.mc.pack.util.config.UtilConfig;
import com.grim3212.mc.pack.util.event.BlockChangeEvents;
import com.grim3212.mc.pack.util.event.EntityDeathEvent;
import com.grim3212.mc.pack.util.grave.TileEntityGrave;
import com.grim3212.mc.pack.util.init.UtilBlocks;
import com.grim3212.mc.pack.util.network.MessageFusRoDah;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GrimUtil extends GrimPart {

	public static GrimUtil INSTANCE = new GrimUtil();

	@SidedProxy(clientSide = "com.grim3212.mc.pack.util.client.UtilClientProxy", serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	public static final String partId = "util";
	public static final String partName = "Grim Util";

	public static SoundEvent fusrodahSound;
	public static SoundEvent fusrodahOldSound;
	public static boolean baubles = false;

	public static Achievement UTIL_START;

	public GrimUtil() {
		super(GrimUtil.partId, GrimUtil.partName, new UtilConfig(), false, false);
		addItem(new UtilBlocks());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);

		PacketDispatcher.registerMessage(MessageFusRoDah.class);
		MinecraftForge.EVENT_BUS.register(new EntityDeathEvent());
		MinecraftForge.EVENT_BUS.register(new BlockChangeEvents());
		fusrodahSound = Utils.registerSound("fusrodah");
		fusrodahOldSound = Utils.registerSound("fusrodah-old");

		baubles = Loader.isModLoaded("baubles");
		if (baubles) {
			GrimLog.info(GrimUtil.partName, "Found Baubles enabling grave support");
		}

		UTIL_START = Utils.addAchievement("achievement.util_start", "util_start", -5, 0, new ItemStack(Items.COMPASS), GrimCore.OPEN_MANUAL);

		proxy.preInit();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		GameRegistry.registerTileEntity(TileEntityGrave.class, "grimpack:grave");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IManualPart getManual() {
		return ManualUtil.INSTANCE;
	}
}