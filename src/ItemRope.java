package net.minecraft.src;

public class ItemRope extends Item_PC {
  public ItemRope(int i) {
    super(i);
  }

  public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
  	boolean ropeFall = true;
    int i1 = 2;
 
    if (world.getBlockId(i, j - 1, k) == 0 && world.getBlockId(i, j - 1, k) != mod_PlasticCraft.blockRope.blockID) {
      if (world.getBlockId(i, j, k) == mod_PlasticCraft.blockRope.blockID)
      	world.setBlockAndMetadataWithNotify(i, j - 1, k, mod_PlasticCraft.blockRope.blockID, 1);
      else world.setBlockWithNotify(i, j - 1, k, mod_PlasticCraft.blockRope.blockID);
      
      itemstack.stackSize--;
      
      while (ropeFall) {
        if (world.getBlockId(i, j - i1, k) == 0) {
          world.setBlockAndMetadataWithNotify(i, j - i1, k, mod_PlasticCraft.blockRope.blockID, 1);
          i1++;
        } else
        	ropeFall = false;
      }
    }
    
    return false;
  }
}