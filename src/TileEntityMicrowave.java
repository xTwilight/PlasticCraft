package net.minecraft.src;

public class TileEntityMicrowave extends TileEntity implements IInventory {
  private ItemStack microwaveItemStacks[];
  public int microwaveBurnTime;
  public int currentItemBurnTime;
  public int microwaveCookTime;
  public int cookTime;
  
  public TileEntityMicrowave() {
    microwaveItemStacks = new ItemStack[3];
    microwaveBurnTime = 0;
    currentItemBurnTime = 0;
    microwaveCookTime = 0;
    cookTime = 120;
  }

  public int getSizeInventory() {
    return microwaveItemStacks.length;
  }

  public ItemStack getStackInSlot(int i) {
    return microwaveItemStacks[i];
  }

  public ItemStack decrStackSize(int i, int j) {
    if (microwaveItemStacks[i] != null) {
      if (microwaveItemStacks[i].stackSize <= j) {
        ItemStack itemstack = microwaveItemStacks[i];
        microwaveItemStacks[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = microwaveItemStacks[i].splitStack(j);
      if(microwaveItemStacks[i].stackSize == 0)
        microwaveItemStacks[i] = null;
      
      return itemstack1;
    } else return null;
  }

  public void setInventorySlotContents(int i, ItemStack itemstack) {
    microwaveItemStacks[i] = itemstack;
    
    if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
      itemstack.stackSize = getInventoryStackLimit();
  }

  public String getInvName() {
    return "Microwave";
  }

  public void readFromNBT(NBTTagCompound nbttagcompound) {
    super.readFromNBT(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
    microwaveItemStacks = new ItemStack[getSizeInventory()];
        
    for (int i=0; i<nbttaglist.tagCount(); i++) {
      NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
      byte byte0 = nbttagcompound1.getByte("Slot");
      
      if (byte0 >= 0 && byte0 < microwaveItemStacks.length)
        microwaveItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
    }

    microwaveBurnTime = nbttagcompound.getShort("BurnTime");
    microwaveCookTime = nbttagcompound.getShort("CookTime");
    currentItemBurnTime = fuel(microwaveItemStacks[1]);
  }

  public void writeToNBT(NBTTagCompound nbttagcompound) {
    super.writeToNBT(nbttagcompound);
    nbttagcompound.setShort("BurnTime", (short)microwaveBurnTime);
    nbttagcompound.setShort("CookTime", (short)microwaveCookTime);
    NBTTagList nbttaglist = new NBTTagList();
        
    for (int i=0; i<microwaveItemStacks.length; i++) {
      if (microwaveItemStacks[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte)i);
        microwaveItemStacks[i].writeToNBT(nbttagcompound1);
        nbttaglist.setTag(nbttagcompound1);
      }
    }

    nbttagcompound.setTag("Items", nbttaglist);
  }

  public int getInventoryStackLimit() {
    return 64;
  }

  public int getCookProgressScaled(int i) {
    return (microwaveCookTime * i) / cookTime;
  }

  public int getBurnTimeRemainingScaled(int i) {
    if (currentItemBurnTime == 0)
      currentItemBurnTime = cookTime;
    
    return (microwaveBurnTime * i) / currentItemBurnTime;
  }

  public boolean isBurning() {
    return microwaveBurnTime > 0;
  }

  public void updateEntity() {
    boolean flag = microwaveBurnTime > 0;
    boolean flag1 = false;
        
    if (microwaveBurnTime > 0 && canSmelt())
      microwaveBurnTime--;
    if (!worldObj.multiplayerWorld) {
      if (microwaveBurnTime == 0 && canSmelt()) {
        currentItemBurnTime = microwaveBurnTime = fuel(microwaveItemStacks[1]);
        if (microwaveBurnTime > 0) {
          flag1 = true;
          if (microwaveItemStacks[1] != null) {
            if (microwaveItemStacks[1].getItem().hasContainerItem()) {
              microwaveItemStacks[1] = new ItemStack(microwaveItemStacks[1].getItem().getContainerItem());
            } else {
              microwaveItemStacks[1].stackSize--;
            }
            if (microwaveItemStacks[1].stackSize == 0)
              microwaveItemStacks[1] = null;
          }
        }
      }
      
      if (isBurning() && canSmelt()) {
        microwaveCookTime++;
        if (microwaveCookTime == cookTime) {
          microwaveCookTime = 0;
          smeltItem();
          flag1 = true;
        }
      } else
        microwaveCookTime = 0;
      
      if (flag != (microwaveBurnTime > 0)) {
        flag1 = true;
        BlockMicrowave.updateFurnaceBlockState(microwaveBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
      }
    }
    
    if (flag1) onInventoryChanged();
  }

  private boolean canSmelt() {
    if (microwaveItemStacks[0] == null) return false;
    ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(microwaveItemStacks[0].getItem().shiftedIndex);
    if (itemstack == null) return false;
    
    if (microwaveItemStacks[2] == null) return true;
    if (!microwaveItemStacks[2].isItemEqual(itemstack)) return false;
    if (microwaveItemStacks[2].stackSize < getInventoryStackLimit() && microwaveItemStacks[2].stackSize < microwaveItemStacks[2].getMaxStackSize())
      return true;
    else 
    	return microwaveItemStacks[2].stackSize < itemstack.getMaxStackSize();
  }

  public void smeltItem() {
    if (!canSmelt()) return;
        
    ItemStack itemstack = FurnaceRecipes.smelting().getSmeltingResult(microwaveItemStacks[0].getItem().shiftedIndex);
    if (microwaveItemStacks[2] == null)
      microwaveItemStacks[2] = itemstack.copy();
    else if(microwaveItemStacks[2].itemID == itemstack.itemID)
      microwaveItemStacks[2].stackSize += itemstack.stackSize;
    
    if (microwaveItemStacks[0].getItem().hasContainerItem())
      microwaveItemStacks[0] = new ItemStack(microwaveItemStacks[0].getItem().getContainerItem());
    else microwaveItemStacks[0].stackSize--;
        
    if (microwaveItemStacks[0].stackSize <= 0)
      microwaveItemStacks[0] = null;
  }

  private int fuel(ItemStack itemstack) {
    if (itemstack == null) return 0;
    
    int i = itemstack.getItem().shiftedIndex;
    if(i == Item.redstone.shiftedIndex) return 2000;
    if(i == Item.lightStoneDust.shiftedIndex) return 4800;
    return i != mod_PlasticCraft.itemBattery.shiftedIndex ? 0 : 25000;
  }

  public boolean isUseableByPlayer(EntityPlayer entityplayer) {
    if (worldObj.getBlockTileEntity(xCoord, yCoord, zCoord) != this)
      return false;
      
    return entityplayer.getDistanceSq((double)xCoord + 0.5D, (double)yCoord + 0.5D, (double)zCoord + 0.5D) <= 64D;
  }
    
  public void openChest() {}
  public void closeChest() {}
}