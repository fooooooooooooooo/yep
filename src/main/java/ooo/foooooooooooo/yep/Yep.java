package ooo.foooooooooooo.yep;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkCheckHandler;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.util.ResourceLocation;
import ooo.foooooooooooo.yep.proxy.CommonProxy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

@Mod(modid = Tags.MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]")
public class Yep {
    public static final ResourceLocation PLUGIN_CHANNEL = new ResourceLocation("velocity", Tags.MODID);

    public static final Logger LOGGER = LogManager.getLogger(Tags.MODID);

    @SidedProxy(clientSide = "ooo.foooooooooooo.yep.proxy.ClientProxy", serverSide = "ooo.foooooooooooo.yep.proxy.ServerProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    // make sure the client doesn't think it needs yep to connect
    @NetworkCheckHandler
    public boolean checkModList(Map<String, String> modList, Side side) {
        return true;
    }
}
