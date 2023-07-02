package ooo.foooooooooooo.yep;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import ooo.foooooooooooo.yep.api.AdvancementMessage;
import ooo.foooooooooooo.yep.api.DeathMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.Objects;

public class EventListener implements Listener {

  public EventListener() {
    Yep.instance.getServer().getMessenger().registerOutgoingPluginChannel(Yep.instance, "velocity:yep");

    Yep.logger.fine("Registered velocity:yep channel");
  }

  @EventHandler
  public void onPlayerDeath(PlayerDeathEvent event) {
    var username = event.getEntity().getName();
    var message = getComponentText(event.deathMessage()).replace(username + " ", "");

    PluginMessenger.sendMessage(event.getEntity(), new DeathMessage(username, message));
  }

  @EventHandler
  public void onAdvancement(PlayerAdvancementDoneEvent event) {
    var component = event.message();
    if (component == null) {
      Yep.logger.finest("Ignoring unsent advancement");
      return;
    }

    var advancement = event.getAdvancement().getDisplay();

    if (advancement == null) {
      return;
    }

    var username = event.getPlayer().getName();
    var title = getComponentText(advancement.title());
    var description = getComponentText(advancement.description());

    PluginMessenger.sendMessage(event.getPlayer(), new AdvancementMessage(username, title, description));
  }

  private String getComponentText(Component component) {
    return PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(component));
  }
}
