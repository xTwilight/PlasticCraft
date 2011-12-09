package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderC4Primed extends Render {
	private RenderBlocks blockRenderer;
	
  public RenderC4Primed() {
  	blockRenderer = new RenderBlocks();
    shadowSize = 0.5F;
  }

  public void renderC4(EntityC4Primed entityc4primed, double d, double d1, double d2, float f, float f1) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    GL11.glScalef(entityc4primed.renderScale, entityc4primed.renderScale, entityc4primed.renderScale);
        
    if (((float)entityc4primed.fuse - f1) + 1.0F < 10F) {
      float f2 = 1.0F - (((float)entityc4primed.fuse - f1) + 1.0F) / 10F;
            
      if (f2 < 0.0F) f2 = 0.0F;
      if (f2 > 1.0F) f2 = 1.0F;
      f2 *= f2;
      f2 *= f2;
      float f4 = 1.0F + f2 * 0.3F;
      GL11.glScalef(f4, f4, f4);
    }
        
    float f3 = (1.0F - (((float)entityc4primed.fuse - f1) + 1.0F) / 100F) * 0.8F;
    loadTexture(mod_PlasticCraft.modDir + "pc_terrain.png");
    blockRenderer.renderBlockOnInventory(mod_PlasticCraft.blockC4, 0, 1.0F);
    
    if ((entityc4primed.fuse / 5) % 2 == 0) {
      GL11.glDisable(3553 /*GL_TEXTURE_2D*/);
      GL11.glDisable(2896 /*GL_LIGHTING*/);
      GL11.glEnable(3042 /*GL_BLEND*/);
      GL11.glBlendFunc(770, 772);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, f3);
      blockRenderer.renderBlockOnInventory(mod_PlasticCraft.blockC4, 0, 1.0F);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042 /*GL_BLEND*/);
      GL11.glEnable(2896 /*GL_LIGHTING*/);
      GL11.glEnable(3553 /*GL_TEXTURE_2D*/);
    }
    
    GL11.glPopMatrix();
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f, float f1) {
  	renderC4((EntityC4Primed)entity, d, d1, d2, f, f1);
  }
}