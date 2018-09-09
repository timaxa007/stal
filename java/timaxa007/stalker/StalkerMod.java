package timaxa007.stalker;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import timaxa007.stalker.entity.EntityAnomaly;
import timaxa007.stalker.entity.EntityTest;
import timaxa007.stalker.event.EventsFML;
import timaxa007.stalker.event.EventsForge;
import timaxa007.stalker.item.ItemDetector;
import timaxa007.stalker.item.ItemMetaMorph;
import timaxa007.stalker.network.SyncStalkerPlayerMessage;
import timaxa007.stalker.network.TraderEntityIdMessage;
import timaxa007.zone_saved.ZoneSavedCommand;

@Mod(modid = StalkerMod.MODID, name = StalkerMod.NAME, version = StalkerMod.VERSION)
public class StalkerMod {

	public static final String
	MODID = "stalker",
	NAME = "Stalker Mod",
	VERSION = "0.021";

	@Mod.Instance(MODID)
	public static StalkerMod instance;

	@SidedProxy(modId = MODID,
			serverSide = "timaxa007.stalker.ProxyCommon",
			clientSide = "timaxa007.stalker.client.ProxyClient")
	public static ProxyCommon proxy;

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

	public static final CreativeTabs tabStalker = new CreativeTabs("tab_stalker") {
		@Override public Item getTabIconItem() {
			return metamorph;
		}
	};

	public static Item
	detector,
	metamorph;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		network.registerMessage(SyncStalkerPlayerMessage.Handler.class, SyncStalkerPlayerMessage.class, 0, Side.CLIENT);
		network.registerMessage(SyncStalkerPlayerMessage.Handler.class, SyncStalkerPlayerMessage.class, 0, Side.SERVER);
		network.registerMessage(TraderEntityIdMessage.Handler.class, TraderEntityIdMessage.class, 1, Side.CLIENT);
		network.registerMessage(TraderEntityIdMessage.Handler.class, TraderEntityIdMessage.class, 1, Side.SERVER);
		MinecraftForge.EVENT_BUS.register(new EventsForge());
		FMLCommonHandler.instance().bus().register(new EventsFML());

		detector = new ItemDetector().setUnlocalizedName(MODID + ".detector").setTextureName(MODID + ":detector").setCreativeTab(tabStalker).setHasSubtypes(true).setMaxDamage(0);
		GameRegistry.registerItem(detector, "item_detector");

		metamorph = new ItemMetaMorph().setUnlocalizedName(MODID + ".metamorph").setTextureName(MODID + ":metamorph").setCreativeTab(tabStalker).setHasSubtypes(true).setMaxDamage(0).setMaxStackSize(1);
		GameRegistry.registerItem(metamorph, "item_metamorph");

		EntityRegistry.registerModEntity(EntityAnomaly.class, "EntityAnomaly", 0, instance, 128, 1, true);
		EntityRegistry.registerModEntity(EntityTest.class, "EntityTest", 1, instance, 64, 3, true);

		NetworkRegistry.INSTANCE.registerGuiHandler(instance, proxy);
		proxy.preInit();
	}

	@Mod.EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		event.registerServerCommand(new RadiationCommand());
	}

}
