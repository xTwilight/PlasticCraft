package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemConsumable extends ItemFood implements ITextureProvider {
  boolean isFood;
	
  public ItemConsumable(int i, int j, float k, boolean flag, boolean food) {
    super(i, j, k, flag);
    maxStackSize = 1;
    isFood = food;
  }

  public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer player, int i) {
    if (itemstack != null && itemstack.itemID == mod_PlasticCraft.itemPlasticBottleW.shiftedIndex)
      return new ItemStack(mod_PlasticCraft.itemPlasticBottle);
    if (itemstack != null && itemstack.itemID == mod_PlasticCraft.itemPlasticBottleM.shiftedIndex)
      return new ItemStack(mod_PlasticCraft.itemPlasticBottle);
    
    return super.onFoodEaten(itemstack, world, player);
  }
  
  public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
    if (itemstack != null && itemstack.itemID == mod_PlasticCraft.itemNeedleHealth.shiftedIndex && player.getEntityHealth() < player.getMaxHealth()) {
      player.heal(15);
      return new ItemStack(mod_PlasticCraft.itemNeedle);
    }
    
    return super.onItemRightClick(itemstack, world, player);
  }
  
  public EnumAction getItemUseAction(ItemStack itemstack) {
  	if (!isFood)
      return EnumAction.drink;
  	
  	return EnumAction.eat;
  }
  
  public String getTextureFile() {
    return mod_PlasticCraft.itemSheet;
  }
}