package timaxa007.stalker.network;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayerMP;
import timaxa007.stalker.StalkerPlayerIEEP;

public class TraderEntityIdMessage implements IMessage {

	public int entityID;

	public TraderEntityIdMessage() {}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(entityID);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		entityID = buf.readInt();
	}

	public static class Handler implements IMessageHandler<TraderEntityIdMessage, IMessage> {

		@Override
		public IMessage onMessage(TraderEntityIdMessage packet, MessageContext message) {
			if (message.side.isClient())
				act(packet);
			else
				act(message.getServerHandler().playerEntity, packet);
			return null;
		}

		@SideOnly(Side.CLIENT)
		private void act(TraderEntityIdMessage packet) {
			Minecraft mc = Minecraft.getMinecraft();
			StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(mc.thePlayer);
			if (stalkerPlayer == null) return;
			stalkerPlayer.npc = mc.theWorld.getEntityByID(packet.entityID);
		}

		private void act(EntityPlayerMP player, TraderEntityIdMessage packet) {
			StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(player);
			if (stalkerPlayer == null) return;
			stalkerPlayer.npc = player.worldObj.getEntityByID(packet.entityID);
		}

	}

}
