package timaxa007.stalker.item;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import timaxa007.stalker.register.ItemStalker;
import timaxa007.stalker.register.Parameters;

public class ItemMetaMorph extends ItemStalker {
	/*
	@SideOnly(Side.CLIENT)
	private IIcon
	icon_coin_copper, icon_coin_silver, icon_coin_gold,
	icon_bag_empty, icon_bag_coin;
	 */
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		if (player.isSneaking()) {}
		else {

		}
		return super.onItemRightClick(itemStack, world, player);
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt != null) {

		}
		return super.getUnlocalizedName(itemStack);
	}

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {

		super.addInformation(itemStack, player, list, flag);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void getSubItems(Item id, CreativeTabs table, List list) {
		//list.add(new ItemStack(id, 1, 0));
		/*
		list.add(addNBT(new ItemStack(id, 1, 0),
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
				));
		list.add(addNBT(new ItemStack(id, 1, 0),
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
				1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
				));
		list.add(addNBT(new ItemStack(id, 1, 0),
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
				-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1
				));

		list.add(addNBT(new ItemStack(id, 1, 0),
				0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0,
				0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0
				));
		list.add(addNBT(new ItemStack(id, 1, 0),
				1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
				1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0
				));
		 */
		ItemStack item = new ItemStack(id, 1, 0);

		for (int i = -1; i <= 1; ++i) {
			item = new ItemStack(id, 1, 0);
			for (Parameters key : Parameters.values()) {
				setParameter(item, key, i, true);
				setParameter(item, key, i, false);
			}
			list.add(item);
		}

		item = new ItemStack(id, 1, 0);
		for (Parameters key : Parameters.values()) {
			if (key.getProtection()) continue;
			setParameter(item, key, 1, true);
		}
		list.add(item);

		item = new ItemStack(id, 1, 0);
		for (Parameters key : Parameters.values()) {
			if (key.getProtection()) continue;
			setParameter(item, key, 1, false);
		}
		list.add(item);

		item = new ItemStack(id, 1, 0);
		for (Parameters key : Parameters.values()) {
			if (!key.getProtection()) continue;
			setParameter(item, key, 111111, true);
			setParameter(item, key, 111111, false);
		}
		list.add(item);


		list.add(setParameter(new ItemStack(id, 1, 0), Parameters.WEIGHT_MAX, 10, true));
		list.add(setParameter(new ItemStack(id, 1, 0), Parameters.WEIGHT_MAX, 10, false));
		/*
		list.add(addNBT(new ItemStack(id, 1, 0),
				Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE,
				Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE, Byte.MAX_VALUE
				));
		list.add(addNBT(new ItemStack(id, 1, 0),
				Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE,
				Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE, Byte.MIN_VALUE
				));

		list.add(addNBT(new ItemStack(id, 1, 0),
				Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE,
				Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE, Short.MAX_VALUE
				));
		list.add(addNBT(new ItemStack(id, 1, 0),
				Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE,
				Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE, Short.MIN_VALUE
				));

		list.add(addNBT(new ItemStack(id, 1, 0),
				Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE,
				Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE
				));
		list.add(addNBT(new ItemStack(id, 1, 0),
				Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE,
				Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE
				));
		 */
	}

	/*если использовать только текстуру coin'а
	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int pass) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt != null) {
			int money = 0;
			if (nbt.hasKey(KEY, NBT.TAG_BYTE)) money = nbt.getByte(KEY);
			else if (nbt.hasKey(KEY, NBT.TAG_SHORT)) money = nbt.getShort(KEY);
			else if (nbt.hasKey(KEY, NBT.TAG_INT)) money = nbt.getInteger(KEY);
			if (money != 0) {
				if (money < 100) return 0xCC5F00;
				else if (money >= 100 && money < 10000 && money % 100 == 0) return 0xAFAFAF;
				else if (money >= 10000 && money % 10000 == 0) return 0xFFB200;
			}
		}
		return super.getColorFromItemStack(itemStack, pass);
	}
	 */
	/*
	@Override
	@SideOnly(Side.CLIENT)
	public boolean requiresMultipleRenderPasses() {
		return true;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack itemStack, int pass) {
		NBTTagCompound nbt = itemStack.getTagCompound();
		if (nbt != null) {
			int money = 0;
			if (nbt.hasKey(KEY, NBT.TAG_BYTE)) money = nbt.getByte(KEY);
			else if (nbt.hasKey(KEY, NBT.TAG_SHORT)) money = nbt.getShort(KEY);
			else if (nbt.hasKey(KEY, NBT.TAG_INT)) money = nbt.getInteger(KEY);
			if (money != 0) {
				if (money == 1) return icon_coin_copper;
				else if (money == 100) return icon_coin_silver;
				else if (money == 10000) return icon_coin_gold;
				else return icon_bag_coin;
			} else return icon_bag_empty;
		}
		return super.getIcon(itemStack, pass);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister ir) {
		super.registerIcons(ir);
		icon_coin_copper = ir.registerIcon(getIconString() + "_copper");
		icon_coin_silver = ir.registerIcon(getIconString() + "_silver");
		icon_coin_gold = ir.registerIcon(getIconString() + "_gold");
		icon_bag_coin = ir.registerIcon("money2b:bag_coin");
		icon_bag_empty = ir.registerIcon("money2b:bag_empty");
	}
	 */
}
