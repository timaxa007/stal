package timaxa007.stalker;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import timaxa007.stalker.radiation.RadiationWSD;

public class RadiationCommand extends CommandBase {

	private static final String[] w = new String[]{
			"create",
			"get"
	};

	@Override
	public String getCommandName() {
		return "radiation";
	}

	/**Return the required permission level for this command.**/
	@Override
	public int getRequiredPermissionLevel() {
		return 2;
	}

	@Override
	public String getCommandUsage(ICommandSender ics) {
		return "commands.radiation.usage";
	}

	@Override
	public void processCommand(ICommandSender ics, String[] args) {
		if (args.length <= 0) {
			throw new WrongUsageException(getCommandUsage(ics), new Object[0]);
		} else {
			EntityPlayerMP player = /*args.length == 0 ? getCommandSenderAsPlayer(ics) : */getPlayer(ics, args[0]);

			RadiationWSD zsw = RadiationWSD.get(player.worldObj);
			if (zsw == null) return;

			if (args[1].equalsIgnoreCase(w[0]) && args.length >= 4) {
				//zsw.createZoneSaved(args[2], (int)player.posX, (int)player.posY, (int)player.posZ);
				zsw.createRadiationZone(player.posX, player.posY, player.posZ, Float.parseFloat(args[2]), Integer.parseInt(args[3]));
			}

			else if (args[1].equalsIgnoreCase(w[1])) {
				if (args.length >= 3) {

				} else {

				}
			}

		}
	}

	/**Adds the strings available in this command to the given list of tab completion options.**/
	@Override
	public List addTabCompletionOptions(ICommandSender ics, String[] args) {
		switch(args.length) {
		case 1:return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
		case 2:return getListOfStringsMatchingLastWord(args, w);
		default:return null;
		}
	}

	/**Return whether the specified command parameter index is a username parameter.**/
	@Override
	public boolean isUsernameIndex(String[] args, int id) {
		return id == 0;
	}

}
