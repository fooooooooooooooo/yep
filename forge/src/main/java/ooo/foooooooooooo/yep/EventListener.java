package ooo.foooooooooooo.yep;

import net.minecraft.advancement.AdvancementDisplay;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import ooo.foooooooooooo.yep.api.AdvancementMessage;
import ooo.foooooooooooo.yep.api.DeathMessage;

public class EventListener {
  @SubscribeEvent
  public static void onDeathEvent(LivingDeathEvent event) {
    if (event.getEntity() instanceof ServerPlayerEntity player) {
      var username = player.getDisplayName().getString();
      var message = getComponentText(event.getSource().getDeathMessage(player)).replace(username + " ", "");

      PluginMessenger.sendMessage(player, new DeathMessage(username, message));
    }
  }

  @SubscribeEvent
  public static void onAdvancementEvent(AdvancementEvent.AdvancementEarnEvent event) {
    AdvancementDisplay display = event.getAdvancement().getDisplay();

    if (display == null) {
      Yep.LOGGER.trace("Ignoring unsent display");
      return;
    }

    if (display.shouldAnnounceToChat()) {
      var player = event.getEntity();
      var title = getComponentText(display.getTitle());
      var description = getComponentText(display.getDescription());

      PluginMessenger.sendMessage((ServerPlayerEntity) player, new AdvancementMessage(player.getDisplayName().getString(), title, description));
    }
  }

  private static String getComponentText(Text component) {
    return component.getString();
  }
}
