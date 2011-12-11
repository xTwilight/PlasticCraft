package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemArmor_PC extends ItemArmor implements ITextureProvider {
	boolean isKevlar;
	
  public ItemArmor_PC(int id, EnumArmorMaterial material, int texture, int type, boolean kevlar) {
    super(id, material, texture, type);
    setFull3D();
    isKevlar = kevlar;
  }
	
  public void onCreated(ItemStack itemstack, World world, EntityPlayer player) {
  	if (isKevlar)
  	  itemstack.addEnchantment(Enchantment.projectileProtection, 2);
  }
  
  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
}