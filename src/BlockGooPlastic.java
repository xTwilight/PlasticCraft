package net.minecraft.src;

import java.util.Random;

public class BlockGooPlastic extends Block_PC {
  public BlockGooPlastic(int i) {
    super(i, Material.sand);
    setHardness(0.5F);
    setResistance(500F);
    setStepSound(soundClothFootstep);
    setBlockName("pGooPlasticBlock");
    blockIndexInTexture = 11;
    setTickOnLoad(true);
  }

  public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k) {
    float f = 0.0625F;
    return AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, (float)(j + 1) - f, (float)(k + 1) - f);
  }

  public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k) {
    float f = 0.0625F;
    return AxisAlignedBB.getBoundingBoxFromPool((float)i + f, j, (float)k + f, (float)(i + 1) - f, j + 1, (float)(k + 1) - f);
  }

  public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
    if (l == Block.waterMoving.blockID || l == Block.waterStill.blockID) {
      int i1 = colors[world.rand.nextInt(colors.length)];
      world.setBlockAndMetadataWithNotify(i, j, k, mod_PlasticCraft.blockPlastic.blockID, i1);
    }
  }

  public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
    freeze(entity);
  }

  public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity) {
    entity.setInWeb();
//    entity.motionX *= 0.40000000000000002D;
//    entity.motionZ *= 0.40000000000000002D;
    freeze(entity);
  }

  public void freeze(Entity entity) {
    if (!mod_PlasticCraft.isWearingFallBoots)
      entity.attackEntityFrom(DamageSource.cactus, 1);
    if (entity instanceof EntityLiving) {
      EntityLiving entityliving = (EntityLiving)entity;
      mod_PlasticCraft.Stun.shockMob(entityliving, 6D, 4D);
    }
  }

  public int tickRate() {
    return 30;
  }

  public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
    produceParticles(world, i, j, k);
    return super.blockActivated(world, i, j, k, entityplayer);
  }

  public void randomDisplayTick(World world, int i, int j, int k, Random random) {
    produceParticles(world, i, j, k);
  }

  private void produceParticles(World world, int i, int j, int k) {
    double d = 0.0625D;
    
    for (int l=0; l<6; l++) {
      double d1 = (float)i + world.rand.nextFloat();
      double d2 = (float)j + world.rand.nextFloat();
      double d3 = (float)k + world.rand.nextFloat();
            
      if (l == 0 && !world.isBlockOpaqueCube(i, j + 1, k))
        d2 = (double)(j + 1) + d;
      if (l == 1 && !world.isBlockOpaqueCube(i, j - 1, k))
        d2 = (double)(j + 0) - d;
      if (l == 2 && !world.isBlockOpaqueCube(i, j, k + 1))
        d3 = (double)(k + 1) + d;
      if (l == 3 && !world.isBlockOpaqueCube(i, j, k - 1))
        d3 = (double)(k + 0) - d;
      if (l == 4 && !world.isBlockOpaqueCube(i + 1, j, k))
        d1 = (double)(i + 1) + d;
      if (l == 5 && !world.isBlockOpaqueCube(i - 1, j, k))
        d1 = (double)(i + 0) - d;
      if (d1 < (double)i || d1 > (double)(i + 1) || d2 < 0.0D || d2 > (double)(j + 1) || d3 < (double)k || d3 > (double)(k + 1))
        world.spawnParticle("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
    }
  }

  public int quantityDropped(Random random) {
    return 1;
  }

  public static int colors[] = { 0, 1, 4, 7, 14, 15 };
}