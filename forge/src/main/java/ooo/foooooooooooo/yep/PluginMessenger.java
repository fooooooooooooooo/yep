package ooo.foooooooooooo.yep;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
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

    // I'd rather not deal with the travesty that is Forge's packet system
    // ...hopefully this doesn't change anytime soon though
    player.networkHandler.sendPacket(new CustomPayloadS2CPacket(Yep.PLUGIN_CHANNEL, byteBuf));
  }
}
