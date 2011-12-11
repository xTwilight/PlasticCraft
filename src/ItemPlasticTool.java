package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemPlasticTool extends ItemTool implements ITextureProvider {
	private static Block blocksEffectiveAgainst[];
	
	public ItemPlasticTool(int i, int type, EnumToolMaterial enumtoolmaterial) {
		super(i, type, enumtoolmaterial, blocksEffectiveAgainst);
		
		if (type == 1)
			blocksEffectiveAgainst = shovelBlocksEffectiveAgainst;
		if (type == 2)
			blocksEffectiveAgainst = pickaxeBlocksEffectiveAgainst;
		if (type == 3)
			blocksEffectiveAgainst = axeBlocksEffectiveAgainst;
	}
	
  public boolean canHarvestBlock(Block block) {
  	if (shiftedIndex == mod_PlasticCraft.toolPlasticPickaxe.shiftedIndex) {
      if (block == Block.obsidian)
        return toolMaterial.getHarvestLevel() == 3;
      if (block == Block.blockDiamond || block == Block.oreDiamond)
        return toolMaterial.getHarvestLevel() >= 2;
      if (block == Block.blockGold || block == Block.oreGold)
        return toolMaterial.getHarvestLevel() >= 2;
      if (block == Block.blockSteel || block == Block.oreIron)
        return toolMaterial.getHarvestLevel() >= 1;
      if (block == Block.blockLapis || block == Block.oreLapis)
        return toolMaterial.getHarvestLevel() >= 1;
      if (block == Block.oreRedstone || block == Block.oreRedstoneGlowing)
        return toolMaterial.getHarvestLevel() >= 2;
      if (block.blockMaterial == Material.rock)
        return true;
      
      return block.blockMaterial == Material.iron;
  	} 
  	
  	if (shiftedIndex == mod_PlasticCraft.toolPlasticShovel.shiftedIndex) {
  		if (block == Block.snow)
        return true;
  		
      return block == Block.blockSnow;
  	}
  	
  	return super.canHarvestBlock(block);
  }

  public float getStrVsBlock(ItemStack itemstack, Block block) {
  	if (shiftedIndex == mod_PlasticCraft.toolPlasticPickaxe.shiftedIndex)
      if (block != null && (block.blockMaterial == Material.iron || block.blockMaterial == Material.rock))
        return efficiencyOnProperMaterial;
    if (shiftedIndex == mod_PlasticCraft.toolPlasticAxe.shiftedIndex)
  	  if (block != null && block.blockMaterial == Material.wood)
        return efficiencyOnProperMaterial;
  	
  	return super.getStrVsBlock(itemstack, block);
  }
	
  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
  
  private static Block shovelBlocksEffectiveAgainst[] = (new Block[] {
    Block.grass, Block.dirt, Block.sand, Block.gravel, Block.snow, Block.blockSnow, Block.blockClay, Block.tilledField, Block.slowSand, Block.mycelium
  });
  	
  private static Block axeBlocksEffectiveAgainst[] = (new Block[] {
    Block.planks, Block.bookShelf, Block.wood, Block.chest, Block.stairDouble, Block.stairSingle, Block.pumpkin, Block.pumpkinLantern
  });
  	
  private static Block pickaxeBlocksEffectiveAgainst[] = (new Block[] {
    Block.cobblestone, Block.stairDouble, Block.stairSingle, Block.stone, Block.sandStone, Block.cobblestoneMossy, Block.oreIron, Block.blockSteel, Block.oreCoal, Block.blockGold, 
    Block.oreGold, Block.oreDiamond, Block.blockDiamond, Block.ice, Block.netherrack, Block.oreLapis, Block.blockLapis, Block.oreRedstone, Block.oreRedstoneGlowing, Block.rail, 
    Block.railDetector, Block.railPowered
  });
}