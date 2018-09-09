package timaxa007.stalker.radiation;

public class RadiationZone {

	public final double posX, posY, posZ;
	public final float radius;
	public final int radiationMax;
	//Easing - LINEAR
	//public final AxisAlignedBB aabb;
	/*this.aabb = AxisAlignedBB.getBoundingBox(
	posX - radius, posY - radius, posZ - radius,
	posX + radius, posY + radius, posZ + radius);*/

	public RadiationZone(final double posX, final double posY, final double posZ, final float radius, final int radiationMax) {
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.radius = radius;
		this.radiationMax = radiationMax;
	}

	@Deprecated
	public RadiationZone(final net.minecraft.util.Vec3 vec, final float radius, final int radiationMax) {
		this.posX = vec.xCoord;
		this.posY = vec.yCoord;
		this.posZ = vec.zCoord;
		this.radius = radius;
		this.radiationMax = radiationMax;
	}

	public double distanceTo(final double posX, final double posY, final double posZ) {
		double disX = posX - this.posX;
		double disY = posY - this.posY;
		double disZ = posZ - this.posZ;
		return Math.sqrt(disX * disX + disY * disY + disZ * disZ);
	}

	public double distanceTo(final net.minecraft.util.Vec3 vec3) {
		double disX = vec3.xCoord - this.posX;
		double disY = vec3.yCoord - this.posY;
		double disZ = vec3.zCoord - this.posZ;
		return Math.sqrt(disX * disX + disY * disY + disZ * disZ);
	}

}
