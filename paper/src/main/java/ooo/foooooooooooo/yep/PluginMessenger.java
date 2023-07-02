package ooo.foooooooooooo.yep;

import ooo.foooooooooooo.yep.messages.IYepMessage;
import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public class PluginMessenger {
    public static void sendMessage(Player player, IYepMessage message) {
        String msg = String.format("%s:%s:%s", player.getName(), message.getType().name(), message);
        player.sendPluginMessage(Yep.instance, "velocity:yep", msg.getBytes(StandardCharsets.UTF_8));
    }
}
