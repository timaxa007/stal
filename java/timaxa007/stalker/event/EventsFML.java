package timaxa007.stalker.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import net.minecraft.potion.PotionEffect;
import timaxa007.stalker.StalkerMod;
import timaxa007.stalker.StalkerPlayerIEEP;
import timaxa007.stalker.radiation.RadiationChunk;
import timaxa007.stalker.radiation.RadiationPoint;
import timaxa007.stalker.radiation.RadiationWSD;
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
				for (int j = 0; j < rc.points.size(); ++j) {
					RadiationPoint rp = rc.points.get(j);
					double distance = rp.distanceTo(event.player.posX, event.player.posY, event.player.posZ);
					if (distance <= rp.radius) {
						rad += (int)((1F - ((float)distance / rp.radius)) * (float)rp.radiationMax);
					}
				}
			}

			if (rad > 0) sp.setParameter(Parameters.RADIATION, sp.getParameter(Parameters.RADIATION) + rad);
			System.out.println(sp.getParameter(Parameters.RADIATION));
			break;
		default:break;
		}
	}
/*
    @SubscribeEvent
    public void onPlayerTickEvent(TickEvent.PlayerTickEvent event) {
        switch(event.phase) {
        case END:
            PotionEffect pe = event.player.getActivePotionEffect(StalkerMod.potion_bleeding);
            if (pe != null && pe.getAmplifier() % 10 == 0) {//то что он есть и каждые полСекунды
                event.player.attackEntityFrom(StalkerMod.damage_bleeding, 1F);
            }
            break;
        default:break;
        }
    }
*/
}
