package ooo.foooooooooooo.yep;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.minecraft.advancement.Advancement;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import ooo.foooooooooooo.yep.api.AdvancementMessage;
import ooo.foooooooooooo.yep.api.DeathMessage;
import ooo.foooooooooooo.yep.events.AdvancementCallback;

public class EventListener {
  public static void initialize() {
    ServerLivingEntityEvents.AFTER_DEATH.register(EventListener::afterEntityDeath);
    AdvancementCallback.EVENT.register(EventListener::onAdvancement);
  }

  // TODO: there may be a better way to do this?
  private static void afterEntityDeath(LivingEntity entity, DamageSource damageSource) {
    if (entity instanceof ServerPlayerEntity player) {
      var username = player.getDisplayName().getString();
      var msg = damageSource.getDeathMessage(player);

      var message = msg.getString().replace(username + " ", "");

      PluginMessenger.sendMessage(player, new DeathMessage(username, message));
    }
  }

  public static void onAdvancement(ServerPlayerEntity player, Advancement advancement) {
    var display = advancement.getDisplay();

    if (display == null || display.isHidden()) {
      Yep.LOGGER.trace("Ignoring unsent display");

      return;
    }

    var username = player.getDisplayName().getString();
    var title = display.getTitle().getString();
    var description = display.getDescription().getString();

    PluginMessenger.sendMessage(player, new AdvancementMessage(username, title, description));
  }
}
