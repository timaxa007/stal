package timaxa007.stalker.register;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants.NBT;

public abstract class ItemStalker extends Item {

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean flag) {
		if (!flag) return;
		if (itemStack.hasTagCompound()) {
			int n = 0;
			NBTTagCompound nbt = itemStack.getTagCompound();

			for (int i = 0; i < 2; ++i) {

				if (i == 0) {
					if (itemStack.getTagCompound().hasKey("active")) {
						list.add("Active:");
						nbt = itemStack.getTagCompound().getCompoundTag("active");
					} else continue;
				} else {
					if (itemStack.getTagCompound().hasKey("passive")) {
						list.add("Passive:");
						nbt = itemStack.getTagCompound().getCompoundTag("passive");
					} else continue;
				}

				for (Parameters key : Parameters.values()) {
					if ((n = getParameter(nbt, key)) != 0) {
						if (key.getProtection())
							list.add("- " + key.getID() + ": " + ((float)n / 100000F) + "%");
						else
							list.add("- " + key.getID() + ": " + n + "");
						n = 0;
					}
				}
				/*
				if ((n = getParameter(nbt, Parameters.IMPACT)) != 0) {list.add("- impact: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_IMPACT)) != 0) {
					list.add("- protection_impact: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.WOUND_HEAL)) != 0) {list.add("- wound_heal: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_WOUND_HEAL)) != 0) {
					list.add("- protection_wound_heal: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.EXPLOSION)) != 0) {list.add("- explosion: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_EXPLOSION)) != 0) {
					list.add("- protection_explosion: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.FIREARMS)) != 0) {list.add("- firearms: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_FIREARMS)) != 0) {
					list.add("- protection_firearms: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.BURN)) != 0) {list.add("- burn: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_BURN)) != 0) {
					list.add("- protection_burn: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.CHEMICAL)) != 0) {list.add("- chemical: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_CHEMICAL)) != 0) {
					list.add("- protection_chemical: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.ELECTRIC)) != 0) {list.add("- electric: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_ELECTRIC)) != 0) {
					list.add("- protection_electric: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.RADIATION)) != 0) {list.add("- radiation: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_RADIATION)) != 0) {
					list.add("- protection_radiation: " + ((float)n / 100000F) + "%");
					n = 0;}

				if ((n = getParameter(nbt, Parameters.PSY)) != 0) {list.add("- psy: " + n);n = 0;}
				if ((n = getParameter(nbt, Parameters.PROTECTION_PSY)) != 0) {
					list.add("- protection_psy: " + ((float)n / 100000F) + "%");
					n = 0;
				}

				if ((n = getParameter(nbt, Parameters.WEIGHT_MAX)) != 0) {
					list.add("- weight_max: " + n);
					n = 0;
				}
				 */
			}
		}

	}
	/*
	@Deprecated
	public static ItemStack addNBT(ItemStack itemStack,

			int active_impact, int active_protection_impact,
			int active_wound_heal, int active_protection_wound_heal,
			int active_explosion, int active_protection_explosion,
			int active_firearms, int active_protection_firearms,
			int active_burn, int active_protection_burn,
			int active_chemical, int active_protection_chemical,
			int active_electric, int active_protection_electric,
			int active_radiation, int active_protection_radiation,
			int active_psy, int active_protection_psy,
			int active_weight_max,

			int passive_impact, int passive_protection_impact,
			int passive_wound_heal, int passive_protection_wound_heal,
			int passive_explosion, int passive_protection_explosion,
			int passive_firearms, int passive_protection_firearms,
			int passive_burn, int passive_protection_burn,
			int passive_chemical, int passive_protection_chemical,
			int passive_electric, int passive_protection_electric,
			int passive_radiation, int passive_protection_radiation,
			int passive_psy, int passive_protection_psy,
			int passive_weight_max

			) {
		if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

		NBTTagCompound active = addNBT(new NBTTagCompound(), 
				active_impact, active_protection_impact,
				active_wound_heal, active_protection_wound_heal,
				active_explosion, active_protection_explosion,
				active_firearms, active_protection_firearms,
				active_burn, active_protection_burn,
				active_chemical, active_protection_chemical,
				active_electric, active_protection_electric,
				active_radiation, active_protection_radiation,
				active_psy, active_protection_psy,
				active_weight_max
				);
		if (!active.hasNoTags()) itemStack.getTagCompound().setTag("active", active);

		NBTTagCompound passive = addNBT(new NBTTagCompound(), 
				passive_impact, passive_protection_impact,
				passive_wound_heal, passive_protection_wound_heal,
				passive_explosion, passive_protection_explosion,
				passive_firearms, passive_protection_firearms,
				passive_burn, passive_protection_burn,
				passive_chemical, passive_protection_chemical,
				passive_electric, passive_protection_electric,
				passive_radiation, passive_protection_radiation,
				passive_psy, passive_protection_psy,
				passive_weight_max);
		if (!passive.hasNoTags()) itemStack.getTagCompound().setTag("passive", passive);

		return itemStack;
	}

	@Deprecated
	public static NBTTagCompound addNBT(NBTTagCompound nbt,
			int impact, int protection_impact,
			int wound_heal, int protection_wound_heal,
			int explosion, int protection_explosion,
			int firearms, int protection_firearms,
			int burn, int protection_burn,
			int chemical, int protection_chemical,
			int electric, int protection_electric,
			int radiation, int protection_radiation,
			int psy, int protection_psy,
			int weight_max
			) {

		if (impact != 0) setParameter(nbt, Parameters.IMPACT, impact);
		if (protection_impact != 0) setParameter(nbt, Parameters.PROTECTION_IMPACT, protection_impact);

		if (wound_heal != 0) setParameter(nbt, Parameters.WOUND_HEAL, wound_heal);
		if (protection_wound_heal != 0) setParameter(nbt, Parameters.PROTECTION_WOUND_HEAL, protection_wound_heal);

		if (explosion != 0) setParameter(nbt, Parameters.EXPLOSION, explosion);
		if (protection_explosion != 0) setParameter(nbt, Parameters.PROTECTION_EXPLOSION, protection_explosion);

		if (firearms != 0) setParameter(nbt, Parameters.FIREARMS, firearms);
		if (protection_firearms != 0) setParameter(nbt, Parameters.PROTECTION_FIREARMS, protection_firearms);

		if (burn != 0) setParameter(nbt, Parameters.BURN, burn);
		if (protection_burn != 0) setParameter(nbt, Parameters.PROTECTION_BURN, protection_burn);

		if (chemical != 0) setParameter(nbt, Parameters.CHEMICAL, chemical);
		if (protection_chemical != 0) setParameter(nbt, Parameters.PROTECTION_CHEMICAL, protection_chemical);

		if (electric != 0) setParameter(nbt, Parameters.ELECTRIC, electric);
		if (protection_electric != 0) setParameter(nbt, Parameters.PROTECTION_ELECTRIC, protection_electric);

		if (radiation != 0) setParameter(nbt, Parameters.RADIATION, radiation);
		if (protection_radiation != 0) setParameter(nbt, Parameters.PROTECTION_RADIATION, protection_radiation);

		if (psy != 0) setParameter(nbt, Parameters.PSY, psy);
		if (protection_psy != 0) setParameter(nbt, Parameters.PROTECTION_PSY, protection_psy);

		if (weight_max != 0) setParameter(nbt, Parameters.WEIGHT_MAX, weight_max);

		return nbt;
	}
	 */
	protected static ItemStack setParameter(ItemStack itemStack, Parameters key, int n, boolean isActive) {
		return setParameter(itemStack, key.getID(), n, isActive);
	}

	protected static ItemStack setParameter(ItemStack itemStack, String key, int n, boolean isActive) {
		if (!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
		NBTTagCompound nbt;
		if (isActive && itemStack.getTagCompound().hasKey("active", NBT.TAG_COMPOUND)) {
			setParameter(itemStack.getTagCompound().getCompoundTag("active"), key, n);
			return itemStack;
		}
		if (!isActive && itemStack.getTagCompound().hasKey("passive", NBT.TAG_COMPOUND)) {
			setParameter(itemStack.getTagCompound().getCompoundTag("passive"), key, n);
			return itemStack;
		}
		//setParameter(itemStack.getTagCompound(), key, n);
		nbt = setParameter(new NBTTagCompound(), key, n);
		if (nbt.hasNoTags()) return itemStack;
		itemStack.getTagCompound().setTag(isActive ? "active" : "passive", nbt);
		return itemStack;
	}

	@Deprecated
	protected static NBTTagCompound setParameter(NBTTagCompound nbt, Parameters key, int n) {
		return setParameter(nbt, key.getID(), n);
	}

	@Deprecated
	protected static NBTTagCompound setParameter(NBTTagCompound nbt, String key, int n) {
		if (n == 0) return nbt;
		if (nbt == null) nbt = new NBTTagCompound();
		else if (n >= Byte.MIN_VALUE && n <= Byte.MAX_VALUE) nbt.setByte(key, (byte)n);
		else if (n >= Short.MIN_VALUE && n <= Short.MAX_VALUE) nbt.setShort(key, (short)n);
		else if (n >= Integer.MIN_VALUE && n <= Integer.MAX_VALUE) nbt.setInteger(key, n);
		return nbt;
	}

	protected static int getParameter(ItemStack itemStack, Parameters key, boolean isActive) {
		return getParameter(itemStack, key.getID(), isActive);
	}

	protected static int getParameter(ItemStack itemStack, String key, boolean isActive) {
		if (!itemStack.hasTagCompound()) return 0;
		if (isActive && itemStack.getTagCompound().hasKey("active"))
			return getParameter(itemStack.getTagCompound().getCompoundTag("active"), key);
		if (!isActive && itemStack.getTagCompound().hasKey("passive"))
			return getParameter(itemStack.getTagCompound().getCompoundTag("passive"), key);
		return getParameter(itemStack.getTagCompound(), key);
	}

	@Deprecated
	protected static int getParameter(NBTTagCompound nbt, Parameters key) {
		return getParameter(nbt, key.getID());
	}

	@Deprecated
	protected static int getParameter(NBTTagCompound nbt, String key) {
		if (nbt == null) return 0;
		if (nbt.hasKey(key, NBT.TAG_BYTE)) return (int)nbt.getByte(key);
		if (nbt.hasKey(key, NBT.TAG_SHORT)) return (int)nbt.getShort(key);
		if (nbt.hasKey(key, NBT.TAG_INT)) return nbt.getInteger(key);
		return 0;
	}

}
