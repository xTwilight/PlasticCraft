package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class Item_PC extends Item implements ITextureProvider {
  public Item_PC(int i) {
  	super(i);
  }

	public String getTextureFile() {
		return mod_PlasticCraft.itemSheet;
	}
}