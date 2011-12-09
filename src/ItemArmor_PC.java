package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemArmor_PC extends ItemArmor implements ITextureProvider {
  private static final int maxDamageArray[] = { 11, 16, 15, 13 };
  public final int armorType;
  public final int damageReduceAmount;
  public final int renderIndex;
  private final EnumArmorMaterial material;
	
  public ItemArmor_PC(int i, int j, int k) {
    super(i, EnumArmorMaterial.CLOTH, j, k);
    material = EnumArmorMaterial.CLOTH;
    armorType = k;
    renderIndex = j;
    damageReduceAmount = 0;
    setMaxDamage(0);
    maxStackSize = 1;
    setFull3D();
  }
	
  public int getItemEnchantability() {
    return material.getEnchantability();
  }
	
  static int[] func_40436_c() {
    return maxDamageArray;
  }
	
  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
}