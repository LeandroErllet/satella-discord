package br.com.craftlife.satella.discord.listener;

import br.com.craftlife.satella.discord.service.GuildService;
import lombok.AllArgsConstructor;
import lombok.val;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class OnlyMediaListener implements EventListener {

    private final JDA jda;

    private final GuildService guildService;

    @Override
    public void onEvent(@NotNull GenericEvent event) {
        if (event instanceof MessageReceivedEvent) {
            val messageReceivedEvent = (MessageReceivedEvent) event;
            val channel = messageReceivedEvent.getChannel();
            val guild = messageReceivedEvent.getGuild();
            val eventMessageOptional = guildService
                    .getMediaChannel(guild)
                    .filter(textChannel -> channel.getId().equalsIgnoreCase(textChannel.getId()))
                    .map(textChannel -> messageReceivedEvent.getMessage());
            eventMessageOptional.ifPresent(eventMessage -> {
                if(eventMessage.getAttachments().isEmpty() && !eventMessage.getAuthor().isBot()) {
                    eventMessage.delete().queue();
                }
            });
        }
    }

}