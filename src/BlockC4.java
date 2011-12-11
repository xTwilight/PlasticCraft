package net.minecraft.src;

import java.util.*;
import net.minecraft.client.Minecraft;

public class BlockC4 extends Block_PC {
  public static double logbase = 10D;
  
  public BlockC4(int i) {
    super(i, Material.tnt);
    setHardness(0.5F);
    setStepSound(soundGrassFootstep);
    setBlockName("pC4");
    blockIndexInTexture = 13;
  }

  public int getBlockTextureFromSide(int i) {
  	if (i == 0) return blockIndexInTexture + 2;
    if (i == 1) return blockIndexInTexture + 1;
    return blockIndexInTexture;
  }

  public void onBlockAdded(World world, int i, int j, int k) {
    super.onBlockAdded(world, i, j, k);
    
    if (world.isBlockIndirectlyGettingPowered(i, j, k)) {
      onBlockDestroyedByPlayer(world, i, j, k, 1);
      world.setBlockWithNotify(i, j, k, 0);
    }
  }

  public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
    if (l>0 && Block.blocksList[l].canProvidePower() && world.isBlockIndirectlyGettingPowered(i, j, k)) {
      onBlockDestroyedByPlayer(world, i, j, k, 1);
      world.setBlockWithNotify(i, j, k, 0);
    }
  }

  public int quantityDropped(Random random) {
    return 0;
  }

  public void onBlockDestroyedByExplosion(World world, int i, int j, int k) {
    EntityC4Primed entityc4primed = spawnC4(world, i, j, k);
    entityc4primed.fuse = world.rand.nextInt(entityc4primed.fuse / 4) + entityc4primed.fuse / 8;
  }

  public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l) {
    if (world.multiplayerWorld)
      return;
    else {
      spawnC4(world, i, j, k);
      return;
    }
  }

  public EntityC4Primed spawnC4(World world, int i, int j, int k) {
    EntityC4Primed entityc4primed = new EntityC4Primed(world, (double)i + 0.5D, (double)j + 0.5D, (double)k + 0.5D);
    
    if (mod_PlasticCraft.c4Enhanced) {
      entityc4primed.connectedCount = countAdjacent(world, i, j, k, true);
      entityc4primed.power = getPower(entityc4primed.connectedCount);
      entityc4primed.renderScale = (float)Math.pow(entityc4primed.connectedCount, 0.3333333432674408D);
      entityc4primed.height *= entityc4primed.renderScale;
      entityc4primed.width *= entityc4primed.renderScale;
      entityc4primed.yOffset = entityc4primed.height / 2.0F;
    }
    
    world.entityJoinedWorld(entityc4primed);
    world.playSoundAtEntity(entityc4primed, "random.fuse", 1.0F, 1.0F);

    return entityc4primed;
  }

  public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
    if (!mod_PlasticCraft.c4Enhanced)
      return false;
    
    ItemStack itemstack = entityplayer.getCurrentEquippedItem();
    if (itemstack != null && (itemstack.itemID == blockID || itemstack.itemID == mod_PlasticCraft.itemC4Defuser.shiftedIndex))
      return false;
    
    int l = countAdjacent(world, i, j, k, false);
    int i1 = getPower(l);
    String s = String.format("%d connected C4, power = %d.", new Object[] { Integer.valueOf(l), Integer.valueOf(i1) });
    if(l != 1) ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(s);
    
    return true;
  }

  public int getPower(int i) {
    double d = 4.1887902047863905D * Math.pow(mod_PlasticCraft.c4Power, 3D);
    d *= i;
    double d1 = (1.0D + Math.log(i) / Math.log(logbase)) * Math.pow((0.75D * d) / 3.1415926535897931D, 0.33333333333333331D);
    return (int)Math.round(d1);
  }

  public int countAdjacent(World world, int i, int j, int k, boolean flag) {
    ChunkCoordinates chunkcoordinates = new ChunkCoordinates(i, j, k);
    HashSet hashset = new HashSet();
    hashset.add(chunkcoordinates);
    countAdjacent(world, chunkcoordinates, ((Set) (hashset)), flag);
    return hashset.size();
  }

  public void countAdjacent(World world, ChunkCoordinates chunkcoordinates, Set set, boolean flag) {
    int ai[][] = adjdiffs;
    int i = ai.length;
    
    for (int j=0; j<i; j++) {
      int ai1[] = ai[j];
      int k = chunkcoordinates.posX + ai1[0];
      int l = chunkcoordinates.posY + ai1[1];
      int i1 = chunkcoordinates.posZ + ai1[2];
      if (world.getBlockId(k, l, i1) != blockID)
        continue;
      ChunkCoordinates chunkcoordinates1 = new ChunkCoordinates(k, l, i1);
      if (!set.add(chunkcoordinates1))
        continue;
      if (flag)
        world.setBlockWithNotify(k, l, i1, 0);
      
      countAdjacent(world, chunkcoordinates1, set, flag);
    }
  }
  
  public static int adjdiffs[][] = { {1, 0, 0}, {-1, 0, 0}, {0, 0, 1}, {0, 0, -1}, {0, 1, 0}, {0, -1, 0} };
}