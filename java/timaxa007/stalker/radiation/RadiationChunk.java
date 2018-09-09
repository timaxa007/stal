package timaxa007.stalker.radiation;

import java.util.ArrayList;

public class RadiationChunk {

	public final int chunkX, chunkZ;
	public final ArrayList<RadiationPoint> points = new ArrayList<RadiationPoint>();

	public RadiationChunk(final int chunkX, final int chunkZ) {
		this.chunkX = chunkX;
		this.chunkZ = chunkZ;
	}

}
