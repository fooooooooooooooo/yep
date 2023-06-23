package ooo.foooooooooooo.yep;

import com.google.common.base.Charsets;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import ooo.foooooooooooo.yep.messages.IYepMessage;

public class PluginMessenger {
    public static void sendMessage(EntityPlayerMP player, IYepMessage message) {
        var name = player.getCommandSenderName();
        var type = message.getType().name();

        String msg = String.format("%s:%s:%s", name, type, message);

        Yep.LOGGER.trace("sent `" + msg + "` for player `" + name + "` of type `" + type + "` with message `" + message + "`");

        var buf = new PacketBuffer(Unpooled.wrappedBuffer(msg.getBytes(Charsets.UTF_8)));

        player.playerNetServerHandler.sendPacket(new S3FPacketCustomPayload(Yep.PLUGIN_CHANNEL.toString(), buf));
    }
}
