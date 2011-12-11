package net.minecraft.src;

public class EntityC4Primed extends Entity {
  public int fuse;
  public int power;
  public int connectedCount;
  public static BlockC4 c4 = (BlockC4)mod_PlasticCraft.blockC4;
  public float renderScale;
  
  public EntityC4Primed(World world) {
    super(world);
    power = mod_PlasticCraft.c4Power;
    fuse = mod_PlasticCraft.c4Fuse;
    preventEntitySpawning = true;
    setSize(0.98F, 0.98F);
    renderScale = 1.0F;
    yOffset = height / 2.0F;
    connectedCount = 1;
  }

  public EntityC4Primed(World world, double d, double d1, double d2) {
    this(world);
    setPosition(d, d1, d2);
    float f = (float)(Math.random() * 3.1415927410125728D * 2D);
    motionX = -MathHelper.sin((f * 3.141593F) / 180F) * 0.02F;
    motionY = 0.20000000298023221D;
    motionZ = -MathHelper.cos((f * 3.141593F) / 180F) * 0.02F;
    fuse = mod_PlasticCraft.c4Fuse;
    prevPosX = posX;
    prevPosY = posY;
    prevPosZ = posZ;
  }

  protected void entityInit() {}

  protected boolean canTriggerWalking() {
    return false;
  }

  public boolean canBeCollidedWith() {
    return !field_9293_aM;
  }

  public void onUpdate() {
    prevPosX = posX;
    prevPosY = posY;
    prevPosZ = posZ;
    motionY -= 0.039999999105930328D;
    moveEntity(motionX, motionY, motionZ);
    motionX *= 0.98000001907348633D;
    motionY *= 0.98000001907348633D;
    motionZ *= 0.98000001907348633D;
        
    if (onGround) {
      motionX *= 0.69999998807907104D;
      motionZ *= 0.69999998807907104D;
      motionY *= -0.5D;
    }
    
    if (fuse-- <= 0) {
      if (!worldObj.multiplayerWorld) {
        setEntityDead();
        explode();
      } else 
      	setEntityDead();
    } else
      worldObj.spawnParticle("smoke", posX, posY + 0.5D, posZ, 0.0D, 0.0D, 0.0D);
  }

  private void explode() {
    int i = power;
    worldObj.createExplosion(null, posX, posY, posZ, i);
  }

  protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {
    nbttagcompound.setByte("Fuse", (byte)fuse);
  }

  protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {
    fuse = nbttagcompound.getByte("Fuse");
  }

  public float getShadowSize() {
    return 0.0F;
  }
}