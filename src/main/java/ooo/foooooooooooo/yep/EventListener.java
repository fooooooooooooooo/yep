package ooo.foooooooooooo.yep;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import ooo.foooooooooooo.yep.events.AdvancementCallback;
import ooo.foooooooooooo.yep.messages.AdvancementMessage;
import ooo.foooooooooooo.yep.messages.DeathMessage;

public class EventListener {
    public static void initialize() {
        ServerLivingEntityEvents.ALLOW_DEATH.register((entity, damageSource, damageAmount) -> {
            EventListener.onEntityDeath(entity, damageSource);
            return true;
        });
        AdvancementCallback.EVENT.register(EventListener::onAdvancement);
    }

    // TODO: there may be a better way to do this?
    private static void onEntityDeath(LivingEntity entity, DamageSource damageSource) {
        if (entity instanceof ServerPlayerEntity player) {
            var name = player.getName().getString();
            var message = getComponentText(damageSource.getDeathMessage(player)).replace(name + " ", "");

            PluginMessenger.sendMessage(player, new DeathMessage(message));
        }
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
