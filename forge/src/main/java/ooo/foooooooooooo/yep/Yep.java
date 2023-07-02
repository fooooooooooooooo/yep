package ooo.foooooooooooo.yep;

import net.minecraft.util.Identifier;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.IExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("yep")
public final class Yep {
    public static String MOD_ID = "yep";
    public static Identifier PLUGIN_CHANNEL = new Identifier("velocity", MOD_ID);

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public Yep() {
        IEventBus MOD_BUS = FMLJavaModLoadingContext.get().getModEventBus();

        // make sure the client doesn't think it needs yep to connect
        ModLoadingContext.get().registerExtensionPoint(IExtensionPoint.DisplayTest.class, () -> new IExtensionPoint.DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        MinecraftForge.EVENT_BUS.register(EventListener.class);

        MOD_BUS.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Yep is enabled!");
    }
}
