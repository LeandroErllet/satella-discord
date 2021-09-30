package br.com.craftlife.satella.discord.command.misc;

import br.com.craftlife.satella.discord.model.Command;
import br.com.craftlife.satella.discord.model.CommandHandler;
import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.service.CommandUtilService;
import br.com.craftlife.satella.discord.service.GuildService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Command(name = "sugestao", alias = "suggestion")
@AllArgsConstructor
public class SuggestionCommand extends CommandHandler {

    private final CommandUtilService commandUtilService;

    private final GuildService guildService;



    @SneakyThrows
    @Override
    public void execute(Context context) {
        val role = guildService
                .getServersRolesFromContext(context)
                .orElseThrow(() -> new Exception("Cargo não encontrado use: !sugestao <cargo> <mensageem>"));
        val message = commandUtilService
                .getTextFromArgument(context.getParameter(), 1, 2)
                .orElseThrow(() -> new Exception("Sem mensagem use !sugestao <cargo> <mensageem>"));
        val guild = context.getEvent()
                .getGuild();
        val emotes = guildService
                .getReactionEmotes(guild);
        val embedBuilder = getBuilder(context, role, message);
        val suggestionChannel = guildService.getSuggestionChannel(guild)
                .orElseThrow(() -> new Exception("Canal de sugestões não encontrado!"));
        guildService.addEmotesToMessage(suggestionChannel.sendMessage(embedBuilder.build()), emotes);

    }

    @NotNull
    private EmbedBuilder getBuilder(Context context, Role role, String message) {
        val embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(String.format("Sugestão para o servidor: %s", role.getName()));
        embedBuilder.setColor(role.getColor());
        embedBuilder.setAuthor(context.getEvent().getAuthor().getName());
        embedBuilder.setThumbnail(context.getEvent().getAuthor().getAvatarUrl());
        embedBuilder.addField("sugestão", message, true);
        return embedBuilder;
    }


    @Override
    public EmbedBuilder getHelp() {
        return null;
    }
}