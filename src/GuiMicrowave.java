package net.minecraft.src;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiMicrowave extends GuiContainer {
  private TileEntityMicrowave microwaveInventory;
	
  public GuiMicrowave(InventoryPlayer inventoryplayer, TileEntityMicrowave tileentitymicrowave) {
    super(new ContainerMicrowave(inventoryplayer, tileentitymicrowave));
    microwaveInventory = tileentitymicrowave;
  }

  public void drawGuiContainerForegroundLayer() {
    fontRenderer.drawString("Microwave", 60, 6, 0x404040);
    fontRenderer.drawString("Inventory", 8, (ySize - 96) + 2, 0x404040);
  }

  protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
    int k = mc.renderEngine.getTexture("/TehKrush/PlasticCraft/guiMicrowave.png");
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    mc.renderEngine.bindTexture(k);
    int l = (width - xSize) / 2;
    int i1 = (height - ySize) / 2;
    drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
    
    if (microwaveInventory.isBurning()) {
      int j1 = microwaveInventory.getBurnTimeRemainingScaled(12);
      drawTexturedModalRect(l + 56, (i1 + 36 + 12) - j1, 176, 12 - j1, 14, j1 + 2);
    }
        
    int k1 = microwaveInventory.getCookProgressScaled(24);
    drawTexturedModalRect(l + 79, i1 + 34, 176, 14, k1 + 1, 16);
  }
}