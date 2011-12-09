package net.minecraft.src;

import java.util.List;
import net.minecraft.client.Minecraft;

public class ItemC4Defuser extends Item_PC {
  public ItemC4Defuser(int i) {
    super(i);
    maxStackSize = 1;
    setMaxDamage(32);
  }

  public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer) {
    List list = world.getLoadedEntityList();
    int i = 0;
    
    for (int j=0; j<list.size(); j++) {
      Entity entity = (Entity)list.get(j);
      
      if (!(entity instanceof EntityC4Primed))
        continue;
      
      EntityC4Primed entityc4primed = (EntityC4Primed)entity;
      entityc4primed.setEntityDead();
      int k = entityc4primed.connectedCount;
      int l = mod_PlasticCraft.blockC4.tickRate();
      int i1;
      
      for(; k > 0; k -= i1) {
        i1 = k >= l ? l : k;
        entityplayer.dropItem(mod_PlasticCraft.blockC4.blockID, i1);
      }

      i += entityc4primed.connectedCount;
    }

    String s = String.format("Defused %d active C4.", new Object[] { Integer.valueOf(i) });
        
    if (i > 0) {
      ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(s);
      itemstack.damageItem(1, entityplayer);
    }
    
    return itemstack;
  }

  public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l) {
    if (world.getBlockId(i, j, k) == mod_PlasticCraft.blockC4.blockID) {
      entityplayer.dropItem(mod_PlasticCraft.blockC4.blockID, 1);
      world.setBlockAndMetadataWithNotify(i, j, k, 0, 0);
      itemstack.damageItem(1, entityplayer);
      return true;
    } else if(world.getBlockId(i, j, k) == Block.tnt.blockID) {
      entityplayer.dropItem(Block.tnt.blockID, 1);
      world.setBlockAndMetadataWithNotify(i, j, k, 0, 0);
      itemstack.damageItem(1, entityplayer);
      return true;
    }
    
    return false;
  }
}