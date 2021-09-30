package br.com.craftlife.satella.discord.listener;

import br.com.craftlife.satella.discord.service.GuildService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AddReactionsListener implements EventListener {

    private final JDA jda;

    private final GuildService guildService;

    @SneakyThrows
    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent messageReceivedEvent) {
            val channel = messageReceivedEvent.getChannel();
            val guild = messageReceivedEvent.getGuild();
            val eventMessageOptional = guildService
                    .getWarningChannel(guild)
                    .filter(textChannel -> channel.getId().equalsIgnoreCase(textChannel.getId()))
                    .map(textChannel -> messageReceivedEvent.getMessage());
            eventMessageOptional.ifPresent(eventMessage -> guildService
                    .getReactionEmotes(guild)
                    .forEach(emote -> eventMessage.addReaction(emote).queue()));
        }
    }

}