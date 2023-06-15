package ooo.foooooooooooo.yep;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AchievementEvent;
import ooo.foooooooooooo.yep.messages.AdvancementMessage;
import ooo.foooooooooooo.yep.messages.DeathMessage;

public class EventListener {
    @SubscribeEvent
    public void onDeathEvent(LivingDeathEvent event) {
        if (event.entityLiving instanceof EntityPlayerMP player) {
            var username = player.getDisplayName();
            // func_151519_b() = get death message from source
            var message = getComponentText(event.source.func_151519_b(player)).replace(username + " ", "");

            PluginMessenger.sendMessage(player, new DeathMessage(message));
        }
    }

    @SubscribeEvent
    public void onAchievementEvent(AchievementEvent event) {
        Achievement achievement = event.achievement;
        EntityPlayerMP player = (EntityPlayerMP) event.entityPlayer;

        // if ("announce-player-achievements" == true)
        if (player.mcServer.func_147136_ar()) {
            // if (player can unlock achievement (i.e. prereqs are met) && player does NOT already have achievement)
            if (player.func_147099_x().canUnlockAchievement(achievement) && !player.func_147099_x().hasAchievementUnlocked(achievement)) {
                // if the achievement is only sent to chat on the client
                if (achievement.isIndependent) {
                    Yep.LOGGER.trace("Ignoring local achievement");
                    return;
                }

                var title = getComponentText(new ChatComponentTranslation(achievement.statId));
                var description = getComponentText(new ChatComponentTranslation(achievement.statId + ".desc"));

                PluginMessenger.sendMessage(player, new AdvancementMessage(title, description));
            }
        }
    }

    private static String getComponentText(IChatComponent component) {
        return component.getUnformattedText();
    }
}
