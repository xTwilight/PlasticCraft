package net.minecraft.src;

import net.minecraft.src.forge.ITextureProvider;

public class ItemConsumable extends ItemFood_PC {
  public ItemConsumable(int i, int j, float k, boolean flag) {
    super(i, j, k, flag);
    maxStackSize = 1;
  }
  
  public ItemStack onFoodEaten(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    entityplayer.getFoodStats().addStatsFrom(this);
    world.playSoundAtEntity(entityplayer, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
    
    if (itemstack != null && itemstack.itemID == mod_PlasticCraft.itemPlasticBottleW.shiftedIndex)
      return new ItemStack(mod_PlasticCraft.itemPlasticBottle);
    if (itemstack != null && itemstack.itemID == mod_PlasticCraft.itemPlasticBottleM.shiftedIndex)
      return new ItemStack(mod_PlasticCraft.itemPlasticBottle);
    
    return itemstack;
  }
  
  public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer player) {
    if (itemstack != null && itemstack.itemID == mod_PlasticCraft.itemNeedleHealth.shiftedIndex && player.getEntityHealth() < player.getMaxHealth()) {
      player.heal(15);
      return new ItemStack(mod_PlasticCraft.itemNeedle);
    }
    
    return super.onItemRightClick(itemstack, world, player);
  }
  
  public EnumAction getItemUseAction(ItemStack itemstack) {
    return EnumAction.drink;
  }
}