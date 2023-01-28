package ooo.foooooooooooo.yep;

import io.netty.buffer.Unpooled;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.s2c.play.CustomPayloadS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import ooo.foooooooooooo.yep.messages.IYepMessage;

import java.nio.charset.StandardCharsets;

public class PluginMessenger {
    public static void sendMessage(ServerPlayerEntity player, IYepMessage message) {
        var name = player.getName().getString();
        var type = message.getType().name();

        String msg = String.format("%s:%s:%s", name, type, message);

        Yep.LOGGER.trace("sent `" + msg + "` for player `" + name + "` of type `" + type + "` with message `" + message + "`");

        // TODO: wrappedBuffer instead of copiedBuffer is best here?
        var byteBuf = new PacketByteBuf(Unpooled.wrappedBuffer(msg.getBytes(StandardCharsets.UTF_8)));

        // i'd rather not deal with the travesty that is Forge's packet system
        // ...hopefully this doesn't change anytime soon though
        player.networkHandler.sendPacket(new CustomPayloadS2CPacket(Yep.PLUGIN_CHANNEL, byteBuf));
    }
}
