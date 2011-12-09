package net.minecraft.src;

import java.util.*;
import net.minecraft.client.Minecraft;

public class BlockTrampoline extends Block_PC {
	private static double expfac = 1.0800000000000001D;
  private static double initfac = 0.80000000000000004D;
  private static double horizDamp = 0.29999999999999999D;
  private static double bounceCap = 3.25D;
  public static int rubberIndex = 5;
  private static int trampolineSide = 6;
  private static int spr[] = new int[2];
  
  public BlockTrampoline(int i) {
    super(i, Material.cloth);
    setHardness(1.0F);
    setStepSound(soundClothFootstep);
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    setBlockName("pTrampoline");
    blockIndexInTexture = rubberIndex;
  }

  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
    if (entity.posY > (double)j)
      bounce(entity, i, j, k);
  }

  public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
    bounce(entity, i, j, k);
  }

  public void bounce(Entity entity, int i, int j, int k) {
    if (((entity instanceof EntityLiving) || (entity instanceof EntityItem) 
      || (entity instanceof EntityFallingSand) || (entity instanceof EntityTNTPrimed) 
      || (entity instanceof EntityC4Primed)) && entity.motionY < 2D && !entity.isSneaking()) {
      World world = entity.worldObj;
      int l = countAdjacent(world, i, j, k);
      double d = getBounceFactor(l);
      double d1;
            
      if ((entity instanceof EntityPlayer) && !mod_PlasticCraft.isWearingFallBoots)
        d1 = 1.0D;
      else if((entity instanceof EntityLiving) && !((EntityLiving)entity).isJumping)
        d1 = 1.3999999999999999D;
      else if((entity instanceof EntityFallingSand) || (entity instanceof EntityTNTPrimed) || (entity instanceof EntityC4Primed) || (entity instanceof EntityExplodeFX))
        d1 = 1.2D;
      else d1 = 2D;
            
      entity.motionY = Math.min(bounceCap, initfac * d1 * d);
      entity.fallDistance = -(float)getHeightForVelocity(entity.motionY);
      if (entity instanceof EntityFallingSand) {
        EntityFallingSand entityfallingsand = (EntityFallingSand)entity;
        entityfallingsand.fallTime = -(int)getTicksUntilRebound(entity.motionY);
        entityfallingsand.onGround = false;
      }
            
      entity.motionX *= horizDamp * getBounceFactor(l);
      entity.motionZ *= horizDamp * getBounceFactor(l);
    }
  }

  public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
    ItemStack itemstack = entityplayer.getCurrentEquippedItem();
        
    if (itemstack != null && itemstack.itemID == blockID)
      return false;
        
    int l = countAdjacent(world, i, j, k);
    String s = String.format("%d connected trampolines.", new Object[] { Integer.valueOf(l) });
        
    if(l != 1)
      ModLoader.getMinecraftInstance().ingameGUI.addChatMessage(s);
        
    return true;
  }

  public boolean canPlaceBlockAt(World world, int i, int j, int k) {
    if (!world.isBlockNormalCube(i, j - 1, k))
      return false;
    
    return super.canPlaceBlockAt(world, i, j, k);
  }

  public boolean canBlockStay(World world, int i, int j, int k) {
    if (!world.isBlockNormalCube(i, j - 1, k))
      return false;
    
    return super.canBlockStay(world, i, j, k);
  }

  public boolean isOpaqueCube() {
    return false;
  }

  public boolean renderAsNormalBlock() {
    return false;
  }

  public int quantityDropped(Random random) {
    return 1;
  }

  public int getBlockTextureFromSide(int i) {
    return i != 1 ? i != 0 ? trampolineSide : Block.wood.blockIndexInTexture : rubberIndex;
  }

  public double getBounceFactor(int i) {
    return Math.pow(expfac, i - 1);
  }

  public double getHeightAtTick(double d, double d1, double d2) {
    return d2 != 0.0D ? getHeightAtTick(d + d1, 0.97999999999999998D * (d1 - 0.080000000000000002D), d2 - 1.0D) : d;
  }

  public double getHeightForVelocity(double d) {
    double d1 = (-1.0D / 0.0D);
    double d2 = 0.0D;
    
    for (int i = 0; d2 >= d1; i++) {
      d2 += d;
      d -= 0.080000000000000002D;
      d *= 0.97999999999999998D;
            
      if (d2 > d1)
        d1 = d2;
    }

    return d1;
  }

  public double getTicksUntilRebound(double d) {
    double d1 = 0.0D;
    int i;
        
    for (i = 0; d1 >= 0.0D; i++) {
      d1 += d;
      d -= 0.040000000000000001D;
      d *= 0.97999999999999998D;
    }

    return (double)i;
  }

  public int countAdjacent(World world, int i, int j, int k) {
    ChunkCoordinates chunkcoordinates = new ChunkCoordinates(i, j, k);
    HashSet hashset = new HashSet();
    hashset.add(chunkcoordinates);
    countAdjacent(world, chunkcoordinates, ((Set) (hashset)));
    return hashset.size();
  }

  public void countAdjacent(World world, ChunkCoordinates chunkcoordinates, Set set) {
    int ai[][] = adjdiffs;
    int i = ai.length;
        
    for (int j = 0; j < i; j++) {
      int ai1[] = ai[j];
      int k = chunkcoordinates.posX + ai1[0];
      int l = chunkcoordinates.posY;
      int i1 = chunkcoordinates.posZ + ai1[1];
            
      if (world.getBlockId(k, l, i1) != blockID)
        continue;
            
      ChunkCoordinates chunkcoordinates1 = new ChunkCoordinates(k, l, i1);
      if (set.add(chunkcoordinates1))
        countAdjacent(world, chunkcoordinates1, set);
    }
  }

  public static int adjdiffs[][] = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
}