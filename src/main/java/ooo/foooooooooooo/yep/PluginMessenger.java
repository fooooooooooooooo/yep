package ooo.foooooooooooo.yep;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import ooo.foooooooooooo.yep.messages.IYepMessage;

import java.nio.charset.StandardCharsets;

public class PluginMessenger {
    public static void sendMessage(ServerPlayerEntity player, IYepMessage message) {
        var name = player.getDisplayName().getString();
        var type = message.getType().name();

        String msg = String.format("%s:%s:%s", name, type, message);

        Yep.LOGGER.trace("sent `" + msg + "` for player `" + name + "` of type `" + type + "` with message `" + message + "`");

        // wrappedBuffer instead of copiedBuffer is best here?
        var byteBuf = new PacketByteBuf(Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8)));

        ServerPlayNetworking.send(player, Yep.PLUGIN_CHANNEL, byteBuf);
    }
}
