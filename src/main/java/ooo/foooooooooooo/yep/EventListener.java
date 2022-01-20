package ooo.foooooooooooo.yep;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.md_5.bungee.chat.TranslationRegistry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

import java.util.Objects;

public class EventListener implements Listener {

    public EventListener() {
        Yep.instance.getServer().getMessenger().registerOutgoingPluginChannel(Yep.instance, "velocity:yep");
        Yep.logger.fine("Registered velocity:yep channel");
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        var translated = getTranslatedMessage(event.deathMessage());

        var result = String.format(translated, event.getEntity().getName());

        PluginMessenger.sendMessage(event.getPlayer(), MessageType.DEATH, result);
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        var component = event.message();
        if (component == null) {
            Yep.logger.fine("Ignoring unsent advancement");
            return;
        }

        var translated = getTranslatedMessage(component);

        var advancementDisplay = event.getAdvancement().getDisplay();

        if (advancementDisplay == null) {
            Yep.logger.info("Advancement display is null");
            return;
        }

        var translatedAdvancement = getTranslatedMessage(advancementDisplay.title().asComponent());

        if (translatedAdvancement == null) {
            Yep.logger.warning("Failed to translate advancement: " + getMessageString(advancementDisplay.title().asComponent()));
            return;
        }

        String result = String.format(translated, event.getPlayer().getName(), translatedAdvancement);

        PluginMessenger.sendMessage(event.getPlayer(), MessageType.ADVANCEMENT, result);
    }

    private String getTranslatedMessage(Component component) {
        return TranslationRegistry.INSTANCE.translate(
                getMessageString(component)
        );
    }

    private String getMessageString(Component component) {
        return PlainTextComponentSerializer.plainText().serialize(Objects.requireNonNull(component));
    }
}
