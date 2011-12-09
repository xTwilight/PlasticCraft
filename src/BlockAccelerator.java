package net.minecraft.src;

import java.util.Random;
import net.minecraft.client.Minecraft;

public class BlockAccelerator extends Block_PC {
  public BlockAccelerator(int i) {
    super(i, Material.sponge);
    setHardness(1.0F);
    setStepSound(soundClothFootstep);
    setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.25F, 1.0F);
    setBlockName("pAccelerator");
    blockIndexInTexture = 7;
  }

  public void onEntityWalking(World world, int i, int j, int k, Entity entity) {
    EntityPlayerSP entityplayersp = ModLoader.getMinecraftInstance().thePlayer;
    double d = Math.abs(entity.motionX);
    double d1 = Math.abs(entity.motionZ);
    
    if ((entity instanceof EntityPlayer) && !entity.isSneaking() && !((EntityPlayer) (entityplayersp)).isJumping) {
      if (d < 0.29999999999999999D)
        entity.motionX *= 3.1000000000000001D;
      if(d1 < 0.29999999999999999D)
        entity.motionZ *= 3.1000000000000001D;
    }
        
    if ((entity instanceof EntityMob) || (entity instanceof EntityCow)) {
      if (d < 0.29999999999999999D)
        entity.motionX *= 2.7999999999999998D;
      if (d1 < 0.29999999999999999D)
        entity.motionZ *= 2.7999999999999998D;
    }
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
}