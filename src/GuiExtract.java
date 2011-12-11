package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiExtract extends GuiContainer {
  private TileEntityExtract extractorInventory;
	
  public GuiExtract(InventoryPlayer inventoryplayer, TileEntityExtract tileentityextract) {
    super(new ContainerExtract(inventoryplayer, tileentityextract));
    extractorInventory = tileentityextract;
  }

  public void drawGuiContainerForegroundLayer() {
    fontRenderer.drawString("Extractor", 60, 6, 0x404040);
    fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
  }

  protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    int k = mc.renderEngine.getTexture("/TehKrush/PlasticCraft/guiExtractor.png");
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    mc.renderEngine.bindTexture(k);
    int l = (width - xSize) / 2;
    int i1 = (height - ySize) / 2;
    drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
        
    if (extractorInventory.isBurning()) {
      int j1 = extractorInventory.getBurnTimeRemainingScaled(12);
      drawTexturedModalRect(l + 56, (i1 + 36 + 12) - j1, 176, 12 - j1, 14, j1 + 2);
    }
        
    int k1 = extractorInventory.getCookProgressScaled(24);
    drawTexturedModalRect(l + 79, i1 + 34, 176, 14, k1 + 1, 16);
  }
}