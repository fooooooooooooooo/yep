package ooo.foooooooooooo.yep;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.minecraft.util.Identifier;
import ooo.foooooooooooo.yep.api.YepApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Yep implements DedicatedServerModInitializer {
  public static final String MOD_ID = "yep";
  public static final Identifier PLUGIN_CHANNEL = new Identifier(YepApi.NAMESPACE, YepApi.PATH);
  public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

  @Override
  public void onInitializeServer() {
    EventListener.initialize();

    LOGGER.info("Yep is enabled!");
  }
}
