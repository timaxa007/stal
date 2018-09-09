package timaxa007.stalker;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.common.util.Constants.NBT;
import timaxa007.stalker.register.Parameters;

public class StalkerPlayerIEEP implements IExtendedEntityProperties {

	private static final String ID = "StalerPlayerIEEP";
	private EntityPlayer player;
	public Entity npc;

	private int
	stamina,

	posion,
	boose,//alco
	toxin,//К примеру, передоз медицинскими препаратами.//For example, overdose with medical drugs.

	immunityPosion,
	immunityBoose,
	immunityToxin,

	impact,//Удар
	woundHeal,//Лечение ран
	explosion,//взрыв
	firearms,//огнестрельное оружие
	burn,//Ожог
	chemical,//химикат
	electric,//электрический
	radiation,//радиация
	psy,//Пси

	immunityImpact,
	immunityWoundHeal,
	immunityExplosion,
	immunityFirearms,
	immunityBurn,
	immunityChemical,
	immunityElectric,
	immunityRadiation,
	immunityPsy,

	transportableWeightMax

	;

	public void setParameter(Parameters key, int n) {
		switch(key) {
		case IMPACT:
			impact = n;
			break;
		case PROTECTION_IMPACT:
			immunityImpact = n;
			break;
		case WOUND_HEAL:
			woundHeal = n;
			break;
		case PROTECTION_WOUND_HEAL:
			immunityWoundHeal = n;
			break;
		case EXPLOSION:
			explosion = n;
			break;
		case PROTECTION_EXPLOSION:
			immunityExplosion = n;
			break;
		case FIREARMS:
			firearms = n;
			break;
		case PROTECTION_FIREARMS:
			immunityFirearms = n;
			break;
		case BURN:
			burn = n;
			break;
		case PROTECTION_BURN:
			immunityBurn = n;
			break;
		case CHEMICAL:
			chemical = n;
			break;
		case PROTECTION_CHEMICAL:
			immunityChemical = n;
			break;
		case ELECTRIC:
			electric = n;
			break;
		case PROTECTION_ELECTRIC:
			immunityElectric = n;
			break;
		case RADIATION:
			radiation = n;
			break;
		case PROTECTION_RADIATION:
			immunityRadiation = n;
			break;
		case PSY:
			psy = n;
			break;
		case PROTECTION_PSY:
			immunityPsy = n;
			break;
		case WEIGHT_MAX:
			transportableWeightMax = n;
			break;
		case JUMP_HEIGHT:
			//impact = n;
			break;
		case SATIETY:
			//impact = n;
			break;
		case STAMINA:
			stamina = n;
			break;
		case POSION:
			posion = n;
			break;
		case PROTECTION_POSION:
			immunityPosion = n;
			break;
		case BOOSE:
			boose = n;
			break;
		case PROTECTION_BOOSE:
			immunityBoose = n;
			break;
		case TOXIN:
			toxin = n;
			break;
		case PROTECTION_TOXIN:
			immunityToxin = n;
			break;
		default:break;
		}
	}

	public int getParameter(Parameters key) {
		switch(key) {
		case IMPACT:
			return impact;
		case PROTECTION_IMPACT:
			return immunityImpact;
		case WOUND_HEAL:
			return woundHeal;
		case PROTECTION_WOUND_HEAL:
			return immunityWoundHeal;
		case EXPLOSION:
			return explosion;
		case PROTECTION_EXPLOSION:
			return immunityExplosion;
		case FIREARMS:
			return firearms;
		case PROTECTION_FIREARMS:
			return immunityFirearms;
		case BURN:
			return burn;
		case PROTECTION_BURN:
			return immunityBurn;
		case CHEMICAL:
			return chemical;
		case PROTECTION_CHEMICAL:
			return immunityChemical;
		case ELECTRIC:
			return electric;
		case PROTECTION_ELECTRIC:
			return immunityElectric;
		case RADIATION:
			return radiation;
		case PROTECTION_RADIATION:
			return immunityRadiation;
		case PSY:
			return psy;
		case PROTECTION_PSY:
			return immunityPsy;
		case WEIGHT_MAX:
			return transportableWeightMax;
		case JUMP_HEIGHT:
			return -1;
		case SATIETY:
			return -1;
		case STAMINA:
			return stamina;
		case POSION:
			return posion;
		case PROTECTION_POSION:
			return immunityPosion;
		case BOOSE:
			return boose;
		case PROTECTION_BOOSE:
			return immunityBoose;
		case TOXIN:
			return toxin;
		case PROTECTION_TOXIN:
			return immunityToxin;
		default:return -1;
		}
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Radiation", radiation);
		compound.setTag(ID, nbt);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		if (compound.hasKey(ID, NBT.TAG_COMPOUND)) {
			NBTTagCompound nbt = compound.getCompoundTag(ID);
			if (nbt.hasKey("rad", NBT.TAG_INT)) radiation = nbt.getInteger("Radiation");
		}
	}

	@Override
	public void init(Entity entity, World world) {
		if (entity instanceof EntityPlayer) player = (EntityPlayer)entity;
	}

	public static String reg(EntityPlayer player) {
		return player.registerExtendedProperties(StalkerPlayerIEEP.ID, new StalkerPlayerIEEP());
	}

	public static StalkerPlayerIEEP get(EntityPlayer player) {
		return (StalkerPlayerIEEP)player.getExtendedProperties(StalkerPlayerIEEP.ID);
	}

}
