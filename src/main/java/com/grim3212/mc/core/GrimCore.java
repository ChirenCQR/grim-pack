package com.grim3212.mc.core;

import com.grim3212.mc.core.client.gui.CoreGuiHandler;
import com.grim3212.mc.core.config.CoreConfig;
import com.grim3212.mc.core.config.GrimConfig;
import com.grim3212.mc.core.item.CoreItems;
import com.grim3212.mc.core.manual.event.LoginEvent;
import com.grim3212.mc.core.part.GrimPart;
import com.grim3212.mc.core.proxy.CommonProxy;

import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = GrimCore.modID, name = GrimCore.modName, version = GrimCore.modVersion, dependencies = "required-after:Forge@[11.15.1.1855,)", acceptedMinecraftVersions = "[1.8.9]", guiFactory = "com.grim3212.mc.core.config.ConfigGuiFactory", updateJSON = "https://raw.githubusercontent.com/grim3212/Grim-Pack/master/update.json")
public class GrimCore extends GrimPart {

	@Instance(GrimCore.modID)
	public static GrimCore INSTANCE;

	@SidedProxy(clientSide = "com.grim3212.mc.core.client.CoreClientProxy", serverSide = COMMON_PROXY)
	public static CommonProxy proxy;

	public static final String modID = "grimcore";
	public static final String modName = "Grim Core";
	public static final String modVersion = "1.0.0.2";

	public GrimCore() {
		super(GrimCore.modID, GrimCore.modName, GrimCore.modVersion);
		addItem(new CoreItems());
	}

	@Override
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		ModMetadata data = event.getModMetadata();
		data.description = "The core module that all of grims mods require.";
		data.url = "http://mods.grim3212.com/mc/" + "my-mods/grim-core/";
		data.credits = "Thanks to all the mod authors of mods that I have updated. Thanks to the Forge team and everyone who has helped contribute or request mods.";

		// Register GUI handler for the Instruction Manual
		NetworkRegistry.INSTANCE.registerGuiHandler(INSTANCE, new CoreGuiHandler());

		// Register LoginEvent for receiving the Instruction Manual
		MinecraftForge.EVENT_BUS.register(new LoginEvent());

		proxy.registerModels();
	}

	@Override
	@EventHandler
	public void init(FMLInitializationEvent event) {
		super.init(event);
		proxy.registerManual(getModSection());
	}

	@Override
	protected Item getCreativeTabIcon() {
		return CoreItems.instruction_manual;
	}

	@Override
	public GrimConfig setConfig() {
		return new CoreConfig();
	}
}