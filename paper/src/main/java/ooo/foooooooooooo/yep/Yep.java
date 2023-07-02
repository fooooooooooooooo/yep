package ooo.foooooooooooo.yep;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class Yep extends JavaPlugin {
  public static Yep instance;
  public static Logger logger;

  @Override
  public void onEnable() {
    instance = this;
    logger = getServer().getLogger();

    getServer().getPluginManager().registerEvents(new EventListener(), this);

    logger.info("Yep is enabled!");
  }

  @Override
  public void onDisable() {
    logger.info("Yep is disabled!");
  }
}
