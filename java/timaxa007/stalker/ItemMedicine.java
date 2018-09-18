package timaxa007.stalker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemMedicine extends Item {

	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		PotionEffect pe = player.getActivePotionEffect(StalkerMod.potion_bleeding);
		if (pe != null) {//то что он есть и каждые полСекунды
			int amplifier = pe.getAmplifier();
			player.removePotionEffect(StalkerMod.potion_bleeding.id);
			if (amplifier >= 620)
				player.addPotionEffect(new PotionEffect(StalkerMod.potion_bleeding.id, amplifier - 600));
			//world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
			if (!player.capabilities.isCreativeMode) --itemStack.stackSize;
		}
		return itemStack;
	}

	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 32;
	}

	@Override
	public EnumAction getItemUseAction(ItemStack itemStack) {
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		player.setItemInUse(itemStack, getMaxItemUseDuration(itemStack));
		return itemStack;
	}

	/*
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		PotionEffect pe = player.getActivePotionEffect(StalkerMod.potion_bleeding);
		if (pe != null) {//то что он есть и каждые полСекунды
			int amplifier = pe.getAmplifier();
			player.removePotionEffect(StalkerMod.potion_bleeding.id);
			if (amplifier >= 80)
				player.addPotionEffect(new PotionEffect(StalkerMod.potion_bleeding.id, amplifier - 60));
		if (!player.capabilities.isCreativeMode) --itemStack.stackSize;
		}
		return super.onItemRightClick(itemStack, world, player);
	}
	 */
}
