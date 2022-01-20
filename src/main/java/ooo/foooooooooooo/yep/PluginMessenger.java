package ooo.foooooooooooo.yep;

import org.bukkit.entity.Player;

import java.nio.charset.StandardCharsets;

public class PluginMessenger {
    public static void sendMessage(Player player, MessageType type, String message) {
        String msg = String.format("%s:%s", type.name(), message);
        player.sendPluginMessage(Yep.instance, "velocity:yep", msg.getBytes(StandardCharsets.UTF_8));
    }
}
