package timaxa007.stalker;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.PotionAttackDamage;
import net.minecraft.potion.PotionEffect;

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
		PotionEffect pe = entity.getActivePotionEffect(this);
		if (pe == null) return;
		int lvl_20 = pe.getAmplifier() % 20;
		int tt = 200 - (10 * (lvl_20));
		if (tt <= 0)
			entity.attackEntityFrom(StalkerMod.damage_bleeding, 0.5F + (float)(pe.getAmplifier() / 20) / 2F);
		else if (pe.getDuration() % tt == 0)
			entity.attackEntityFrom(StalkerMod.damage_bleeding, 0.5F + (float)(pe.getAmplifier() / 20) / 2F);
	}

}
