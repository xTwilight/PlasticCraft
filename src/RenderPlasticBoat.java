package net.minecraft.src;

import org.lwjgl.opengl.GL11;

public class RenderPlasticBoat extends Render {
	protected ModelBase boat;
//  protected ModelBase f;
    
  public RenderPlasticBoat(){
    shadowSize = 0.5F;
    boat = new ModelBoat();
  }

  public void renderBoat(EntityPlasticBoat entityplasticboat, double d, double d1, double d2, float f1, float f2) {
    GL11.glPushMatrix();
    GL11.glTranslatef((float)d, (float)d1, (float)d2);
    GL11.glRotatef(180F - f1, 0.0F, 1.0F, 0.0F);
    
    float f3 = (float)entityplasticboat.func_41018_h() - f2;
    float f4 = (float)entityplasticboat.func_41020_g() - f2;
    if(f4 < 0.0F) f4 = 0.0F;
    if(f3 > 0.0F) GL11.glRotatef(((MathHelper.sin(f3) * f3 * f4) / 10F) * (float)entityplasticboat.func_41016_i(), 1.0F, 0.0F, 0.0F);
    
    loadTexture("/terrain.png");
    float f5 = 0.75F;
    GL11.glScalef(f5, f5, f5);
    GL11.glScalef(1.0F / f5, 1.0F / f5, 1.0F / f5);
    loadTexture((new StringBuilder()).append(mod_PlasticCraft.modDir).append("entityPlasticBoat.png").toString());
    GL11.glScalef(-1F, -1F, 1.0F);
    boat.render(entityplasticboat, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
    GL11.glPopMatrix();
  }

  public void doRender(Entity entity, double d, double d1, double d2, float f1, float f2) {
    renderBoat((EntityPlasticBoat)entity, d, d1, d2, f1, f2);
  }

// I can't remember why these methods were here, they aren't even called. Meh.
//  public void a(ModelBase modelbase) {
//    f = modelbase;
//  }
//
//  public boolean a(EntityPlasticBoat entityplasticboat, int i, float f1) {
//    if (i == 0) {
//      a(boat);
//      GL11.glEnable(2977 /*GL_NORMALIZE*/);
//      GL11.glEnable(3042 /*GL_BLEND*/);
//      GL11.glBlendFunc(770, 771);
//      return true;
//    }
//    if (i == 1) {
//      GL11.glDisable(3042 /*GL_BLEND*/);
//      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
//    }
//    
//    return false;
//  }
}