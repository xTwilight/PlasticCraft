package net.minecraft.src;

public class ItemBlockPlexiglass extends ItemBlock {
  public ItemBlockPlexiglass(int i) {
    super(i);
    setMaxDamage(0);
    setHasSubtypes(true);
    setItemName("pPlexiglass");
  }

  public int getPlacedBlockMetadata(int i) {
    return i;
  }
  
  public String getItemNameIS(ItemStack itemstack) {
  	if(itemstack.getItemDamage() == 1)
      return "glowplexiglass";
    
  	return "plexiglass";
  }
}