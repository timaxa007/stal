package timaxa007.stalker.radiation;

import java.util.ArrayList;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.util.Constants.NBT;

public class RadiationWSD extends WorldSavedData {

	public static final String ID = "RadiationWSD";
	private World world;
	public final ArrayList<RadiationChunk>
	loadedChunk = new ArrayList<RadiationChunk>(),
	unloadedChunk = new ArrayList<RadiationChunk>();
	public final ArrayList<RadiationPoint> radiationPointsWorld = new ArrayList<RadiationPoint>();

	public RadiationWSD(String tag) {
		super(tag);
	}

	@Deprecated
	public void createRadiationPoint(final net.minecraft.util.Vec3 vec3, final float radius, final int radMax) {
		createRadiationPoint(vec3.xCoord, vec3.yCoord, vec3.zCoord, radius, radMax);
	}

	public void createRadiationPoint(final double xCoord, final double yCoord, final double zCoord, final float radius, final int radMax) {
		if ((int)radius / 16 < 8)
			createRadiationPointChunk(xCoord, yCoord, zCoord, radius, radMax);
		else
			radiationPointsWorld.add(new RadiationPoint(xCoord, yCoord, zCoord, radius, radMax));
	}
	
	@Deprecated
	public void createRadiationPointChunk(final net.minecraft.util.Vec3 vec3, final float radius, final int radMax) {
		createRadiationPointChunk(vec3.xCoord, vec3.yCoord, vec3.zCoord, radius, radMax);
	}

	public void createRadiationPointChunk(final double xCoord, final double yCoord, final double zCoord, final float radius, final int radMax) {
		int chunkX = (int)xCoord / 16;
		int chunkZ = (int)zCoord / 16;
		RadiationChunk rc = null;

		if (rc == null && !loadedChunk.isEmpty())
			for (int i = 0; i < loadedChunk.size(); ++i) {
				RadiationChunk rpc = loadedChunk.get(i);
				if (rpc.chunkX == chunkX && rpc.chunkZ == chunkZ) {
					rc = rpc;
					break;
				}
			}

		if (rc == null && !unloadedChunk.isEmpty())
			for (int i = 0; i < unloadedChunk.size(); ++i) {
				RadiationChunk rpc = unloadedChunk.get(i);
				if (rpc.chunkX == chunkX && rpc.chunkZ == chunkZ) {
					rc = rpc;
					break;
				}
			}

		if (rc == null) rc = new RadiationChunk(chunkX, chunkZ);

		if (rc != null) {
			rc.points.add(new RadiationPoint(xCoord, yCoord, zCoord, radius, radMax));
			if (world.getChunkProvider().chunkExists(chunkX, chunkZ))
				loadedChunk.add(rc);
			else
				unloadedChunk.add(rc);
		}

	}

	public void chunkLoad(Chunk chunk) {
		if (unloadedChunk.isEmpty()) return;
		for (int i = 0; i < unloadedChunk.size(); ++i) {
			RadiationChunk rpc = unloadedChunk.get(i);
			if (rpc.chunkX == chunk.xPosition && rpc.chunkZ == chunk.zPosition) {
				loadedChunk.add(rpc);
				unloadedChunk.remove(i);
				break;
			}
		}
	}

	public void chunkUnload(Chunk chunk) {
		if (loadedChunk.isEmpty()) return;
		for (int i = 0; i < loadedChunk.size(); ++i) {
			RadiationChunk rpc = loadedChunk.get(i);
			if (rpc.chunkX == chunk.xPosition && rpc.chunkZ == chunk.zPosition) {
				unloadedChunk.add(rpc);
				loadedChunk.remove(i);
				break;
			}
		}
	}

	public static RadiationWSD get(final World world) {
		if (world.mapStorage == null) return null;
		RadiationWSD data = (RadiationWSD)world.mapStorage.loadData(RadiationWSD.class, ID);
		if (data == null) {
			data = new RadiationWSD(ID);
			data.markDirty();
			world.mapStorage.setData(ID, data);
		}
		data.world = world;
		return data;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		if (!nbt.hasKey("zc", NBT.TAG_LIST)) return;
		unloadedChunk.clear();
		loadedChunk.clear();
		NBTTagList list = nbt.getTagList("zc", NBT.TAG_COMPOUND);
		NBTTagCompound tag = null;
		for (int i = 0; i < list.tagCount(); ++i) {
			tag = list.getCompoundTagAt(i);
			if (!tag.hasKey("points", NBT.TAG_LIST) ||
					!tag.hasKey("chunkX", NBT.TAG_INT) || !tag.hasKey("chunkZ", NBT.TAG_INT)) continue;
			RadiationChunk rc = new RadiationChunk(tag.getInteger("chunkX"), tag.getInteger("chunkZ"));
			NBTTagList points = tag.getTagList("points", NBT.TAG_COMPOUND);
			NBTTagCompound point = null;
			for (int j = 0; j < points.tagCount(); ++j) {
				point = points.getCompoundTagAt(j);
				rc.points.add(new RadiationPoint(
						point.getDouble("x"),
						point.getDouble("y"),
						point.getDouble("z"),
						point.getFloat("r"),
						point.getInteger("radMax")
						));
			}
			unloadedChunk.add(rc);
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		NBTTagList list = new NBTTagList();
		NBTTagCompound tag = null;

		if (!unloadedChunk.isEmpty())
			for (int i = 0; i < unloadedChunk.size(); ++i) {
				RadiationChunk rpc = unloadedChunk.get(i);
				if (rpc.points.isEmpty()) continue;

				tag = new NBTTagCompound();

				tag.setInteger("chunkX", rpc.chunkX);
				tag.setInteger("chunkZ", rpc.chunkZ);

				NBTTagList points = new NBTTagList();
				NBTTagCompound point = null;
				for (int j = 0; j < rpc.points.size(); ++j) {
					point = new NBTTagCompound();
					RadiationPoint rp = rpc.points.get(j);
					point.setDouble("x", rp.posX);
					point.setDouble("y", rp.posY);
					point.setDouble("z", rp.posZ);
					point.setFloat("r", rp.radius);
					point.setInteger("radMax", rp.radiationMax);
					points.appendTag(point);
				}
				tag.setTag("points", points);

				list.appendTag(tag);
			}

		if (!loadedChunk.isEmpty())
			for (int i = 0; i < loadedChunk.size(); ++i) {
				RadiationChunk rpc = loadedChunk.get(i);
				if (rpc.points.isEmpty()) continue;

				tag = new NBTTagCompound();

				tag.setInteger("chunkX", rpc.chunkX);
				tag.setInteger("chunkZ", rpc.chunkZ);

				NBTTagList points = new NBTTagList();
				NBTTagCompound point = null;
				for (int j = 0; j < rpc.points.size(); ++j) {
					point = new NBTTagCompound();
					RadiationPoint rp = rpc.points.get(j);
					point.setDouble("x", rp.posX);
					point.setDouble("y", rp.posY);
					point.setDouble("z", rp.posZ);
					point.setFloat("r", rp.radius);
					point.setInteger("radMax", rp.radiationMax);
					points.appendTag(point);
				}
				if (points.tagCount() > 0)
					tag.setTag("points", points);
				list.appendTag(tag);
			}

		if (list.tagCount() > 0) nbt.setTag("zc", list);
	}

}
