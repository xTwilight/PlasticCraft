package net.minecraft.src;

import java.util.Random;
import net.minecraft.src.forge.ITextureProvider;

public class BlockMicrowave extends BlockContainer implements ITextureProvider {
  public static int plasticIndex = 0;
  private static int microwaveFront = 8;
  public static int microwaveAnim = 255;
  private static int spr[] = new int[3];
  private final boolean isCooking;
  private static boolean keepMicrowaveInventory = false;
  
  public BlockMicrowave(int i, boolean flag) {
    super(i, Material.sponge);
    setHardness(2.0F);
    setResistance(1500F);
    setStepSound(soundPowderFootstep);
    setBlockName("pMicrowave");
    blockIndexInTexture = plasticIndex;
    isCooking = flag;
  }

  public int idDropped(int i, Random random) {
    return mod_PlasticCraft.blockMicrowaveIdle.blockID;
  }

  public void onBlockAdded(World world, int i, int j, int k) {
    super.onBlockAdded(world, i, j, k);
    setDefaultDirection(world, i, j, k);
  }

  private void setDefaultDirection(World world, int i, int j, int k) {
    if (world.multiplayerWorld)
      return;
      
    int l = world.getBlockId(i, j, k - 1);
    int i1 = world.getBlockId(i, j, k + 1);
    int j1 = world.getBlockId(i - 1, j, k);
    int k1 = world.getBlockId(i + 1, j, k);
    byte byte0 = 3;
          
    if (Block.opaqueCubeLookup[l] && !Block.opaqueCubeLookup[i1])
      byte0 = 3;
    if (Block.opaqueCubeLookup[i1] && !Block.opaqueCubeLookup[l])
      byte0 = 2;
    if (Block.opaqueCubeLookup[j1] && !Block.opaqueCubeLookup[k1])
      byte0 = 5;
    if (Block.opaqueCubeLookup[k1] && !Block.opaqueCubeLookup[j1])
      byte0 = 4;
      
    world.setBlockMetadataWithNotify(i, j, k, byte0);
  }

  public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l) {
    if (l == 1) return plasticIndex;
    if (l == 0) return plasticIndex;
    int i1 = iblockaccess.getBlockMetadata(i, j, k);
    if (l != i1) return plasticIndex;
    
    if (isCooking) return microwaveAnim;
      
    return microwaveFront;
  }

  public int getBlockTextureFromSide(int i) {
    if (i == 0) return plasticIndex;
    if (i == 1) return plasticIndex;
    if (i == 3) return microwaveFront;
    
    return plasticIndex;
  }

  public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer) {
    if (world.multiplayerWorld)
      return true;
    else {
      TileEntityMicrowave tileentity = (TileEntityMicrowave)world.getBlockTileEntity(i, j, k);
      ModLoader.OpenGUI(entityplayer, new GuiMicrowave(entityplayer.inventory, tileentity));
       return true;
    }
  }

  public static void updateFurnaceBlockState(boolean flag, World world, int i, int j, int k) {
    int l = world.getBlockMetadata(i, j, k);
    TileEntity tileentity = world.getBlockTileEntity(i, j, k);
    keepMicrowaveInventory = true;
        
    if (flag)
      world.setBlockWithNotify(i, j, k, mod_PlasticCraft.blockMicrowaveOn.blockID);
    else
      world.setBlockWithNotify(i, j, k, mod_PlasticCraft.blockMicrowaveIdle.blockID);
        
    keepMicrowaveInventory = false;
        
    world.setBlockMetadataWithNotify(i, j, k, l);
        
    if (tileentity != null) {
      tileentity.validate();
      world.setBlockTileEntity(i, j, k, tileentity);
    }
  }

  public TileEntity getBlockEntity() {
    return new TileEntityMicrowave();
  }

  public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving) {
    int l = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
    if (l == 0) world.setBlockMetadataWithNotify(i, j, k, 2);
    if (l == 1) world.setBlockMetadataWithNotify(i, j, k, 5);
    if (l == 2) world.setBlockMetadataWithNotify(i, j, k, 3);
    if (l == 3) world.setBlockMetadataWithNotify(i, j, k, 4);
  }

  public void onBlockRemoval(World world, int i, int j, int k) {
    if(!keepMicrowaveInventory) {
      TileEntityMicrowave tileentitymicrowave = (TileEntityMicrowave)world.getBlockTileEntity(i, j, k);
      label0:
      for (int l=0; l<tileentitymicrowave.getSizeInventory(); l++) {
        ItemStack itemstack = tileentitymicrowave.getStackInSlot(l);
        if(itemstack == null)
          continue;
        
        float f = world.rand.nextFloat() * 0.8F + 0.1F;
        float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
        float f2 = world.rand.nextFloat() * 0.8F + 0.1F;
        
        do {
          if (itemstack.stackSize <= 0)
            continue label0;
                    
          int i1 = world.rand.nextInt(21) + 10;
          if(i1 > itemstack.stackSize)
            i1 = itemstack.stackSize;
                    
          itemstack.stackSize -= i1;
          EntityItem entityitem = new EntityItem(world, (float)i + f, (float)j + f1, (float)k + f2, new ItemStack(itemstack.itemID, i1, itemstack.getItemDamage()));
          float f3 = 0.05F;
          entityitem.motionX = (float)world.rand.nextGaussian() * f3;
          entityitem.motionY = (float)world.rand.nextGaussian() * f3 + 0.2F;
          entityitem.motionZ = (float)world.rand.nextGaussian() * f3;
          world.entityJoinedWorld(entityitem);
        } while(true);
      }
    }
    
    super.onBlockRemoval(world, i, j, k);
  }
  
  public String getTextureFile() {
    return mod_PlasticCraft.blockSheet;
  }
}