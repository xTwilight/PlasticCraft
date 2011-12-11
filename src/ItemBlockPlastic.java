package net.minecraft.src;

public class ItemBlockPlastic extends ItemBlock {
  public ItemBlockPlastic(int i) {
    super(i);
    setMaxDamage(0);
    setHasSubtypes(true);
    setItemName("pPlasticBlock");
  }

  public int getPlacedBlockMetadata(int i) {
    return i;
  }

  public String getItemNameIS(ItemStack itemstack) {
    if (itemstack.getItemDamage() == 1) return "plastic.orange";
    else if (itemstack.getItemDamage() == 2) return "plastic.magenta";
    else if (itemstack.getItemDamage() == 3) return "plastic.lblue";
    else if (itemstack.getItemDamage() == 4) return "plastic.yellow";
    else if (itemstack.getItemDamage() == 5) return "plastic.lime";
    else if (itemstack.getItemDamage() == 6) return "plastic.pink";
    else if (itemstack.getItemDamage() == 7) return "plastic.gray";
    else if (itemstack.getItemDamage() == 8) return "plastic.teal";
    else if (itemstack.getItemDamage() == 9) return "plastic.cyan";
    else if (itemstack.getItemDamage() == 10) return "plastic.purple";
    else if (itemstack.getItemDamage() == 11) return "plastic.blue";
    else if (itemstack.getItemDamage() == 12) return "plastic.brown";
    else if (itemstack.getItemDamage() == 13) return "plastic.green";
    else if (itemstack.getItemDamage() == 14) return "plastic.red";
    else if (itemstack.getItemDamage() == 15) return "plastic.black";
    return "plastic.plain";
  }
}