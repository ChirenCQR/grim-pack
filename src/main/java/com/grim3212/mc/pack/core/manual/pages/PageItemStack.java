package com.grim3212.mc.pack.core.manual.pages;

import java.awt.Color;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.core.client.TooltipHelper;
import com.grim3212.mc.pack.core.manual.gui.GuiManualPage;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class PageItemStack extends Page {

	private ResourceLocation stackOverlay = new ResourceLocation(GrimPack.modID, "textures/gui/stack_overlay.png");

	private List<ItemStack> displayStacks;

	private int stackShown = 0;
	private int update = 0;
	private int updateTime = 1;

	public PageItemStack(String pageName, ItemStack stack) {
		this(pageName, 1, stack);
	}

	public PageItemStack(String pageName, int updateTime, ItemStack... stacks) {
		this(pageName, updateTime, Lists.newArrayList(stacks));
	}

	public PageItemStack(String pageName, int updateTime, List<ItemStack> stacks) {
		super(pageName, false);

		this.displayStacks = stacks;
		this.updateTime = updateTime;
	}

	@Override
	public void drawScreen(GuiManualPage gui, int mouseX, int mouseY) {
		super.drawScreen(gui, mouseX, mouseY);

		relativeMouseX = mouseX;
		relativeMouseY = mouseY;

		int x = gui.getX() + 15;
		int y = gui.getY() + 28;
		PageInfo.drawText(x, y, this.getInfo());

		TextureManager render = Minecraft.getInstance().getTextureManager();
		render.bindTexture(stackOverlay);

		RenderSystem.color4f(1f, 1f, 1f, 1f);
		((Screen) gui).blit(gui.getX() + 21, gui.getY() + 120, 21, 120, 147, 85);

		tooltipItem = ItemStack.EMPTY;

		ItemStack outstack = displayStacks.get(stackShown);

		int xOffset = 72;
		int yOffset = 142;
		this.renderItem(gui, outstack, gui.getX() + xOffset, gui.getY() + yOffset);

		FontRenderer renderer = Minecraft.getInstance().fontRenderer;
		renderer.drawString(outstack.getDisplayName().getFormattedText(), (gui.getManualWidth() / 2 - renderer.getStringWidth(outstack.getDisplayName().getFormattedText()) / 2) + gui.getX(), gui.getY() + 210, Color.BLACK.getRGB());

		if (!tooltipItem.isEmpty()) {
			TooltipHelper.renderToolTip(tooltipItem, mouseX, mouseY);
		}
	}

	@Override
	public void renderItem(GuiManualPage gui, ItemStack item, int x, int y) {
		ItemRenderer render = Minecraft.getInstance().getItemRenderer();

		RenderSystem.pushMatrix();
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		RenderHelper.enableStandardItemLighting();

		RenderSystem.translatef(-x * 2f, -y * 2f, 0);
		RenderSystem.scalef(3F, 3F, 0.75F);

		RenderSystem.enableRescaleNormal();
		RenderSystem.enableDepthTest();

		render.renderItemAndEffectIntoGUI(item, x, y);
		render.renderItemOverlayIntoGUI(Minecraft.getInstance().fontRenderer, item, x, y, (String) null);
		RenderHelper.disableStandardItemLighting();
		RenderSystem.popMatrix();

		if (relativeMouseX >= x && relativeMouseY >= y && relativeMouseX <= x + (16 * 3) && relativeMouseY <= y + (16 * 3)) {
			this.tooltipItem = item;
		}

		RenderSystem.disableLighting();
	}

	@Override
	public void updateScreen() {
		if (update % this.updateTime == 0) {
			stackShown++;

			if (stackShown == displayStacks.size())
				stackShown = 0;
		}
		++update;
	}

	@Override
	public JsonObject deconstruct() {
		JsonObject json = super.deconstruct();

		JsonArray displayStacks = new JsonArray();
		for (ItemStack stack : this.displayStacks) {
			displayStacks.add(this.deconstructItem(stack));
		}

		json.add("displayStacks", displayStacks);

		return json;
	}
}
