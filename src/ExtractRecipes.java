package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public class ExtractRecipes {
  private static final ExtractRecipes smeltingBase = new ExtractRecipes();
  private Map smeltingList;
  private Map extractionList;
  
  private ExtractRecipes() {
    smeltingList = new HashMap();
    extractionList = new HashMap();
    addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
    addExtraction(Block.oreIron.blockID, new ItemStack(Block.stone));
    addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
    addExtraction(Block.oreGold.blockID, new ItemStack(Block.stone));
    addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));
    addExtraction(Block.oreDiamond.blockID, new ItemStack(Block.stone));
    addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
    addSmelting(Item.porkRaw.shiftedIndex, new ItemStack(Item.porkCooked));
    addExtraction(Item.porkRaw.shiftedIndex, new ItemStack(mod_PlasticCraft.itemGelatin, 3));
    addSmelting(Item.fishRaw.shiftedIndex, new ItemStack(Item.fishCooked));
    addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
    addSmelting(Item.clay.shiftedIndex, new ItemStack(Item.brick));
    addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2));
    addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
    addExtraction(Block.wood.blockID, new ItemStack(mod_PlasticCraft.itemWoodDust, 3));
  }

  public static ExtractRecipes smelting() {
    return smeltingBase;
  }

  public final void addSmelting(int i, ItemStack itemstack) {
    smeltingList.put(Integer.valueOf(i), itemstack);
  }

  public final void addExtraction(int i, ItemStack itemstack) {
    extractionList.put(Integer.valueOf(i), itemstack);
  }

  public ItemStack getSmeltingResult(int i) {
    return (ItemStack)smeltingList.get(Integer.valueOf(i));
  }

  public ItemStack getExtractionResult(int i) {
    return (ItemStack)extractionList.get(Integer.valueOf(i));
  }

  public Map getSmeltingList() {
    return smeltingList;
  }

  public Map getExtractionList() {
    return extractionList;
  }
}