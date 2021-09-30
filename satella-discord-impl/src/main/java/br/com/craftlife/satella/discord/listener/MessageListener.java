package br.com.craftlife.satella.discord.listener;

import br.com.craftlife.satella.discord.service.CommandService;
import br.com.craftlife.satella.discord.model.Context;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class MessageListener extends ListenerAdapter {


    private final CommandService commandService;

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.getAuthor().isBot() || !event.isFromGuild()) {
            return;
        }
        String rawMessage = event.getMessage().getContentRaw();
        String[] split = rawMessage.split("^!");
        if (split.length > 1) {
            try {
                callCommandHandlers(new Context(event, split[1]));
            } catch (Exception e) {
                event.getChannel().sendMessage(e.getMessage()).queue();
            }
        }
    }

    private void callCommandHandlers(Context context) {
        commandService.findAppropriateHandler(context)
                .stream()
                .peek(commandHandler -> context.getEvent().getMessage().delete().queue())
                .forEach(handler -> handler.execute(context));
    }

}