package net.minecraft.src;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.*;

import net.minecraft.client.Minecraft;
import net.minecraft.src.forge.*;

import org.lwjgl.opengl.GL11;

public class mod_PlasticCraft extends BaseMod {
  public String getVersion() { return "v2.4 (for 1.0.0)"; }
  public static String modDir = "/TehKrush/PlasticCraft/";
  private static String getAppdata() { return Minecraft.getMinecraftDir().getPath(); }
  private static Props props = new Props((new File((new StringBuilder()).append(getAppdata()).append("/config/").append("mod_PlasticCraft.props").toString())).getPath());
  private static void console(String s) { System.out.println("[PlasticCraft] " + s); }
  public static int iOff = 256;
  
  public static String itemSheet = modDir + "pc_items.png";
  public static String blockSheet = modDir + "pc_terrain.png";
  
  private static EnumToolMaterial PLASTIC = EnumHelper.addToolMaterial("Plastic", 1, 400, 3F, 0, 5);
  private static EnumArmorMaterial KEVLAR = EnumHelper.addArmorMaterial("Kevlar", 18, new int[] {0, 8, 6, 0}, 12);
  private static EnumArmorMaterial UTILITY = EnumHelper.addArmorMaterial("Utility", 0, new int[] {0, 0, 0, 0}, 5);

  static { prepareProps(); console("Reading /config/PlasticCraft.props"); }
  // Blocks
  public static Block blockPlastic = new BlockPlastic(props.getInt("blockPlastic"));
  public static Block blockPlasticGoo = new BlockGooPlastic(props.getInt("blockPlasticGoo"));
  public static Block blockC4 = new BlockC4(props.getInt("blockC4"));
  public static Block blockSynthCloth = new Block_PC(props.getInt("blockSynthFiber"), 16, Material.cloth).setHardness(1.2F).setResistance(1250F).setStepSound(Block.soundClothFootstep).setBlockName("pSynthFiber");
  public static Block blockPlexiglass = new BlockPlexiglass(props.getInt("blockPlexiglass"));
  public static Block blockTap = new BlockTap(props.getInt("blockLatexTap"));
  public static Block blockPlexidoor = new BlockPlasticDoor(props.getInt("blockPlexiglassDoor"));
  public static Block blockMicrowaveIdle = new BlockMicrowave(props.getInt("blockMicrowave"), false);
  public static Block blockMicrowaveOn = new BlockMicrowave(props.getInt("blockMicrowaveActive"), true).setLightValue(0.875F);
  public static Block blockTrampoline = new BlockTrampoline(props.getInt("blockTrampoline"));
  public static Block blockAccelerator = new BlockAccelerator(props.getInt("blockAccelerator"));
  public static Block blockExtractor = new BlockExtract(props.getInt("blockExtractor"));
  public static Block blockRope = new BlockRope(props.getInt("blockRope"));
  // Items
  public static Item itemPlastic = new Item_PC(props.getInt("itemPlasticBall") - iOff).setIconIndex(0).setItemName("pPlasticBall");
  public static Item itemPlasticClear = new Item_PC(props.getInt("itemClearBall") - iOff).setIconIndex(1).setItemName("pClearBall");
  public static Item itemPlasticStick = new Item_PC(props.getInt("itemPlasticStick") - iOff).setIconIndex(2).setItemName("pPlasticStick");
  public static Item itemMould = new Item_PC(props.getInt("itemMould") - iOff).setIconIndex(3).setItemName("pMould");
  public static Item itemMouldFull = new Item_PC(props.getInt("itemFilledMould") - iOff).setIconIndex(4).setItemName("pFilledMould");
  public static Item itemWoodDust = new Item_PC(props.getInt("itemWoodFlour") - iOff).setIconIndex(5).setItemName("pWoodFlour");
  public static Item itemSynthString = new Item_PC(props.getInt("itemPlasticString") - iOff).setIconIndex(6).setItemName("pPlasticString");
  public static Item itemIntegratedCircuit = new Item_PC(props.getInt("itemSiliconChip") - iOff).setIconIndex(7).setItemName("pSiliconChip");
  public static Item itemBowlGelatin = new Item_PC(props.getInt("itemGelatinBowl") - iOff).setIconIndex(8).setItemName("pGelatinBowl");
  public static Item itemGelatin = new Item_PC(props.getInt("itemGelatin") - iOff).setIconIndex(9).setItemName("pGelatin");
  public static Item itemPlasticGoo = new Item_PC(props.getInt("itemGooPlastic") - iOff).setIconIndex(10).setItemName("pGooPlastic");
  public static Item itemRubber = new Item_PC(props.getInt("itemRubberBall") - iOff).setIconIndex(11).setItemName("pRubberBall");
  public static Item itemDuctTape = new Item_PC(props.getInt("itemTape") - iOff).setIconIndex(12).setItemName("pTape");
  public static Item itemPlexidoor = new ItemPlasticDoor(props.getInt("itemPlexiglassDoor") - iOff).setIconIndex(13).setItemName("pItemPlasticDoor");
  public static Item itemBattery = new Item_PC(props.getInt("itemBattery") - iOff).setIconIndex(14).setItemName("pBattery");
  public static Item itemSilicon = new Item_PC(props.getInt("itemSilicon") - iOff).setIconIndex(15).setItemName("pSilicon");
  public static Item itemPlasticBoat = new ItemPlasticBoat(props.getInt("itemPlasticBoat") - iOff).setIconIndex(16).setItemName("pBoat");
  public static Item itemC4Defuser = new ItemC4Defuser(props.getInt("itemC4Defuser") - iOff).setIconIndex(17).setItemName("pC4Defuser");
  public static Item itemRope = new ItemRope(props.getInt("itemRope") - iOff).setIconIndex(35).setItemName("pRope");
  public static Item itemPlasticBucket = new ItemPlasticBucket(props.getInt("itemPlasticBucket") - iOff, 0).setIconIndex(18).setItemName("pPlasticBucket");
  public static Item itemPlasticBucketW = new ItemPlasticBucket(props.getInt("itemPlasticWaterBucket") - iOff, Block.waterMoving.blockID).setIconIndex(19).setItemName("pPlasticWaterBucket").setContainerItem(itemPlasticBucket);
  public static Item itemPlasticBucketM = new ItemPlasticBucket(props.getInt("itemPlasticMilkBucket") - iOff, -1).setIconIndex(20).setItemName("pPlasticMilkBucket").setContainerItem(itemPlasticBucket);
  public static Item itemPlasticBucketL = new Item_PC(props.getInt("itemPlasticLatexBucket") - iOff).setIconIndex(21).setItemName("pPlasticLatexBucket");
  public static Item itemPlasticBottle = new ItemPlasticBucket(props.getInt("itemEmptyBottle") - iOff, 0).setIconIndex(22).setItemName("pEmptyBottle");
  public static Item itemPlasticBottleW = new ItemConsumable(props.getInt("itemWaterBottle") - iOff, 3, 0.8F, false).setIconIndex(23).setItemName("pWaterBottle");
  public static Item itemPlasticBottleM = new ItemConsumable(props.getInt("itemMilkBottle") - iOff, 6, 0.6F, true).setIconIndex(24).setItemName("pMilkBottle");
  public static Item itemNeedle = new Item_PC(props.getInt("itemNeedle") - iOff).setIconIndex(25).setItemName("pNeedle");
  public static Item itemNeedleHealth = new ItemConsumable(props.getInt("itemRedNeedle") - iOff, 0, 0, true).setIconIndex(26).setItemName("pHealthNeedle");
  public static Item itemJello = new ItemFood_PC(props.getInt("itemJello") - iOff, 6, 0.7F, false).setIconIndex(34).setItemName("pJello");
  // Tools and Armor
  public static Item armorNightGoggles = new ItemArmor_PC(props.getInt("armorNightVisionGoggles") - iOff, mod_PlasticCraft.UTILITY, ModLoader.AddArmor("NVGoggles"), 0, false).setIconIndex(27).setItemName("pNVGoggles");
  public static Item armorKevlarVest = new ItemArmor_PC(props.getInt("armorKevlarVest") - iOff, mod_PlasticCraft.KEVLAR, ModLoader.AddArmor("Kevlar"), 1, true).setIconIndex(28).setItemName("pKevlarVest");
  public static Item armorKevlarLegs = new ItemArmor_PC(props.getInt("armorKevlarLegs") - iOff, mod_PlasticCraft.KEVLAR, ModLoader.AddArmor("Kevlar"), 2, true).setIconIndex(29).setItemName("pKevlarLegs");
  public static Item armorFallBoots = new ItemArmor_PC(props.getInt("armorFallDampeningBoots") - iOff, mod_PlasticCraft.UTILITY, ModLoader.AddArmor("FallDampener"), 3, false).setIconIndex(30).setItemName("pFallDampener");
  public static Item toolPlasticShovel = new ItemPlasticSpade(props.getInt("toolPlasticShovel") - iOff, mod_PlasticCraft.PLASTIC).setIconIndex(31).setItemName("pShovel");
  public static Item toolPlasticPickaxe = new ItemPlasticPickaxe(props.getInt("toolPlasticPickaxe") - iOff, mod_PlasticCraft.PLASTIC).setIconIndex(32).setItemName("pPickaxe");
  public static Item toolPlasticAxe = new ItemPlasticAxe(props.getInt("toolPlasticAxe") - iOff, mod_PlasticCraft.PLASTIC).setIconIndex(33).setItemName("pAxe");
  // Booleans
  public static int c4Power = props.getInt("c4Power");
  public static int c4Fuse = props.getInt("c4Fuse") * 20;
  public static boolean c4Enhanced = props.getBoolean("c4Enhanced");
  private static String nightvisionStyle = props.getString("nightvisionStyle");
  public static boolean isWearingFallBoots;
  static { props.save(); }
  
  // Repair Lists
  private static ArrayList class1 = new ArrayList(Arrays.asList(new Item[] {
    Item.swordWood, Item.shovelWood, Item.pickaxeWood, Item.axeWood, Item.hoeWood, Item.swordGold, Item.shovelGold, Item.pickaxeGold, Item.axeGold,
    Item.hoeGold, Item.helmetLeather, Item.plateLeather, Item.legsLeather, Item.bootsLeather, Item.helmetGold, Item.plateGold, Item.legsGold, Item.bootsGold
  }));
  private static ArrayList class2 = new ArrayList(Arrays.asList(new Item[] {
    Item.swordStone, Item.shovelStone, Item.pickaxeStone, Item.axeStone, Item.hoeStone, Item.helmetChain, Item.plateChain, Item.legsChain, Item.bootsChain, toolPlasticShovel, toolPlasticPickaxe, toolPlasticAxe
  }));
  private static ArrayList class3 = new ArrayList(Arrays.asList(new Item[] {
    Item.flintAndSteel, Item.shovelSteel, Item.pickaxeSteel, Item.axeSteel, Item.swordSteel, Item.hoeSteel, Item.helmetSteel, Item.plateSteel, Item.legsSteel, Item.bootsSteel, armorKevlarVest, armorKevlarLegs
  }));
  private static ArrayList class4 = new ArrayList(Arrays.asList(new Item[] {
    Item.swordDiamond, Item.shovelDiamond, Item.pickaxeDiamond, Item.axeDiamond, Item.hoeDiamond, Item.helmetDiamond, Item.plateDiamond, Item.legsDiamond, Item.bootsDiamond
  }));
  
  public void ModsLoaded() {
  	MinecraftForge.versionDetectStrict("PlasticCraft", 1, 2, 1);
    MinecraftForgeClient.preloadTexture(itemSheet);
    MinecraftForgeClient.preloadTexture(blockSheet);
  }
  
  public void load() {
    registerItems();
    console("Registering items.");
    addRecipes();
    console("Registering recipes.");
    
    MinecraftForge.setToolClass(toolPlasticShovel, "shovel", 1);
    MinecraftForge.setToolClass(toolPlasticPickaxe, "pickaxe", 1);
    MinecraftForge.setToolClass(toolPlasticAxe, "axe", 1);
  	
    ModLoader.SetInGameHook(this, true, false);
    
    ModLoader.RegisterTileEntity(TileEntityMicrowave.class, "Microwave");
    ModLoader.RegisterTileEntity(TileEntityExtract.class, "Extracting Furnace");
    ModLoader.RegisterEntityID(EntityC4Primed.class, "C4", ModLoader.getUniqueEntityId());
    ModLoader.RegisterEntityID(EntityPlasticBoat.class, "Plastic Boat", ModLoader.getUniqueEntityId());
    
    ModLoader.RemoveSpawn("Cow", EnumCreatureType.creature);
    ModLoader.RegisterEntityID(EntityPlasticCow.class, "Cow", 92);
    ModLoader.AddSpawn(EntityPlasticCow.class, 8, 4, 4, EnumCreatureType.creature);
  }
  
  public static void registerItems() {
    ModLoader.RegisterBlock(blockPlastic, ItemBlockPlastic.class);
    ModLoader.RegisterBlock(blockPlasticGoo);
    ModLoader.RegisterBlock(blockC4);
    ModLoader.RegisterBlock(blockSynthCloth);
    ModLoader.RegisterBlock(blockPlexiglass, ItemBlockPlexiglass.class);
    ModLoader.RegisterBlock(blockTap);
    ModLoader.RegisterBlock(blockPlexidoor);
    ModLoader.RegisterBlock(blockMicrowaveIdle);
    ModLoader.RegisterBlock(blockMicrowaveOn);
    ModLoader.RegisterBlock(blockTrampoline);
    ModLoader.RegisterBlock(blockAccelerator);
    ModLoader.RegisterBlock(blockExtractor);
    ModLoader.RegisterBlock(blockRope);
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 0), "Plain Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 1), "Orange Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 2), "Magenta Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 3), "Light Blue Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 4), "Yellow Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 5), "Lime Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 6), "Pink Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 7), "Grey Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 8), "Teal Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 9), "Cyan Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 10), "Purple Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 11), "Blue Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 12), "Brown Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 13), "Green Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 14), "Red Plastic");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlastic.blockID], 1, 15), "Black Plastic");
    ModLoader.AddName(blockPlasticGoo, "Gooey Plastic Block");
    ModLoader.AddName(blockC4, "C4 Explosive");
    ModLoader.AddName(blockSynthCloth, "Synthetic Fiber");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlexiglass.blockID], 1, 0), "Plexiglass");
    ModLoader.AddName(new ItemStack(Item.itemsList[blockPlexiglass.blockID], 1, 1), "Glowing Plexiglass");
    ModLoader.AddName(blockTap, "Latex Extractor");
    ModLoader.AddName(blockMicrowaveIdle, "Microwave Oven");
    ModLoader.AddName(blockTrampoline, "Trampoline");
    ModLoader.AddName(blockAccelerator, "Accelerator");
    ModLoader.AddName(blockExtractor, "Extracting Furnace");
    
    ModLoader.AddName(itemPlastic, "Plastic Ball");
    ModLoader.AddName(itemPlasticClear, "Clear Plastic Ball");
    ModLoader.AddName(itemPlasticStick, "Plastic Stick");
    ModLoader.AddName(itemWoodDust, "Wood Flour");
    ModLoader.AddName(itemMould, "Mould");
    ModLoader.AddName(itemMouldFull, "Filled Mould");
    ModLoader.AddName(itemSynthString, "Sythetic String");
    ModLoader.AddName(itemIntegratedCircuit, "Integrated Circuit");
    ModLoader.AddName(itemBowlGelatin, "Bowl of Gelatin");
    ModLoader.AddName(itemGelatin, "Gelatin Powder");
    ModLoader.AddName(itemPlasticGoo, "Plastic Goo");
    ModLoader.AddName(itemRubber, "Rubber Ball");
    ModLoader.AddName(itemDuctTape, "Duct Tape");
    ModLoader.AddName(itemPlexidoor, "Plexiglass Door");
    ModLoader.AddName(itemBattery, "Battery");
    ModLoader.AddName(itemSilicon, "Rough Silicon");
    ModLoader.AddName(itemPlasticBoat, "Plastic Boat");
    ModLoader.AddName(itemC4Defuser, "Handheld C4 Defuser");
    ModLoader.AddName(itemRope, "Synthetic Rope");
    ModLoader.AddName(itemPlasticBucket, "Plastic Bucket");
    ModLoader.AddName(itemPlasticBucketW, "Plastic Water Bucket");
    ModLoader.AddName(itemPlasticBucketM, "Plastic Milk Bucket");
    ModLoader.AddName(itemPlasticBucketL, "Plastic Latex Bucket");
    ModLoader.AddName(itemPlasticBottle, "Plastic Bottle");
    ModLoader.AddName(itemPlasticBottleW, "Water Bottle");
    ModLoader.AddName(itemPlasticBottleM, "Milk Bottle");
    ModLoader.AddName(itemNeedle, "Needle");
    ModLoader.AddName(itemNeedleHealth, "Health Needle");
    ModLoader.AddName(itemJello, "Jello");
    ModLoader.AddName(armorNightGoggles, "Night-Vision Goggles");
    ModLoader.AddName(armorKevlarVest, "Kevlar Vest");
    ModLoader.AddName(armorKevlarLegs, "Kevlar Pants");
    ModLoader.AddName(armorFallBoots, "Shock-Absorbing Boots");
    ModLoader.AddName(toolPlasticShovel, "Plastic Shovel");
    ModLoader.AddName(toolPlasticPickaxe, "Plastic Pickaxe");
    ModLoader.AddName(toolPlasticAxe, "Plastic Axe");
  }

  public static void addRecipes() {
  	// Blocks
    ModLoader.AddRecipe(new ItemStack(blockPlastic, 1, 0), new Object[] { "PP", "PP", 
      'P', itemPlastic });
    ModLoader.AddRecipe(new ItemStack(blockPlasticGoo), new Object[] { "PP", "PP", 
      'P', itemPlasticGoo });
    ModLoader.AddRecipe(new ItemStack(blockC4), new Object[] { "GPG", "PCP", "GPG", 
      'P', blockPlastic, 'G', Item.gunpowder, 'C', itemIntegratedCircuit });
    ModLoader.AddRecipe(new ItemStack(blockSynthCloth), new Object[] { "SS", "SS", 
      'S', itemSynthString });
    ModLoader.AddRecipe(new ItemStack(blockPlexiglass, 1, 0), new Object[] { "PP", "PP", 
      'P', itemPlasticClear });
    ModLoader.AddRecipe(new ItemStack(blockPlexiglass, 1, 1), new Object[] { " L ", "LPL", " L ", 
      'P', new ItemStack(blockPlexiglass, 1, 0), 'L', Item.lightStoneDust });
    ModLoader.AddRecipe(new ItemStack(blockTap), new Object[] { "P", "P", "P", 
      'P', itemPlastic });
    ModLoader.AddRecipe(new ItemStack(blockMicrowaveIdle), new Object[] { "IPI", "GSG", "IPI", 
      'P', blockPlastic, 'I', Item.ingotIron, 'G', Block.glass, 'S', itemIntegratedCircuit });
    ModLoader.AddRecipe(new ItemStack(blockTrampoline), new Object[] { "RRR", "WWW", 
      'R', itemRubber, 'W', Block.planks });
    ModLoader.AddRecipe(new ItemStack(blockAccelerator, 4), new Object[] { "RXR", "XSX", "RXR", 
      'R', itemRubber, 'X', Item.redstone, 'S', itemIntegratedCircuit });
    ModLoader.AddRecipe(new ItemStack(blockExtractor), new Object[] { "PXP", "PFP", "PEP", 
      'P', blockPlastic, 'F', Block.stoneOvenIdle, 'E', blockTap, 'X', itemIntegratedCircuit });
    
    // Items
    ModLoader.AddRecipe(new ItemStack(itemPlastic, 4), new Object[] { "X", 
      'X', blockPlastic });
    ModLoader.AddRecipe(new ItemStack(itemPlasticClear, 4), new Object[] { "X", 
      'X', new ItemStack(blockPlexiglass, 1, 0) });
    ModLoader.AddRecipe(new ItemStack(itemPlasticGoo, 4), new Object[] { "X", 
      'X', blockPlasticGoo });
    ModLoader.AddRecipe(new ItemStack(itemSynthString, 4), new Object[] { "X", 
      'X', blockSynthCloth });
    ModLoader.AddRecipe(new ItemStack(itemPlasticStick, 4), new Object[] { "P", "P", 
      'P', itemPlastic });
    ModLoader.AddRecipe(new ItemStack(itemSynthString, 4), new Object[] { "PP", 
      'P', itemPlasticClear });
    ModLoader.AddRecipe(new ItemStack(itemPlasticClear), new Object[] { "P", 
      'P', itemPlastic });
    ModLoader.AddRecipe(new ItemStack(itemMould), new Object[] { "S S", " S ", 
      'S', Block.cobblestone });
    ModLoader.AddShapelessRecipe(new ItemStack(itemMouldFull), new Object[] { 
      new ItemStack(itemWoodDust), new ItemStack(Item.egg), new ItemStack(itemMould) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemMouldFull), new Object[] {
      new ItemStack(itemWoodDust), new ItemStack(itemGelatin), new ItemStack(itemMould) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemMouldFull), new Object[] { 
      new ItemStack(itemWoodDust), new ItemStack(Item.dyePowder, 1, 15), new ItemStack(itemMould) });
    ModLoader.AddRecipe(new ItemStack(itemWoodDust, 4), new Object[] { "W", 
      'W', Block.planks });
    ModLoader.AddShapelessRecipe(new ItemStack(itemBowlGelatin), new Object[] { 
      new ItemStack(Item.beefRaw), new ItemStack(Item.bowlEmpty) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemBowlGelatin), new Object[] { 
      new ItemStack(Item.chickenRaw), new ItemStack(Item.bowlEmpty) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemBowlGelatin), new Object[] { 
      new ItemStack(Item.porkRaw), new ItemStack(Item.bowlEmpty) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemBowlGelatin), new Object[] { 
      new ItemStack(Item.leather), new ItemStack(Item.bowlEmpty) });
    ModLoader.AddRecipe(new ItemStack(itemDuctTape, 4), new Object[] { "RRR", "GGG", 
      'R', itemRubber, 'G', itemPlasticGoo });
    ModLoader.AddRecipe(new ItemStack(itemPlexidoor), new Object[] { "PP", "PP", "PP", 
      'P', new ItemStack(blockPlexiglass, 1, 0) });
    ModLoader.AddRecipe(new ItemStack(itemBattery), new Object[] { "III", "RBR", "LLL", 
      'I', Item.ingotIron, 'R', Item.redstone, 'L', Item.lightStoneDust, 'B', Item.bucketLava });
    ModLoader.AddRecipe(new ItemStack(itemSilicon, 3), new Object[] { " SS", "S S", "SS ", 
      'S', Block.sand });
    ModLoader.AddRecipe(new ItemStack(itemIntegratedCircuit, 2), new Object[] { "SFS", "IRI", "PPP", 
      'F', blockSynthCloth, 'I', Item.ingotIron, 'R', Item.redstoneRepeater, 'P', blockPlastic, 'S', itemSilicon });
    ModLoader.AddRecipe(new ItemStack(itemPlasticBoat), new Object[] { "P P", "PGP", 
      'P', blockPlastic, 'G', new ItemStack(blockPlexiglass, 1, 0) });
    ModLoader.AddRecipe(new ItemStack(itemC4Defuser), new Object[] { "PBP", "RCG", "IS ", 
      'P', itemPlastic, 'R', Item.redstone, 'I', Item.ingotIron, 'C', itemIntegratedCircuit, 'G', Block.glass, 'B', Block.button, 'S', itemBattery });
    ModLoader.AddRecipe(new ItemStack(itemRope), new Object[] { "/I", "SS", "SS", 
      'S', blockSynthCloth, '/', itemSynthString, 'I', Item.ingotIron });
    ModLoader.AddRecipe(new ItemStack(itemPlasticBucket), new Object[] { "P P", " P ", 
      'P', itemPlastic });
    ModLoader.AddRecipe(new ItemStack(itemPlasticBottle), new Object[] { "P  ", " P ", "  P", 
      'P', itemPlasticClear });
    ModLoader.AddShapelessRecipe(new ItemStack(itemPlasticBottleM), new Object[] { 
      new ItemStack(itemPlasticBucketM), new ItemStack(itemPlasticBottle) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemPlasticBottleM), new Object[] {
      new ItemStack(Item.bucketMilk), new ItemStack(itemPlasticBottle) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemPlasticBottleW), new Object[] { 
      new ItemStack(itemPlasticBucketW), new ItemStack(itemPlasticBottle) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemPlasticBottleW), new Object[] { 
      new ItemStack(Item.bucketMilk), new ItemStack(itemPlasticBottle) });
    ModLoader.AddRecipe(new ItemStack(itemNeedle, 2), new Object[] { "P P", "G G", " I ", 
      'P', itemPlasticClear, 'G', Block.glass, 'I', Item.ingotIron });
    ModLoader.AddRecipe(new ItemStack(itemNeedleHealth), new Object[] { " A ", "RFR", " N ", 
      'N', itemNeedle, 'R', Item.redstone, 'F', Block.plantRed, 'A', Item.speckledMelon });
    ModLoader.AddShapelessRecipe(new ItemStack(itemJello), new Object[] { 
      new ItemStack(itemPlasticBucketW), new ItemStack(Item.sugar), new ItemStack(itemGelatin) });
    ModLoader.AddShapelessRecipe(new ItemStack(itemJello), new Object[] { 
      new ItemStack(Item.bucketWater), new ItemStack(Item.sugar), new ItemStack(itemGelatin) });
    
    // Tools and Armor
    ModLoader.AddRecipe(new ItemStack(armorNightGoggles), new Object[] { "SSS", "S S", "PCP", 
      'S', itemSynthString, 'P', Item.diamond, 'C', itemIntegratedCircuit });
    ModLoader.AddRecipe(new ItemStack(armorKevlarVest), new Object[] { "SSS", "/I/", "SSS", 
      'S', blockSynthCloth, '/', itemSynthString, 'I', Item.plateLeather });
    ModLoader.AddRecipe(new ItemStack(armorKevlarLegs), new Object[] { "SSS", "/I/", "SSS", 
      'S', blockSynthCloth, '/', itemSynthString, 'I', Item.legsLeather });
    ModLoader.AddRecipe(new ItemStack(armorFallBoots), new Object[] { "O O", " C ", "R R", 
      'R', itemRubber, 'O', Block.obsidian, 'C', itemIntegratedCircuit });
    ModLoader.AddRecipe(new ItemStack(toolPlasticShovel), new Object[] { " P ", " / ", " / ", 
      'P', itemPlastic, '/', itemPlasticStick });
    ModLoader.AddRecipe(new ItemStack(toolPlasticPickaxe), new Object[] { "PPP", " / ", " / ", 
    	'P', itemPlastic, '/', itemPlasticStick });
    ModLoader.AddRecipe(new ItemStack(toolPlasticAxe), new Object[] { "PP ", "P/ ", " / ", 
    	'P', itemPlastic, '/', itemPlasticStick });
    
    // Other
    ModLoader.AddRecipe(new ItemStack(Item.bow), new Object[] { "S/ ", "S /", "S/ ", 
      '/', Item.stick, 'S', itemSynthString });
    ModLoader.AddRecipe(new ItemStack(Item.fishingRod), new Object[] { "  /", " /S", "/ S", 
      '/', Item.stick, 'S', itemSynthString });
    ModLoader.AddRecipe(new ItemStack(Item.feather, 4), new Object[] { "S", "/", 
      '/', Item.stick, 'S', blockSynthCloth });
    ModLoader.AddRecipe(new ItemStack(Item.redstone, 5), new Object[] { "SIS", " S ", "SIS", 
      'S', itemSilicon, 'I', Item.ingotIron });
    ModLoader.AddRecipe(new ItemStack(Item.redstone, 15), new Object[] { "SIS", " S ", "SIS", 
      'S', itemSilicon, 'I', Item.ingotGold });
    ModLoader.AddRecipe(new ItemStack(Item.cake), new Object[] { "MMM", "SES", "WWW", 
      'M', itemPlasticBucketM, 'S', Item.sugar, 'E', Item.egg, 'W', Item.wheat });
    ModLoader.AddShapelessRecipe(new ItemStack(Block.pistonStickyBase), new Object[] { 
      new ItemStack(Block.pistonBase), new ItemStack(itemPlasticGoo) });
    
    // Misc
    BlockPlastic.recipes();
    
    // Furnace(s)  
    ModLoader.AddSmelting(itemMouldFull.shiftedIndex, new ItemStack(itemPlastic, 3));
    ModLoader.AddSmelting(itemPlastic.shiftedIndex, new ItemStack(itemPlasticGoo));
    ModLoader.AddSmelting(itemBowlGelatin.shiftedIndex, new ItemStack(itemGelatin, 4));
    ModLoader.AddSmelting(itemPlasticBucketL.shiftedIndex, new ItemStack(itemRubber, 2));
    addExtractorSmelting(itemMouldFull.shiftedIndex, new ItemStack(itemPlastic, 3), new ItemStack(itemMould));
    addExtractorSmelting(itemPlastic.shiftedIndex, new ItemStack(itemPlasticGoo));
    addExtractorSmelting(itemBowlGelatin.shiftedIndex, new ItemStack(itemGelatin, 4), new ItemStack(Item.bowlEmpty));
    addExtractorSmelting(itemPlasticBucketL.shiftedIndex, new ItemStack(itemRubber, 2), new ItemStack(itemPlasticBucket));
    
    // Repair
    for (int i=0; i<class1.size(); i++) {
      Item item = (Item)class1.get(i);
      ModLoader.AddShapelessRecipe(new ItemStack(item), new Object[] { 
      	new ItemStack(itemDuctTape), new ItemStack(item, 1, -1) });
    }
    for (int i=0; i<class2.size(); i++) {
      Item item1 = (Item)class2.get(i);
      ModLoader.AddShapelessRecipe(new ItemStack(item1), new Object[] { 
      	new ItemStack(itemDuctTape), new ItemStack(itemDuctTape), new ItemStack(item1, 1, -1) });
    }  
    for (int i=0; i<class3.size(); i++) {
      Item item2 = (Item)class3.get(i);
      ModLoader.AddRecipe(new ItemStack(item2), new Object[] {
        " D ", "DXD", " D ", 'D', itemDuctTape, 'X', new ItemStack(item2, 1, -1) });
    }
    for (int i=0; i<class4.size(); i++) {
      Item item3 = (Item)class4.get(i);
      ModLoader.AddRecipe(new ItemStack(item3), new Object[] {
        "DDD", "DXD", "DDD", 'D', itemDuctTape, 'X', new ItemStack(item3, 1, -1) });
    }     
  }
  
  public static void addRepairs(int i, Item item) {
    if (i == 1) class1.add(item);
    if (i == 2) class2.add(item);
    if (i == 3) class3.add(item);
    if (i == 4) class4.add(item);
  }
  
  public static void addExtractorSmelting(int i, ItemStack itemstack, ItemStack itemstack1) {
    ExtractRecipes.smelting().addSmelting(i, itemstack);
    ExtractRecipes.smelting().addExtraction(i, itemstack1);
  }
  
  public static void addExtractorSmelting(int i, ItemStack itemstack) {
    ExtractRecipes.smelting().addSmelting(i, itemstack);
  }
  
  public boolean OnTickInGame(float f, Minecraft minecraft) {
    if (minecraft.currentScreen == null) {
      renderNightvisionOverlay(minecraft);
    }

    enableShockAbsorbing(minecraft);
    Stun.tick();
    return true;
  }
  
  public void AddRenderer(Map map) {
    map.put(net.minecraft.src.EntityC4Primed.class, new RenderC4Primed());
    map.put(net.minecraft.src.EntityPlasticBoat.class, new RenderPlasticBoat());
  }
  
  public void RegisterAnimation(Minecraft minecraft) {
    ModLoader.addAnimation(new TextureFrameAnimFX(BlockMicrowave.microwaveAnim, modDir + "blockMicrowaveAnim.png"));
  }

  public boolean DispenseEntity(World world, double d, double d1, double d2, int i, int j, ItemStack itemstack) {
    EntityPlayerSP entityplayersp = ModLoader.getMinecraftInstance().thePlayer;
    int k = itemstack.itemID;
    Random random = world.rand;
    Entity block = null;
        
    if (k == blockC4.blockID)
    	block = new EntityC4Primed(world, d, d1, d2);
    if (k == Block.tnt.blockID)
    	block = new EntityTNTPrimed(world, d, d1, d2);
    if (k == blockPlasticGoo.blockID)
    	block = new EntityFallingSand(world, d, d1, d2, blockPlasticGoo.blockID);
    if (k == Block.sand.blockID)
    	block = new EntityFallingSand(world, d, d1, d2, Block.sand.blockID);
    if (k == Block.gravel.blockID)
    	block = new EntityFallingSand(world, d, d1, d2, Block.gravel.blockID);
    if (block != null) {
      block.posZ = (double)i * (random.nextDouble() * 3D + 2D) + (double)(random.nextFloat() - 0.5F);
      block.motionX = 1.0D;
      block.motionY = (double)j * (random.nextDouble() * 3D + 2D) + (double)(random.nextFloat() - 0.5F);
      world.entityJoinedWorld((Entity)block);
      world.playSoundEffect(d, d1, d2, "random.bow", 1.0F, 1.2F);
      return true;
    } else
    return false;
  }

  private void enableShockAbsorbing(Minecraft minecraft) {
    ItemStack itemstack = minecraft.thePlayer.inventory.armorItemInSlot(0);
    
    if (itemstack != null && itemstack.itemID == armorFallBoots.shiftedIndex) {
      minecraft.thePlayer.fallDistance = -1F;
      isWearingFallBoots = true;
    } else
      isWearingFallBoots = false;
  }

  private void renderNightvisionOverlay(Minecraft minecraft) {
    ItemStack itemstack = minecraft.thePlayer.inventory.armorItemInSlot(3);
        
    if (!minecraft.gameSettings.hideGUI && itemstack != null && itemstack.itemID == armorNightGoggles.shiftedIndex)
      renderTextureOverlay(minecraft, "%blur%/TehKrush/PlasticCraft/guiNightVision" + nightvisionStyle + ".png", 1.0F);
  }

  private void renderTextureOverlay(Minecraft minecraft, String s, float f) {
    ScaledResolution scaledresolution = new ScaledResolution(minecraft.gameSettings, minecraft.displayWidth, minecraft.displayHeight);
    int i = scaledresolution.getScaledWidth();
    int j = scaledresolution.getScaledHeight();
    GL11.glEnable(3042 /*GL_BLEND*/);
    GL11.glDisable(2929 /*GL_DEPTH_TEST*/);
    GL11.glDepthMask(false);
    GL11.glBlendFunc(770, 771);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
    GL11.glDisable(3008 /*GL_ALPHA_TEST*/);
    GL11.glBindTexture(3553 /*GL_TEXTURE_2D*/, minecraft.renderEngine.getTexture(s));
    Tessellator tessellator = Tessellator.instance;
    tessellator.startDrawingQuads();
    tessellator.addVertexWithUV(0.0D, j, -90D, 0.0D, 1.0D);
    tessellator.addVertexWithUV(i, j, -90D, 1.0D, 1.0D);
    tessellator.addVertexWithUV(i, 0.0D, -90D, 1.0D, 0.0D);
    tessellator.addVertexWithUV(0.0D, 0.0D, -90D, 0.0D, 0.0D);
    tessellator.draw();
    GL11.glDepthMask(true);
    GL11.glEnable(2929 /*GL_DEPTH_TEST*/);
    GL11.glEnable(3008 /*GL_ALPHA_TEST*/);
    GL11.glColor4f(1.0F, 1.0F, 1.0F, f);
  }

  private static void prepareProps() {
    console("Preparing /config/PlasticCraft.props");
    
    props.getInt("blockPlastic", 125);
    props.getInt("blockPlasticGoo", 126);
    props.getInt("blockC4", 127);
    props.getInt("blockSynthFiber", 128);
    props.getInt("blockPlexiglass", 129);
    props.getInt("blockLatexTap", 130);
    props.getInt("blockPlexiglassDoor", 131);
    props.getInt("blockMicrowave", 132);
    props.getInt("blockMicrowaveActive", 133);
    props.getInt("blockTrampoline", 134);
    props.getInt("blockAccelerator", 135);
    props.getInt("blockExtractor", 136);
    props.getInt("blockRope", 137);
    
    props.getInt("itemPlasticBall", 1001);
    props.getInt("itemClearBall", 1002);
    props.getInt("itemPlasticStick", 1003);
    props.getInt("itemMould", 1004);
    props.getInt("itemFilledMould", 1005);
    props.getInt("itemWoodFlour", 1006);
    props.getInt("itemPlasticString", 1007);
    props.getInt("itemSiliconChip", 1008);
    props.getInt("itemGelatinBowl", 1009);
    props.getInt("itemGelatin", 1010);
    props.getInt("itemGooPlastic", 1011);
    props.getInt("itemRubberBall", 1012);
    props.getInt("itemTape", 1013);
    props.getInt("itemPlexiglassDoor", 1014);
    props.getInt("itemBattery", 1015);
    props.getInt("itemSilicon", 1016);
    props.getInt("itemPlasticBoat", 1017);
    props.getInt("itemC4Defuser", 1018);
    props.getInt("itemPlasticBucket", 1019);
    props.getInt("itemPlasticWaterBucket", 1020);
    props.getInt("itemPlasticMilkBucket", 1021);
    props.getInt("itemPlasticLatexBucket", 1022);
    props.getInt("itemEmptyBottle", 1023);
    props.getInt("itemWaterBottle", 1024);
    props.getInt("itemMilkBottle", 1025);
    props.getInt("itemNeedle", 1026);
    props.getInt("itemRedNeedle", 1027);
    props.getInt("itemJello", 1028);
    props.getInt("itemRope", 1029);
    
    props.getInt("armorNightVisionGoggles", 1040);
    props.getInt("armorKevlarVest", 1041);
    props.getInt("armorKevlarLegs", 1042);
    props.getInt("armorFallDampeningBoots", 1043);
    props.getInt("toolPlasticShovel", 1050);
    props.getInt("toolPlasticPickaxe", 1051);
    props.getInt("toolPlasticAxe", 1052);
    
    props.getInt("c4Power", 10);
    props.getInt("c4Fuse", 6);
    props.getBoolean("c4Enhanced", true);
    props.getString("nightvisionStyle", "SDK");
  }

  public static boolean fieldExists(Object obj, String s) {
    Field afield[] = obj.getClass().getDeclaredFields();
    
    for (int i=0; i<afield.length; i++)
      if (afield[i].getName().equals(s))
        return true;
    
    return false;
  }
    
  static class Stun {
  	private static ArrayList shockedMobs = new ArrayList();
    private static ArrayList shockedMobsTime = new ArrayList();
    private static ArrayList shockedMobsCantTime = new ArrayList();
    private static ArrayList shockedMobsSpeed = new ArrayList();
    private static long lastTime = System.currentTimeMillis();
    private static double delta = 0.0D;
    
    static void tick() {
      delta = (double)(System.currentTimeMillis() - lastTime) / 1000D;
      lastTime = System.currentTimeMillis();
            
      for (int i=0; i<shockedMobs.size(); i++) {
        EntityLiving entityliving = (EntityLiving)shockedMobs.get(i);
        double d = ((Double)shockedMobsTime.get(i)).doubleValue();
        double d1 = ((Double)shockedMobsCantTime.get(i)).doubleValue();
        d -= delta;
                
        if (d <= -d1) {
          shockedMobs.remove(i);
          shockedMobsTime.remove(i);
          shockedMobsCantTime.remove(i);
          shockedMobsSpeed.remove(i);
          i--;
          continue;
        }
                
        if (d > 0.0D) {
          entityliving.motionX = 0.0D;
          entityliving.newPosY = 0.0D;
          entityliving.motionZ = 0.0D;
        } else {
          float f = ((Float)shockedMobsSpeed.get(i)).floatValue();
          entityliving.moveSpeed = f;
        }
         
        shockedMobsTime.set(i, Double.valueOf(d));
      }
    }

    static void shockMob(EntityLiving entityliving, double d, double d1) {
      if (!shockedMobs.contains(entityliving)) {
        shockedMobs.add(entityliving);
        shockedMobsTime.add(Double.valueOf(d));
        shockedMobsCantTime.add(Double.valueOf(d1));
        shockedMobsSpeed.add(Float.valueOf(entityliving.moveSpeed));
        entityliving.moveSpeed = 0.0F;
      }
    }
  }
}