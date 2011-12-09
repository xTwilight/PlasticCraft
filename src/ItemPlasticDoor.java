package net.minecraft.src;

public class ItemPlasticDoor extends Item_PC {
  public ItemPlasticDoor(int i) {
    super(i);
    maxStackSize = 1;
  }

  public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
    if(l != 1) return false;
    j++;
    Block block = mod_PlasticCraft.blockPlexidoor;
    if(!block.canPlaceBlockAt(world, i, j, k)) return false;
        
    int i1 = MathHelper.floor_double((double)(((entityplayer.rotationYaw + 180F) * 4F) / 360F) - 0.5D) & 3;
    byte byte0 = 0;
    byte byte1 = 0;
    if (i1 == 0) byte1 = 1;
    if (i1 == 1) byte0 = -1;
    if (i1 == 2) byte1 = -1;
    if (i1 == 3) byte0 = 1;
    
    int j1 = (world.isBlockNormalCube(i - byte0, j, k - byte1) ? 1 : 0) + (world.isBlockNormalCube(i - byte0, j + 1, k - byte1) ? 1 : 0);
    int k1 = (world.isBlockNormalCube(i + byte0, j, k + byte1) ? 1 : 0) + (world.isBlockNormalCube(i + byte0, j + 1, k + byte1) ? 1 : 0);
    boolean flag = world.getBlockId(i - byte0, j, k - byte1) == block.blockID || world.getBlockId(i - byte0, j + 1, k - byte1) == block.blockID;
    boolean flag1 = world.getBlockId(i + byte0, j, k + byte1) == block.blockID || world.getBlockId(i + byte0, j + 1, k + byte1) == block.blockID;
    boolean flag2 = false;
        
    if (flag && !flag1) flag2 = true;
    else if (k1 > j1) flag2 = true;
    if (flag2) {
      i1 = i1 - 1 & 3;
      i1 += 4;
    }
    
    world.editingBlocks = true;
    world.setBlockAndMetadataWithNotify(i, j, k, block.blockID, i1);
    world.setBlockAndMetadataWithNotify(i, j + 1, k, block.blockID, i1 + 8);
    world.editingBlocks = false;
    world.notifyBlocksOfNeighborChange(i, j, k, block.blockID);
    world.notifyBlocksOfNeighborChange(i, j + 1, k, block.blockID);
    itemstack.stackSize--;
    return true;
  }
}
