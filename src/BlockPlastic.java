package net.minecraft.src;

import java.util.Random;

public class BlockPlastic extends Block_PC {
  public BlockPlastic(int i) {
    super(i, Material.sponge);
    setHardness(1.5F);
    setResistance(1500F);
    setStepSound(soundWoodFootstep);
    blockIndexInTexture = 1;
    setBlockName("pPlasticBlock");
  }

  public int getRenderColor(int meta) {
  	if (meta == 0) return 0xFFFFFFFF; // white
    if (meta == 1) return 0xFFFF6600; // orange
    if (meta == 2) return 0xFFBA34F2; // magenta
    if (meta == 3) return 0xFF1E8DFF; // light blue
    if (meta == 4) return 0xFFFFFF33; // yellow
    if (meta == 5) return 0xFFB3FF46; // lime
    if (meta == 6) return 0xFFfD9FF3; // pink
    if (meta == 7) return 0xFF9C9C9C; // gray
    if (meta == 8) return 0xFF398DA6; // teal
    if (meta == 9) return 0xFF00E4FF; // cyan
    if (meta == 10) return 0xFF7F00FF; // purple
    if (meta == 11) return 0xFF0000FF; // blue
    if (meta == 12) return 0xFF793F1C; // brown
    if (meta == 13) return 0xFF28AF28; // green
    if (meta == 14) return 0xFFFF0000; // red
    if (meta == 15) return 0xFF2D2D2D; // black
    return 0xFFFFFFFF; // white
  }

  public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k) {
    int meta = iblockaccess.getBlockMetadata(i, j, k);
    if (meta == 0) return 0xFFFFFFFF; // white
    if (meta == 1) return 0xFFFF6600; // orange
    if (meta == 2) return 0xFFBA34F2; // magenta
    if (meta == 3) return 0xFF1E8DFF; // light blue
    if (meta == 4) return 0xFFFFFF33; // yellow
    if (meta == 5) return 0xFFB3FF46; // lime
    if (meta == 6) return 0xFFfD9FF3; // pink
    if (meta == 7) return 0xFF9C9C9C; // gray
    if (meta == 8) return 0xFF398DA6; // teal
    if (meta == 9) return 0xFF00E4FF; // cyan
    if (meta == 10) return 0xFF7F00FF; // purple
    if (meta == 11) return 0xFF0000FF; // blue
    if (meta == 12) return 0xFF793F1C; // brown
    if (meta == 13) return 0xFF28AF28; // green
    if (meta == 14) return 0xFFFF0000; // red
    if (meta == 15) return 0xFF2D2D2D; // black
    return 0xFFFFFFFF; // white
  }

  public void onNeighborBlockChange(World world, int i, int j, int k, int l) {
    if (l == Block.fire.blockID || l == Block.lavaMoving.blockID || l == Block.lavaStill.blockID)
      world.setBlockWithNotify(i, j, k, mod_PlasticCraft.blockPlasticGoo.blockID);
  }

  public int quantityDropped(Random random) {
    return 1;
  }

  protected int damageDropped(int i) {
    return i;
  }

  public static void recipes() {
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 0), new Object[] {
      new ItemStack(Item.dyePowder, 1, 15), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 1), new Object[] {
      new ItemStack(Item.dyePowder, 1, 14), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 2), new Object[] {
      new ItemStack(Item.dyePowder, 1, 13), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 3), new Object[] {
      new ItemStack(Item.dyePowder, 1, 12), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 4), new Object[] {
      new ItemStack(Item.dyePowder, 1, 11), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 5), new Object[] {
      new ItemStack(Item.dyePowder, 1, 10), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 6), new Object[] {
      new ItemStack(Item.dyePowder, 1, 9), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 7), new Object[] {
      new ItemStack(Item.dyePowder, 1, 8), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 8), new Object[] {
      new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.dyePowder, 1, 6), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 9), new Object[] {
      new ItemStack(Item.dyePowder, 1, 6), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 10), new Object[] {
      new ItemStack(Item.dyePowder, 1, 5), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 11), new Object[] {
      new ItemStack(Item.dyePowder, 1, 4), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 12), new Object[] {
      new ItemStack(Item.dyePowder, 1, 3), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 13), new Object[] {
      new ItemStack(Item.dyePowder, 1, 2), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 14), new Object[] {
      new ItemStack(Item.dyePowder, 1, 1), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
    ModLoader.AddShapelessRecipe(new ItemStack(mod_PlasticCraft.blockPlastic, 1, 15), new Object[] {
      new ItemStack(Item.dyePowder, 1, 0), new ItemStack(Item.itemsList[mod_PlasticCraft.blockPlastic.blockID], 1, 0)
    });
  }
}