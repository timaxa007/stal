package timaxa007.stalker.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import timaxa007.stalker.StalkerMod;
import timaxa007.stalker.StalkerPlayerIEEP;
import timaxa007.stalker.network.SyncStalkerPlayerMessage;
import timaxa007.stalker.radiation.RadiationWSD;

public class EventsForge {

	@SubscribeEvent
	public void addEntityConstructing(EntityEvent.EntityConstructing event) {
		if (event.entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)event.entity;
			if (StalkerPlayerIEEP.get(player) == null) StalkerPlayerIEEP.reg(player);
		}
	}

	@SubscribeEvent
	public void cloneStalkerPlayerIEEP(PlayerEvent.Clone event) {
		if (event.original.isDead) return;
		StalkerPlayerIEEP originalStalkerPlayerIEEP = StalkerPlayerIEEP.get(event.original);
		if (originalStalkerPlayerIEEP == null) return;
		StalkerPlayerIEEP newStalkerPlayerIEEP = StalkerPlayerIEEP.get(event.entityPlayer);
		if (newStalkerPlayerIEEP == null) return;
		NBTTagCompound nbt = new NBTTagCompound();
		newStalkerPlayerIEEP.saveNBTData(nbt);
		originalStalkerPlayerIEEP.loadNBTData(nbt);
	}

	@SubscribeEvent
	public void syncStalkerPlayerIEEP(EntityJoinWorldEvent event) {
		if (event.entity instanceof EntityPlayerMP) {
			EntityPlayerMP player = (EntityPlayerMP)event.entity;
			StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(player);
			if (stalkerPlayer == null) return;
			NBTTagCompound nbt = new NBTTagCompound();
			stalkerPlayer.saveNBTData(nbt);
			SyncStalkerPlayerMessage message = new SyncStalkerPlayerMessage();
			message.nbt = nbt;
			StalkerMod.network.sendTo(message, player);
		}
	}

	@SubscribeEvent
	public void bleedingAddEffect(LivingHurtEvent event) {
		if (event.entity.worldObj.isRemote) return;
		if (event.source == StalkerMod.damage_bleeding) return;
		//if (event.source.isUnblockable()) return;

		PotionEffect pe = event.entityLiving.getActivePotionEffect(StalkerMod.potion_bleeding);
		if (pe != null) {
			int duration = pe.getDuration();
			event.entityLiving.removePotionEffect(StalkerMod.potion_bleeding.id);
			event.entityLiving.addPotionEffect(new PotionEffect(StalkerMod.potion_bleeding.id, duration + 500, 0, true));
		} else {
			event.entityLiving.addPotionEffect(new PotionEffect(StalkerMod.potion_bleeding.id, 600, 0, true));
		}
	}

	@SubscribeEvent
	public void worldLoad(WorldEvent.Load event) {
		RadiationWSD.get(event.world);
	}

	@SubscribeEvent
	public void worldSave(WorldEvent.Save event) {
		RadiationWSD.get(event.world).markDirty();
	}

	@SubscribeEvent
	public void chunkLoad(ChunkEvent.Load event) {
		RadiationWSD zsw = RadiationWSD.get(event.world);
		if (zsw != null) zsw.chunkLoad(event.getChunk());
	}

	@SubscribeEvent
	public void chunkUnload(ChunkEvent.Unload event) {
		RadiationWSD zsw = RadiationWSD.get(event.world);
		if (zsw != null) zsw.chunkUnload(event.getChunk());
	}

}
