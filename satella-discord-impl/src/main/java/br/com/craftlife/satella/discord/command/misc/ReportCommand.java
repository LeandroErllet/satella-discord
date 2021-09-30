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
import org.apache.commons.validator.routines.UrlValidator;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
@Command(name = "denunciar", alias = "report")
@AllArgsConstructor
public class ReportCommand extends CommandHandler {

    private final CommandUtilService commandUtilService;

    private final GuildService guildService;


    @SneakyThrows
    @Override
    public void execute(Context context) {
        val role = guildService
                .getServersRolesFromContext(context)
                .orElseThrow(() -> new Exception("Cargo não encontrado use: !denunciar <servidor> <player> <provas> <motivo>"));
        val player = commandUtilService
                .getTextFromArgument(context.getParameter(), 1, 4)
                .orElseThrow(() -> new Exception("sem player use !denunciar <servidor> <player> <provas> <motivo>"));
        val evidences = commandUtilService
                .getTextFromArgument(context.getParameter(), 2, 4)
                .filter(s -> new UrlValidator().isValid(s))
                .orElseThrow(() -> new Exception("sem provas ou não é uma imagem valida use: !denunciar <servidor> <player> <provas> <motivo>"));
        val message = commandUtilService
                .getTextFromArgument(context.getParameter(), 3, 4)
                .orElseThrow(() -> new Exception("Sem mensagem use !denunciar <servidor> <player> <provas> <motivo>"));
        val guild = context.getEvent()
                .getGuild();
        val emotes = guildService
                .getReactionEmotes(guild);
        val suggestionChannel = guildService
                .getReportChannel(guild)
                .orElseThrow(() -> new Exception("Canal de denuncias não encontrado!"));
        val embedBuilder = getBuilder(context, role, evidences, player, message);
        guildService.addEmotesToMessage(suggestionChannel.sendMessage(embedBuilder.build()), emotes);
    }

    @NotNull
    private EmbedBuilder getBuilder(Context context, Role role, String evidences, String player, String message) {
        val embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(String.format("Denuncia no Servidor: %s", role.getName()));
        embedBuilder.setColor(role.getColor());
        embedBuilder.setAuthor(context.getEvent().getAuthor().getName());
        embedBuilder.setThumbnail(context.getEvent().getAuthor().getAvatarUrl());
        embedBuilder.setImage(evidences);
        embedBuilder.addField("denunciado", player, true);
        embedBuilder.addField("motivo", message, true);
        return embedBuilder;
    }


    @Override
    public EmbedBuilder getHelp() {
        return null;
    }
}