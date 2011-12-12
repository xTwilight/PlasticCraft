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
    addSmelting(Block.oreCoal.blockID, new ItemStack(Item.coal));
    addSmelting(Block.oreIron.blockID, new ItemStack(Item.ingotIron));
    addSmelting(Block.oreGold.blockID, new ItemStack(Item.ingotGold));
    addSmelting(Block.oreDiamond.blockID, new ItemStack(Item.diamond));
    addSmelting(Block.oreRedstone.blockID, new ItemStack(Item.redstone));
    addSmelting(Block.oreLapis.blockID, new ItemStack(Item.dyePowder, 1, 4));
    addSmelting(Block.sand.blockID, new ItemStack(Block.glass));
    addSmelting(Item.porkRaw.shiftedIndex, new ItemStack(Item.porkCooked));
    addSmelting(Item.beefRaw.shiftedIndex, new ItemStack(Item.beefCooked));
    addSmelting(Item.chickenRaw.shiftedIndex, new ItemStack(Item.chickenCooked));
    addSmelting(Item.fishRaw.shiftedIndex, new ItemStack(Item.fishCooked));
    addSmelting(Block.cobblestone.blockID, new ItemStack(Block.stone));
    addSmelting(Item.clay.shiftedIndex, new ItemStack(Item.brick));
    addSmelting(Block.cactus.blockID, new ItemStack(Item.dyePowder, 1, 2));
    addSmelting(Block.wood.blockID, new ItemStack(Item.coal, 1, 1));
    
    addExtraction(Block.oreCoal.blockID, new ItemStack(Block.stone));
    addExtraction(Block.oreIron.blockID, new ItemStack(Block.stone));
    addExtraction(Block.oreGold.blockID, new ItemStack(Block.stone));
    addExtraction(Block.oreDiamond.blockID, new ItemStack(Block.stone));
    addExtraction(Block.oreRedstone.blockID, new ItemStack(Block.stone));
    addExtraction(Block.oreLapis.blockID, new ItemStack(Block.stone));
    addExtraction(Item.porkRaw.shiftedIndex, new ItemStack(mod_PlasticCraft.itemGelatin, 2));
    addExtraction(Item.beefRaw.shiftedIndex, new ItemStack(mod_PlasticCraft.itemGelatin, 2));
    addExtraction(Item.chickenRaw.shiftedIndex, new ItemStack(mod_PlasticCraft.itemGelatin, 2));
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