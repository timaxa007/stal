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

	public RadiationWSD(String tag) {
		super(tag);
	}

	@Deprecated
	public void createRadiationZone(final net.minecraft.util.Vec3 vec3, final float radius, final int radMax) {
		createRadiationZone(vec3.xCoord, vec3.yCoord, vec3.zCoord, radius, radMax);
	}

	public void createRadiationZone(final double xCoord, final double yCoord, final double zCoord, final float radius, final int radMax) {
		int chunkX = (int)xCoord / 16;
		int chunkZ = (int)zCoord / 16;
		RadiationChunk rc = null;

		if (rc == null && !loadedChunk.isEmpty())
			for (int i = 0; i < loadedChunk.size(); ++i) {
				RadiationChunk rzc = loadedChunk.get(i);
				if (rzc.chunkX == chunkX && rzc.chunkZ == chunkZ) {
					rc = rzc;
					break;
				}
			}

		if (rc == null && !unloadedChunk.isEmpty())
			for (int i = 0; i < unloadedChunk.size(); ++i) {
				RadiationChunk rzc = unloadedChunk.get(i);
				if (rzc.chunkX == chunkX && rzc.chunkZ == chunkZ) {
					rc = rzc;
					break;
				}
			}

		if (rc == null) rc = new RadiationChunk(chunkX, chunkZ);

		if (rc != null) {
			rc.zones.add(new RadiationZone(xCoord, yCoord, zCoord, radius, radMax));
			if (world.getChunkProvider().chunkExists(chunkX, chunkZ))
				loadedChunk.add(rc);
			else
				unloadedChunk.add(rc);
		}

	}

	public void chunkLoad(Chunk chunk) {
		if (unloadedChunk.isEmpty()) return;
		for (int i = 0; i < unloadedChunk.size(); ++i) {
			RadiationChunk rzc = unloadedChunk.get(i);
			if (rzc.chunkX == chunk.xPosition && rzc.chunkZ == chunk.zPosition) {
				loadedChunk.add(rzc);
				unloadedChunk.remove(i);
				break;
			}
		}
	}

	public void chunkUnload(Chunk chunk) {
		if (loadedChunk.isEmpty()) return;
		for (int i = 0; i < loadedChunk.size(); ++i) {
			RadiationChunk rzc = loadedChunk.get(i);
			if (rzc.chunkX == chunk.xPosition && rzc.chunkZ == chunk.zPosition) {
				unloadedChunk.add(rzc);
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
			if (!tag.hasKey("zones", NBT.TAG_LIST) ||
					!tag.hasKey("chunkX", NBT.TAG_INT) || !tag.hasKey("chunkZ", NBT.TAG_INT)) continue;
			RadiationChunk rc = new RadiationChunk(tag.getInteger("chunkX"), tag.getInteger("chunkZ"));
			NBTTagList zones = tag.getTagList("zones", NBT.TAG_COMPOUND);
			NBTTagCompound zone = null;
			for (int j = 0; j < zones.tagCount(); ++j) {
				zone = zones.getCompoundTagAt(j);
				rc.zones.add(new RadiationZone(
						zone.getDouble("x"),
						zone.getDouble("y"),
						zone.getDouble("z"),
						zone.getFloat("r"),
						zone.getInteger("radMax")
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
				RadiationChunk rzc = unloadedChunk.get(i);
				if (rzc.zones.isEmpty()) continue;

				tag = new NBTTagCompound();

				tag.setInteger("chunkX", rzc.chunkX);
				tag.setInteger("chunkZ", rzc.chunkZ);

				NBTTagList zones = new NBTTagList();
				NBTTagCompound zone = null;
				for (int j = 0; j < rzc.zones.size(); ++j) {
					zone = new NBTTagCompound();
					RadiationZone rz = rzc.zones.get(j);
					zone.setDouble("x", rz.posX);
					zone.setDouble("y", rz.posY);
					zone.setDouble("z", rz.posZ);
					zone.setFloat("r", rz.radius);
					zone.setInteger("radMax", rz.radiationMax);
					zones.appendTag(zone);
				}
				tag.setTag("zones", zones);

				list.appendTag(tag);
			}

		if (!loadedChunk.isEmpty())
			for (int i = 0; i < loadedChunk.size(); ++i) {
				RadiationChunk rzc = loadedChunk.get(i);
				if (rzc.zones.isEmpty()) continue;

				tag = new NBTTagCompound();

				tag.setInteger("chunkX", rzc.chunkX);
				tag.setInteger("chunkZ", rzc.chunkZ);

				NBTTagList zones = new NBTTagList();
				NBTTagCompound zone = null;
				for (int j = 0; j < rzc.zones.size(); ++j) {
					zone = new NBTTagCompound();
					RadiationZone rz = rzc.zones.get(j);
					zone.setDouble("x", rz.posX);
					zone.setDouble("y", rz.posY);
					zone.setDouble("z", rz.posZ);
					zone.setFloat("r", rz.radius);
					zone.setInteger("radMax", rz.radiationMax);
					zones.appendTag(zone);
				}
				if (zones.tagCount() > 0)
					tag.setTag("zones", zones);
				list.appendTag(tag);
			}

		if (list.tagCount() > 0) nbt.setTag("zc", list);
	}

}
