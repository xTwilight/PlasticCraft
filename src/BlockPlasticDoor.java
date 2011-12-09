package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

public class BlockPlasticDoor extends BlockDoor implements ITextureProvider {
  public BlockPlasticDoor(int i) {
    super(i, Material.glass);
    setHardness(1.0F);
    setResistance(1500F);
    setStepSound(soundGlassFootstep);
    setBlockName("pPlasticDoor");
    blockIndexInTexture = mod_PlasticCraft.blockPlexiglass.blockIndexInTexture;
  }

  public int getRenderBlockPass() {
    return 1;
  }

  public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
    return super.shouldSideBeRendered(iblockaccess, i, j, k, 1 - l);
  }

  public int getBlockTextureFromSideAndMetadata(int i, int data) {
  	if ((data & 0x8) == 8) return 3;
    return 4;
  }

  public int idDropped(int data, Random rand, int j) {
  	if ((data & 0x8) != 0) return 0;
    return mod_PlasticCraft.itemPlexidoor.shiftedIndex;
  }
  
  public String getTextureFile() {
		return mod_PlasticCraft.blockSheet;
	}
}