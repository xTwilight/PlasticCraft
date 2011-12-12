package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemFood_PC extends ItemFood implements ITextureProvider {
  public ItemFood_PC(int i, int j, float f, boolean flag) {
    super(i, j, f, flag);
  }

  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
}