package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemPlasticAxe extends ItemTool implements ITextureProvider {
  private static Block blocksEffectiveAgainst[] = new Block[] {
    Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stairDouble, Block.stairSingle, Block.pumpkin, Block.pumpkinLantern
  };

  public ItemPlasticAxe(int i, EnumToolMaterial enumtoolmaterial) {
    super(i, 3, enumtoolmaterial, blocksEffectiveAgainst);
  }

  public float getStrVsBlock(ItemStack itemstack, Block block) {
    if (block != null && block.blockMaterial == Material.wood)
      return efficiencyOnProperMaterial;
    
    return super.getStrVsBlock(itemstack, block);
  }
  
  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
}