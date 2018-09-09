package timaxa007.stalker.register;

public enum Parameters {

	IMPACT("impact", false),
	PROTECTION_IMPACT("prot_impact", true),

	WOUND_HEAL("wound_heal", false),
	PROTECTION_WOUND_HEAL("prot_wound_heal", true),

	EXPLOSION("explosion", false),
	PROTECTION_EXPLOSION("prot_explosion", true),

	FIREARMS("firearms", false),
	PROTECTION_FIREARMS("prot_firearms", true),

	BURN("burn", false),
	PROTECTION_BURN("prot_burn", true),

	CHEMICAL("chemical", false),
	PROTECTION_CHEMICAL("prot_chemical", true),

	ELECTRIC("electric", false),
	PROTECTION_ELECTRIC("prot_electric", true),

	RADIATION("radiation", false),
	PROTECTION_RADIATION("prot_radiation", true),

	PSY("psy", false),
	PROTECTION_PSY("prot_psy", true),

	WEIGHT_MAX("weight_max", false),
	JUMP_HEIGHT("jump_height", true),

	SATIETY("satiety", false),
	STAMINA("stamina", false),

	POSION("posion", false),
	PROTECTION_POSION("prot_posion", true),
	BOOSE("boose", false),
	PROTECTION_BOOSE("prot_boose", true),
	TOXIN("toxin", false),
	PROTECTION_TOXIN("prot_toxin", true);

	private final String id;
	private final boolean protection;

	Parameters(String id, boolean protection) {
		this.id = id;
		this.protection = protection;
	}

	public String getID() {
		return id;
	}

	public boolean getProtection() {
		return protection;
	}

}
