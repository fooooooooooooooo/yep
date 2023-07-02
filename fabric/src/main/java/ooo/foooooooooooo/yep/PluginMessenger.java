package ooo.foooooooooooo.yep;

import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import ooo.foooooooooooo.yep.api.YepApi;
import ooo.foooooooooooo.yep.api.YepMessage;

import java.nio.charset.StandardCharsets;

public class PluginMessenger {
  public static void sendMessage(ServerPlayerEntity player, YepMessage message) {
    Yep.LOGGER.trace("sending `%s` for player `%s`".formatted(message, message.player));

    var serialized = YepApi.serializeMessage(message);

    // TODO: wrappedBuffer instead of copiedBuffer is best here?
    var byteBuf = new PacketByteBuf(Unpooled.wrappedBuffer(serialized.getBytes(StandardCharsets.UTF_8)));

    ServerPlayNetworking.send(player, Yep.PLUGIN_CHANNEL, byteBuf);
  }
}
