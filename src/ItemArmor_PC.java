package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemArmor_PC extends ItemArmor implements ITextureProvider {
  public ItemArmor_PC(int id, EnumArmorMaterial material, int texture, int type) {
    super(id, material, texture, type);
    setFull3D();
  }
	
  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
}