package net.minecraft.src;

public class TileEntityExtract extends TileEntity implements IInventory {
	private ItemStack extractorItemStacks[];
  public int extractorBurnTime;
  public int currentItemBurnTime;
  public int extractorCookTime;
  public int cookTime;
  
  public TileEntityExtract() {
    extractorItemStacks = new ItemStack[4];
    extractorBurnTime = 0;
    currentItemBurnTime = 0;
    extractorCookTime = 0;
    cookTime = 180;
  }

  public int getSizeInventory() {
    return extractorItemStacks.length;
  }

  public ItemStack getStackInSlot(int i) {
    return extractorItemStacks[i];
  }

  public ItemStack decrStackSize(int i, int j) {
    if (extractorItemStacks[i] != null) {
      if (extractorItemStacks[i].stackSize <= j) {
        ItemStack itemstack = extractorItemStacks[i];
        extractorItemStacks[i] = null;
        return itemstack;
      }
      ItemStack itemstack1 = extractorItemStacks[i].splitStack(j);
      if(extractorItemStacks[i].stackSize == 0)
        extractorItemStacks[i] = null;
      
      return itemstack1;
    } else return null;
  }

  public void setInventorySlotContents(int i, ItemStack itemstack) {
    extractorItemStacks[i] = itemstack;
    
    if (itemstack != null && itemstack.stackSize > getInventoryStackLimit())
      itemstack.stackSize = getInventoryStackLimit();
  }

  public String getInvName() {
    return "Extracting Furnace";
  }

  public void readFromNBT(NBTTagCompound nbttagcompound) {
    super.readFromNBT(nbttagcompound);
    NBTTagList nbttaglist = nbttagcompound.getTagList("Items");
    extractorItemStacks = new ItemStack[getSizeInventory()];
        
    for (int i=0; i<nbttaglist.tagCount(); i++) {
      NBTTagCompound nbttagcompound1 = (NBTTagCompound)nbttaglist.tagAt(i);
      byte byte0 = nbttagcompound1.getByte("Slot");
      
      if (byte0 >= 0 && byte0 < extractorItemStacks.length)
      	extractorItemStacks[byte0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
    }

    extractorBurnTime = nbttagcompound.getShort("BurnTime");
    extractorCookTime = nbttagcompound.getShort("CookTime");
    currentItemBurnTime = fuel(extractorItemStacks[1]);
  }

  public void writeToNBT(NBTTagCompound nbttagcompound) {
    super.writeToNBT(nbttagcompound);
    nbttagcompound.setShort("BurnTime", (short)extractorBurnTime);
    nbttagcompound.setShort("CookTime", (short)extractorCookTime);
    NBTTagList nbttaglist = new NBTTagList();
        
    for (int i=0; i<extractorItemStacks.length; i++) {
      if (extractorItemStacks[i] != null) {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte)i);
        extractorItemStacks[i].writeToNBT(nbttagcompound1);
        nbttaglist.setTag(nbttagcompound1);
      }
    }

    nbttagcompound.setTag("Items", nbttaglist);
  }

  public int getInventoryStackLimit() {
    return 64;
  }

  public int getCookProgressScaled(int i) {
    return (extractorCookTime * i) / cookTime;
  }

  public int getBurnTimeRemainingScaled(int i) {
    if (currentItemBurnTime == 0)
      currentItemBurnTime = cookTime;
    
    return (extractorBurnTime * i) / currentItemBurnTime;
  }

  public boolean isBurning() {
    return extractorBurnTime > 0;
  }

  public void updateEntity() {
    boolean flag = extractorBurnTime > 0;
    boolean flag1 = false;
    if (extractorBurnTime > 0)
      extractorBurnTime--;
    
    if (!worldObj.multiplayerWorld) {
      if (extractorBurnTime == 0 && canSmelt()) {
        currentItemBurnTime = extractorBurnTime = fuel(extractorItemStacks[1]);
        if (extractorBurnTime > 0) {
          flag1 = true;
          if (extractorItemStacks[1] != null) {
            if (extractorItemStacks[1].getItem().hasContainerItem())
              extractorItemStacks[1] = new ItemStack(extractorItemStacks[1].getItem().getContainerItem());
            else
              extractorItemStacks[1].stackSize--;
            
            if (extractorItemStacks[1].stackSize == 0)
              extractorItemStacks[1] = null;
          }
        }
      }
      if (isBurning() && canSmelt()) {
        extractorCookTime++;
        if (extractorCookTime == cookTime) {
          extractorCookTime = 0;
          smeltItem();
          flag1 = true;
        }
      } else
        extractorCookTime = 0;
            
      if (flag != (extractorBurnTime > 0)) {
      	flag1 = true;
      	BlockExtract.updateFurnaceBlockState(extractorBurnTime > 0, worldObj, xCoord, yCoord, zCoord);
      }
    }
    
    if (flag1) onInventoryChanged();
  }

  private boolean canSmelt() {
    if (extractorItemStacks[0] == null) return false;
    ItemStack itemstack = ExtractRecipes.smelting().getSmeltingResult(extractorItemStacks[0].getItem().shiftedIndex);
    ItemStack itemstack1 = ExtractRecipes.smelting().getExtractionResult(extractorItemStacks[0].getItem().shiftedIndex);
        
    if(itemstack == null) return false;
    if(extractorItemStacks[2] == null || extractorItemStacks[3] == null) return true;
    if(!extractorItemStacks[2].isItemEqual(itemstack) || !extractorItemStacks[3].isItemEqual(itemstack1)) return false;
    if(extractorItemStacks[2].stackSize < getInventoryStackLimit() && extractorItemStacks[2].stackSize < extractorItemStacks[2].getMaxStackSize() || extractorItemStacks[3].stackSize < getInventoryStackLimit() && extractorItemStacks[3].stackSize < extractorItemStacks[3].getMaxStackSize())
      return true;
    else
      return extractorItemStacks[2].stackSize < itemstack.getMaxStackSize();
  }

  public void smeltItem() {
    if (!canSmelt()) return;

    ItemStack itemstack = ExtractRecipes.smelting().getSmeltingResult(extractorItemStacks[0].getItem().shiftedIndex);
    ItemStack itemstack1 = ExtractRecipes.smelting().getExtractionResult(extractorItemStacks[0].getItem().shiftedIndex);
        
    if (extractorItemStacks[2] == null)
      extractorItemStacks[2] = itemstack.copy();
    else if (extractorItemStacks[2].itemID == itemstack.itemID)
      extractorItemStacks[2].stackSize += itemstack.stackSize;
    
    if (itemstack1 != null) {
      if (extractorItemStacks[3] == null)
        extractorItemStacks[3] = itemstack1.copy();
      else if(extractorItemStacks[3].itemID == itemstack1.itemID)
        extractorItemStacks[3].stackSize += itemstack1.stackSize;
    }
    if (extractorItemStacks[0].getItem().hasContainerItem())
      extractorItemStacks[0] = new ItemStack(extractorItemStacks[0].getItem().getContainerItem());
    else
    	extractorItemStacks[0].stackSize--;
        
    if (extractorItemStacks[0].stackSize <= 0)
      extractorItemStacks[0] = null;
  }

  private int fuel(ItemStack itemstack) {
    if (itemstack == null) return 0;
    
    int i = itemstack.getItem().shiftedIndex;
    if (i < 256 && Block.blocksList[i].blockMaterial == Material.wood) return 250;
    if (i == Item.stick.shiftedIndex) return 80;
    if (i == Block.sapling.blockID) return 100;
    if (i == Block.netherrack.blockID) return 120;
    if (i == Item.coal.shiftedIndex) return 1600;
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