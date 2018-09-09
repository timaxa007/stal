package timaxa007.stalker.network;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import timaxa007.stalker.StalkerPlayerIEEP;

public class SyncStalkerPlayerMessage implements IMessage {

	public NBTTagCompound nbt;

	public SyncStalkerPlayerMessage() {}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
	}

	public static class Handler implements IMessageHandler<SyncStalkerPlayerMessage, IMessage> {

		@Override
		public IMessage onMessage(SyncStalkerPlayerMessage packet, MessageContext message) {
			if (message.side.isClient())
				act(packet);
			else
				act(message.getServerHandler().playerEntity, packet);
			return null;
		}

		@SideOnly(Side.CLIENT)
		private void act(SyncStalkerPlayerMessage packet) {
			StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(Minecraft.getMinecraft().thePlayer);
			if (stalkerPlayer == null) return;
			stalkerPlayer.loadNBTData(packet.nbt);
		}

		private void act(EntityPlayerMP player, SyncStalkerPlayerMessage packet) {
			StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(player);
			if (stalkerPlayer == null) return;
			stalkerPlayer.loadNBTData(packet.nbt);
		}

	}

}
