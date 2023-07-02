package ooo.foooooooooooo.yep;

import ooo.foooooooooooo.yep.api.YepApi;
import ooo.foooooooooooo.yep.api.YepMessage;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public class PluginMessenger {
  public static void sendMessage(Player player, YepMessage message) {
    Yep.logger.finest("sending `%s` for player `%s`".formatted(message, message.player));

    var serialized = YepApi.serializeMessage(message);

    player.sendPluginMessage(Yep.instance, "velocity:yep", serialized.getBytes(StandardCharsets.UTF_8));
  }
}
