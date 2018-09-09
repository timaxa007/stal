package timaxa007.stalker.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import timaxa007.stalker.StalkerPlayerIEEP;
import timaxa007.stalker.radiation.RadiationChunk;
import timaxa007.stalker.radiation.RadiationWSD;
import timaxa007.stalker.radiation.RadiationZone;
import timaxa007.stalker.register.Parameters;

public class EventsFML {

	@SubscribeEvent
	public void tickPlayer(TickEvent.PlayerTickEvent event) {
		if (event.side.isClient()) return;
		switch (event.phase) {
		case END:
			StalkerPlayerIEEP sp = StalkerPlayerIEEP.get(event.player);
			if (sp == null) return;
			RadiationWSD rwsd = RadiationWSD.get(event.player.worldObj);
			if (rwsd == null) return;
			if (rwsd.loadedChunk.isEmpty()) return;

			int rad = 0;

			for (int i = 0; i < rwsd.loadedChunk.size(); ++i) {
				RadiationChunk rc = rwsd.loadedChunk.get(i);
				for (int j = 0; j < rc.zones.size(); ++j) {
					RadiationZone rz = rc.zones.get(j);
					double distance = rz.distanceTo(event.player.posX, event.player.posY, event.player.posZ);
					if (distance <= rz.radius) {
						rad += (int)((1F - ((float)distance / rz.radius)) * (float)rz.radiationMax);
					}
				}
			}

			if (rad > 0) sp.setParameter(Parameters.RADIATION, sp.getParameter(Parameters.RADIATION) + rad);
			System.out.println(sp.getParameter(Parameters.RADIATION));
			break;
		default:break;
		}
	}

}
