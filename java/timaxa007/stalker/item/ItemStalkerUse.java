package timaxa007.stalker.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import timaxa007.stalker.StalkerPlayerIEEP;
import timaxa007.stalker.register.ItemStalker;
import timaxa007.stalker.register.Parameters;

public class ItemStalkerUse extends ItemStalker {

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		StalkerPlayerIEEP stalkerPlayer = StalkerPlayerIEEP.get(player);
		if (stalkerPlayer == null) return itemStack;

		if (itemStack.getTagCompound().hasKey("active")) {
			NBTTagCompound nbt = itemStack.getTagCompound().getCompoundTag("active");
			int n = 0;

			for (Parameters key : Parameters.values()) {
				if ((n = getParameter(nbt, key)) != 0) {
					if (key.getProtection()) continue;

					
					n = 0;
				}
			}


		}

		player.getFoodStats().addStats(0, 0F);

		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);

		if (!player.capabilities.isCreativeMode) --itemStack.stackSize;
		return itemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.eat;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (player.canEat(false)) player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		return itemStack;
	}

}
