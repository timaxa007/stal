package timaxa007.stalker.entity;

import java.util.ArrayList;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import timaxa007.stalker.StalkerMod;
import timaxa007.stalker.StalkerPlayerIEEP;
import timaxa007.stalker.network.TraderEntityIdMessage;

public class EntityTest extends EntityLiving {

	public ArrayList<String> dialog = new ArrayList<String>();

	public EntityTest(World world) {
		super(world);
	}

	@Override
	protected boolean interact(EntityPlayer player) {
		StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(player);
		if (stalkerPlayer == null) return false;
		stalkerPlayer.npc = this;
		if (player instanceof EntityPlayerMP) {
			TraderEntityIdMessage message = new TraderEntityIdMessage();
			message.entityID = this.getEntityId();
			StalkerMod.network.sendTo(message, (EntityPlayerMP)player);
		}
		player.openGui(StalkerMod.instance, 0, player.worldObj, -1, -1, -1);
		return true;
	}

	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);
	}

	public void readEntityFromNBT(NBTTagCompound nbt) {
		super.readEntityFromNBT(nbt);
	}

}
