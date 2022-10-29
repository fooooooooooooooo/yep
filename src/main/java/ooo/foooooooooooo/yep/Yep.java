package ooo.foooooooooooo.yep;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Yep implements ModInitializer {

    public static String MOD_ID = "yep";
    public static Identifier PLUGIN_CHANNEL = new Identifier("velocity", MOD_ID);

    public static Yep instance;
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public Yep() {
        instance = this;
    }

    @Override
    public void onInitialize() {
        EventListener.initialize();

        System.out.println("SHITFUCK SHIT FUCK SHIT FUCK");

        LOGGER.info("Yep is enabled!");
    }
}
