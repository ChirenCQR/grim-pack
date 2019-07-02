package com.grim3212.mc.pack.cuisine.item;

import com.grim3212.mc.pack.core.item.ItemManualFood;
import com.grim3212.mc.pack.core.item.ItemManualPage;
import com.grim3212.mc.pack.core.part.GrimItemGroups;
import com.grim3212.mc.pack.cuisine.config.CuisineConfig;
import com.grim3212.mc.pack.cuisine.init.CuisineNames;
import com.grim3212.mc.pack.cuisine.item.ItemSodaBottle.SodaType;

import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

public class CuisineItems {

	@ObjectHolder(CuisineNames.SODA_APPLE)
	public static Item soda_apple;
	@ObjectHolder(CuisineNames.SODA_SLURM)
	public static Item soda_slurm;
	@ObjectHolder(CuisineNames.SODA_COCOA)
	public static Item soda_cocoa;
	@ObjectHolder(CuisineNames.SODA_ORANGE)
	public static Item soda_orange;
	@ObjectHolder(CuisineNames.SODA_CREAM_ORANGE)
	public static Item soda_cream_orange;
	@ObjectHolder(CuisineNames.SODA_SPIKED_ORANGE)
	public static Item soda_spiked_orange;
	@ObjectHolder(CuisineNames.SODA_DIAMOND)
	public static Item soda_diamond;
	@ObjectHolder(CuisineNames.SODA_GOLDEN_APPLE)
	public static Item soda_golden_apple;
	@ObjectHolder(CuisineNames.SODA_ROOT_BEER)
	public static Item soda_root_beer;
	@ObjectHolder(CuisineNames.SODA_MUSHROOM)
	public static Item soda_mushroom;
	@ObjectHolder(CuisineNames.SODA_CARBONATED_WATER)
	public static Item soda_carbonated_water;
	@ObjectHolder(CuisineNames.CO2)
	public static Item co2;
	@ObjectHolder(CuisineNames.BOTTLE)
	public static Item bottle;

	@ObjectHolder(CuisineNames.DRAGON_FRUIT)
	public static Item dragon_fruit;
	@ObjectHolder(CuisineNames.HEALTHPACK)
	public static Item healthpack;
	@ObjectHolder(CuisineNames.HEALTHPACK_SUPER)
	public static Item healthpack_super;
	@ObjectHolder(CuisineNames.BANDAGE)
	public static Item bandage;
	@ObjectHolder(CuisineNames.SWEETS)
	public static Item sweets;
	@ObjectHolder(CuisineNames.POWERED_SUGAR)
	public static Item powered_sugar;
	@ObjectHolder(CuisineNames.POWERED_SWEETS)
	public static Item powered_sweets;
	@ObjectHolder(CuisineNames.BUTTER)
	public static Item butter;
	@ObjectHolder(CuisineNames.CHEESE)
	public static Item cheese;
	@ObjectHolder(CuisineNames.COCOA_FRUIT)
	public static Item cocoa_fruit;
	@ObjectHolder(CuisineNames.COCOA_DUST)
	public static Item cocoa_dust;
	@ObjectHolder(CuisineNames.CHOCOLATE_BOWL)
	public static Item chocolate_bowl;
	@ObjectHolder(CuisineNames.CHOCOLATE_BOWL_HOT)
	public static Item chocolate_bowl_hot;
	@ObjectHolder(CuisineNames.CHOCOLATE_BAR)
	public static Item chocolate_bar;
	@ObjectHolder(CuisineNames.CHOCOLATE_BAR_WRAPPED)
	public static Item chocolate_bar_wrapped;
	@ObjectHolder(CuisineNames.CHOCOLATE_BALL)
	public static Item chocolate_ball;
	@ObjectHolder(CuisineNames.WRAPPER)
	public static Item wrapper;
	@ObjectHolder(CuisineNames.BREAD_SLICE)
	public static Item bread_slice;
	@ObjectHolder(CuisineNames.CHEESE_BURGER)
	public static Item cheese_burger;
	@ObjectHolder(CuisineNames.HOT_CHEESE)
	public static Item hot_cheese;
	@ObjectHolder(CuisineNames.EGGS_MIXED)
	public static Item eggs_mixed;
	@ObjectHolder(CuisineNames.EGGS_UNMIXED)
	public static Item eggs_unmixed;
	@ObjectHolder(CuisineNames.EGGS_COOKED)
	public static Item eggs_cooked;
	@ObjectHolder(CuisineNames.KNIFE)
	public static Item knife;
	@ObjectHolder(CuisineNames.MIXER)
	public static Item mixer;
	@ObjectHolder(CuisineNames.DOUGH)
	public static Item dough;
	@ObjectHolder(CuisineNames.PAN)
	public static Item pan;
	@ObjectHolder(CuisineNames.PUMPKIN_SLICE)
	public static Item pumpkin_slice;
	@ObjectHolder(CuisineNames.RAW_EMPTY_PIE)
	public static Item raw_empty_pie;
	@ObjectHolder(CuisineNames.RAW_APPLE_PIE)
	public static Item raw_apple_pie;
	@ObjectHolder(CuisineNames.RAW_CHOCOLATE_PIE)
	public static Item raw_chocolate_pie;
	@ObjectHolder(CuisineNames.RAW_PORK_PIE)
	public static Item raw_pork_pie;
	@ObjectHolder(CuisineNames.RAW_MELON_PIE)
	public static Item raw_melon_pie;
	@ObjectHolder(CuisineNames.RAW_PUMPKIN_PIE)
	public static Item raw_pumpkin_pie;

	@SubscribeEvent
	public void initItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();

		if (CuisineConfig.subpartDairy.get() || CuisineConfig.subpartPie.get())
			r.register(new ItemDamage(CuisineNames.KNIFE, 64));

		if (CuisineConfig.subpartSoda.get()) {
			r.register(new ItemSodaBottle(SodaType.APPLE));
			r.register(new ItemSodaBottle(SodaType.SLURM));
			r.register(new ItemSodaBottle(SodaType.COCOA));
			r.register(new ItemSodaBottle(SodaType.ORANGE));
			r.register(new ItemSodaBottle(SodaType.CREAM_ORANGE));
			r.register(new ItemSodaBottle(SodaType.SPIKED_ORANGE));
			r.register(new ItemSodaBottle(SodaType.DIAMOND));
			r.register(new ItemSodaBottle(SodaType.GOLDEN_APPLE));
			r.register(new ItemSodaBottle(SodaType.ROOT_BEER));
			r.register(new ItemSodaBottle(SodaType.MUSHROOM));
			r.register(new ItemSodaBottle(SodaType.CARBONATED_WATER));
			r.register(new ItemManualPage(CuisineNames.CO2, "cuisine:soda.carbon", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE)));
			r.register(new ItemManualPage(CuisineNames.BOTTLE, "cuisine:soda.carbon", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE)));
		}

		if (CuisineConfig.subpartDragonFruit.get())
			r.register(new ItemManualFood(CuisineNames.DRAGON_FRUIT, "cuisine:dragonfruit", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.DRAGON_FRUIT)));

		if (CuisineConfig.subpartHealth.get()) {
			r.register(new ItemManualFood(CuisineNames.SWEETS, "cuisine:sugar.sweets", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.SWEETS)));
			r.register(new ItemManualFood(CuisineNames.POWERED_SWEETS, "cuisine:sugar.sweets", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.POWERED_SWEETS)));
			r.register(new ItemManualPage(CuisineNames.POWERED_SUGAR, "cuisine:sugar.sweets", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE)));
			r.register(new ItemHealthPack(CuisineNames.BANDAGE, 3));
			r.register(new ItemHealthPack(CuisineNames.HEALTHPACK, 5));
			r.register(new ItemHealthPack(CuisineNames.HEALTHPACK_SUPER, 12));
		}

		if (CuisineConfig.subpartDairy.get()) {
			r.register(new ItemDamage(CuisineNames.MIXER, 64));
			r.register(new ItemManualPage(CuisineNames.BUTTER, "cuisine:dairy.butter", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.BUTTER)));
			r.register(new ItemManualPage(CuisineNames.CHEESE, "cuisine:dairy.cheeseblock", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.CHEESE)));
			r.register(new ItemManualPage(CuisineNames.BREAD_SLICE, "cuisine:food.sandwiches", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.BREAD_SLICE)));
			r.register(new ItemManualPage(CuisineNames.CHEESE_BURGER, "cuisine:food.sandwiches", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.CHEESE_BURGER)));
			r.register(new ItemManualPage(CuisineNames.HOT_CHEESE, "cuisine:food.sandwiches", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.HOT_CHEESE)));
			r.register(new ItemManualPage(CuisineNames.EGGS_MIXED, "cuisine:food.eggs", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.EGGS_MIXED)));
			r.register(new ItemManualPage(CuisineNames.EGGS_UNMIXED, "cuisine:food.eggs", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.EGGS_UNMIXED)));
			r.register(new ItemManualPage(CuisineNames.EGGS_COOKED, "cuisine:food.cooked", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.EGGS_COOKED)));
		}

		if (CuisineConfig.subpartChocolate.get()) {
			r.register(new ItemCocoaFruit());
			r.register(new ItemManualPage(CuisineNames.COCOA_DUST, "cuisine:cocoa.dye", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE)));
			r.register(new ItemManualPage(CuisineNames.CHOCOLATE_BALL, "cuisine:bowlchoc.chocBall", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.CHOCOLATE_BALL)));
			r.register(new ItemManualPage(CuisineNames.WRAPPER, "cuisine:choco.candy", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE)));
			r.register(new ItemBowlChocolate(CuisineNames.CHOCOLATE_BOWL, 16));
			r.register(new ItemManualPage(CuisineNames.CHOCOLATE_BAR, "cuisine:choco.bars", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.CHOCOLATE_BAR)));
			r.register(new ItemManualPage(CuisineNames.CHOCOLATE_BAR_WRAPPED, "cuisine:choco.candy", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.CHOCOLATE_BAR_WRAPPED)));
			r.register(new ItemBowlChocolate(CuisineNames.CHOCOLATE_BOWL_HOT, 1, Items.BOWL));
		}

		if (CuisineConfig.subpartPie.get()) {
			r.register(new ItemManualPage(CuisineNames.DOUGH, "cuisine:food.extras", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.DOUGH)));
			r.register(new ItemManualPage(CuisineNames.PAN, "cuisine:food.utensils", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).maxStackSize(16)));
			r.register(new ItemManualFood(CuisineNames.PUMPKIN_SLICE, "cuisine:pie.craft", new Item.Properties().group(GrimItemGroups.GRIM_CUISINE).food(CuisineFoods.PUMPKIN_SLICE)));
			r.register(new ItemRawPie(CuisineNames.RAW_EMPTY_PIE));
			r.register(new ItemRawPie(CuisineNames.RAW_APPLE_PIE));
			r.register(new ItemRawPie(CuisineNames.RAW_CHOCOLATE_PIE));
			r.register(new ItemRawPie(CuisineNames.RAW_PORK_PIE));
			r.register(new ItemRawPie(CuisineNames.RAW_MELON_PIE));
			r.register(new ItemRawPie(CuisineNames.RAW_PUMPKIN_PIE));
		}
	}
}