package timaxa007.stalker.radiation;

import net.minecraft.util.AxisAlignedBB;

public class RadiationZone {

	public final int radiationMax;
	public final AxisAlignedBB aabb;
	//Easing - LINEAR

	public RadiationZone(AxisAlignedBB aabb, final int radiationMax) {
		this.aabb = aabb;
		this.radiationMax = radiationMax;
	}

	public RadiationZone(final double xPos, final double yPos, final double zPos, final float radius, final int radiationMax) {
		this.aabb = AxisAlignedBB.getBoundingBox(
				xPos - radius, yPos - radius, zPos - radius,
				xPos + radius, yPos + radius, zPos + radius);
		this.radiationMax = radiationMax;
	}

	public RadiationZone(final double xPosA, final double yPosA, final double zPosA,
			final double xPosB, final double yPosB, final double zPosB, final int radiationMax) {
		this.aabb = AxisAlignedBB.getBoundingBox(
				Math.min(xPosA, xPosB),
				Math.min(yPosA, yPosB),
				Math.min(zPosA, zPosB),
				Math.max(xPosA, xPosB),
				Math.max(yPosA, yPosB),
				Math.max(zPosA, zPosB));
		this.radiationMax = radiationMax;
	}

	@Deprecated
	public RadiationZone(final net.minecraft.util.Vec3 vec, final float radius, final int radiationMax) {
		this.aabb = AxisAlignedBB.getBoundingBox(
				vec.xCoord - radius, vec.yCoord - radius, vec.zCoord - radius,
				vec.xCoord + radius, vec.yCoord + radius, vec.zCoord + radius);
		this.radiationMax = radiationMax;
	}

	public RadiationZone(final net.minecraft.util.Vec3 vecA, final net.minecraft.util.Vec3 vecB, final int radiationMax) {
		this.aabb = AxisAlignedBB.getBoundingBox(
				Math.min(vecA.xCoord, vecB.xCoord),
				Math.min(vecA.yCoord, vecB.yCoord),
				Math.min(vecA.zCoord, vecB.zCoord),
				Math.max(vecA.xCoord, vecB.xCoord),
				Math.max(vecA.yCoord, vecB.yCoord),
				Math.max(vecA.zCoord, vecB.zCoord));
		this.radiationMax = radiationMax;
	}

}
