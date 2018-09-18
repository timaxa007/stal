package timaxa007.stalker;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionAttackDamage;

public class PotionBleeding extends PotionAttackDamage {

	public PotionBleeding(int id, boolean isBadEffect, int liquidColor) {
		super(id, isBadEffect, liquidColor);
	}

	@Override
	public boolean isReady(int p_76397_1_, int p_76397_2_) {
		return true;
	}

	@Override
	public void performEffect(EntityLivingBase entity, int amplifier) {
		if (amplifier % 10 == 0) {
			//entity.attackEntityFrom(StalkerMod.damage_bleeding, 1F);
		}
	}
}
