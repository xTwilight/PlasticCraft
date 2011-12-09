package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

public class BlockPlexiglass extends BlockBreakable implements ITextureProvider {
  public BlockPlexiglass(int i) {
    super(i, 2, Material.glass, false);
    setHardness(1.0F);
    setResistance(1500F);
    setLightOpacity(1);
    setStepSound(soundGlassFootstep);
    setBlockName("pPlexiglass");
    setTickOnLoad(true);
  }
  
  public int getLightValue(IBlockAccess iblockaccess, int i, int j, int k) {
  	int meta = iblockaccess.getBlockMetadata(i, j, k);
  	if (meta == 1) return 15;
  	else return 0;
  }

  public int getRenderBlockPass() {
    return 1;
  }

  public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
    return super.shouldSideBeRendered(iblockaccess, i, j, k, 1 - l);
  }

  public int quantityDropped(Random random) {
    return 1;
  }
  
  protected int damageDropped(int i) {
    return i;
  }
  
  public String getTextureFile() {
		return mod_PlasticCraft.blockSheet;
	}
}