package ooo.foooooooooooo.yep;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ooo.foooooooooooo.yep.messages.AdvancementMessage;
import ooo.foooooooooooo.yep.messages.DeathMessage;

public class EventListener {
    public static void initialize() {
        ServerPlayerEvents.ALLOW_DEATH.register((player, source, damageAmount) -> {
            EventListener.onPlayerDeath(player, source);
            return true;
        });
    }

    private static void onPlayerDeath(ServerPlayerEntity player, DamageSource source) {
        var name = player.getDisplayName().getString();
        var message = getComponentText(source.getDeathMessage(player)).replace(name + " ", "");

        PluginMessenger.sendMessage(player, new DeathMessage(message));
    }

    public static void onAdvancement(ServerPlayerEntity player, Advancement advancement) {
        var display = advancement.getDisplay();

        if (display == null || display.isHidden()) {
            Yep.LOGGER.trace("Ignoring unsent display");
            return;
        }

        var title = getComponentText(display.getTitle());
        var description = getComponentText(display.getDescription());

        PluginMessenger.sendMessage(player, new AdvancementMessage(title, description));
    }

    private static String getComponentText(Text component) {
        return component.getString();
    }
}
