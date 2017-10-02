package com.grim3212.mc.pack.decor.client.tile;

import java.util.List;

import org.lwjgl.opengl.GL11;

import com.grim3212.mc.pack.GrimPack;
import com.grim3212.mc.pack.decor.block.DecorBlocks;
import com.grim3212.mc.pack.decor.tile.TileEntityNeonSign;

import net.minecraft.block.Block;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.client.model.ModelSign;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class TileEntityNeonSignRenderer extends TileEntitySpecialRenderer<TileEntityNeonSign> {

	private static final ResourceLocation NEON_SIGN_TEXTURE = new ResourceLocation(GrimPack.modID, "textures/entities/neon_sign.png");
	private static final ResourceLocation NEON_SIGN_CLEAR_TEXTURE = new ResourceLocation(GrimPack.modID, "textures/entities/neon_sign_clear.png");
	private static final ResourceLocation NEON_SIGN_WHITE_TEXTURE = new ResourceLocation(GrimPack.modID, "textures/entities/neon_sign_white.png");
	private static final ResourceLocation SIGN_TEXTURE = new ResourceLocation("textures/entity/sign.png");
	/** The ModelSign instance for use in this renderer */
	private final ModelSign model = new ModelSign();

	@Override
	public void render(TileEntityNeonSign te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		Block block = te.getBlockType();
		GlStateManager.pushMatrix();

		if (block == DecorBlocks.neon_sign_standing) {
			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			float f1 = (float) (te.getBlockMetadata() * 360) / 16.0F;
			GlStateManager.rotate(-f1, 0.0F, 1.0F, 0.0F);
			this.model.signStick.showModel = true;
		} else {
			int k = te.getBlockMetadata();
			float f2 = 0.0F;

			if (k == 2) {
				f2 = 180.0F;
			}

			if (k == 4) {
				f2 = 90.0F;
			}

			if (k == 5) {
				f2 = -90.0F;
			}

			GlStateManager.translate((float) x + 0.5F, (float) y + 0.5F, (float) z + 0.5F);
			GlStateManager.rotate(-f2, 0.0F, 1.0F, 0.0F);
			GlStateManager.translate(0.0F, -0.3125F, -0.4375F);
			this.model.signStick.showModel = false;
		}

		if (destroyStage >= 0) {
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 2.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else {
			if (te.mode == 0) {
				this.bindTexture(NEON_SIGN_TEXTURE);
			} else if (te.mode == 1) {
				this.bindTexture(NEON_SIGN_WHITE_TEXTURE);
			} else if (te.mode == 2) {
				this.bindTexture(NEON_SIGN_CLEAR_TEXTURE);
			} else {
				// Currently should never happen
				this.bindTexture(SIGN_TEXTURE);
			}
		}

		GlStateManager.enableRescaleNormal();
		GlStateManager.pushMatrix();
		float f = 0.6666667F;
		GlStateManager.scale(f, -f, -f);
		this.model.renderSign();
		GlStateManager.popMatrix();
		FontRenderer fontrenderer = this.getFontRenderer();
		float f3 = 0.010416667F;
		GlStateManager.translate(0.0F, 0.33333334F, 0.046666667F);
		GlStateManager.scale(f3, -f3, f3);
		GlStateManager.glNormal3f(0.0F, 0.0F, -f3);
		GlStateManager.depthMask(false);

		if (destroyStage < 0) {
			for (int j = 0; j < te.signText.length; ++j) {
				if (te.signText[j] != null) {
					ITextComponent itextcomponent = te.signText[j];
					List<ITextComponent> list = GuiUtilRenderComponents.splitText(itextcomponent, 90, fontrenderer, false, true);
					String s = list != null && !list.isEmpty() ? ((ITextComponent) list.get(0)).getFormattedText() : "";

					if (j == te.lineBeingEdited) {
						s = "> " + s + "\2470 <";
						renderLightText(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5);
					} else {
						renderLightText(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5);

						// Render on both sides for clear sign
						if (te.mode == 2 && block == DecorBlocks.neon_sign_standing) {
							GlStateManager.pushMatrix();
							GlStateManager.translate(0.0F, 0.0F, -9F);
							GlStateManager.rotate(180F, 0.0F, 1.0F, 0.0F);
							renderLightText(s, -fontrenderer.getStringWidth(s) / 2, j * 10 - te.signText.length * 5);
							GlStateManager.popMatrix();
						}
					}

				}
			}
		}

		GlStateManager.depthMask(true);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.popMatrix();

		if (destroyStage >= 0) {
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}

	private void renderLightText(String s, int x, int y) {
		GlStateManager.pushAttrib();
		GlStateManager.depthMask(true);
		this.getFontRenderer().drawString(s, x, y, 0);
		drawString(s, x, y, true);
		drawString(s, x, y, false);
		GlStateManager.popAttrib();
	}

	protected void drawString(String s, int x, int y, boolean flag) {
		FontRenderer font = this.getFontRenderer();

		GlStateManager.pushMatrix();
		GlStateManager.disableLighting();
		GlStateManager.depthMask(false);
		GlStateManager.enableBlend();

		if (flag) {
			GlStateManager.disableAlpha();
			GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE);
			int k = 61680;
			int l = k % 0x10000;
			int i1 = k / 0x10000;
			OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l / 1.0F, (float) i1 / 1.0F);
		} else {
			GlStateManager.blendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_ALPHA);
		}

		font.drawString(s, x, y, 0xff000000);

		if (flag) {
			GlStateManager.enableAlpha();
		}

		font.drawString(s, x, y, 0xff000000);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.disableBlend();
		GlStateManager.depthMask(true);
		GlStateManager.enableLighting();
		GlStateManager.popMatrix();
	}

}
