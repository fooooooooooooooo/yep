package ooo.foooooooooooo.yep;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Yep implements DedicatedServerModInitializer {
    public static String MOD_ID = "yep";
    public static Identifier PLUGIN_CHANNEL = new Identifier("velocity", MOD_ID);

    public static Yep instance;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Yep() {
        instance = this;
    }

    @Override
    public void onInitializeServer() {
        EventListener.initialize();

        LOGGER.info("Yep is enabled!");
    }
}
