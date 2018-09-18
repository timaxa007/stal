package timaxa007.stalker.client;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import timaxa007.stalker.ProxyCommon;

public class ProxyClient extends ProxyCommon {

	@Override
	public void preInit() {

	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return super.getServerGuiElement(ID, player, world, x, y, z);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return super.getClientGuiElement(ID, player, world, x, y, z);
	}

}
