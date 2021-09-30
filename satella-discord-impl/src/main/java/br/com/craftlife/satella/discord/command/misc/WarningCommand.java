package br.com.craftlife.satella.discord.command.misc;

import br.com.craftlife.satella.discord.model.Command;
import br.com.craftlife.satella.discord.model.CommandHandler;
import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.model.RoleGroup;
import br.com.craftlife.satella.discord.service.CommandUtilService;
import br.com.craftlife.satella.discord.service.GuildService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Role;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Component
@Command(name = "aviso", alias = "warning")
@AllArgsConstructor
public class WarningCommand extends CommandHandler {


    private final CommandUtilService commandUtilService;

    private final GuildService guildService;


    @SneakyThrows
    @Override
    public void execute(Context context) {
        val role = guildService
                .getServersRolesFromContext(context)
                .filter(option -> context.getEvent().getChannel().getId().equalsIgnoreCase("392298426244530176"))
                .orElseThrow(() -> new Exception("Cargo não encontrado use: !aviso <cargo> <mensageem>"));
        val message = commandUtilService
                .getTextFromArgument(context.getParameter(), 1, 2)
                .orElseThrow(() -> new Exception("Sem mensagem use !aviso <cargo> <mensageem>"));
        val guild = context.getEvent().getGuild();
        val roleGroup = guildService
                .getRoleGroup(guild)
                .stream()
                .filter(source -> source.getRoleSourceID().equalsIgnoreCase(role.getId()))
                .findFirst()
                .orElseThrow(() -> new Exception("Cargo não encontrado use: !aviso <cargo> <mensageem>"));;
        val embedBuilder = getEmbedBuilder(roleGroup, role, message);
        val warningChannel = guildService.getWarningChannel(guild)
                .orElseThrow(() -> new Exception("Canal de avisos não encontrado!"));
        val messageBuilder = new MessageBuilder();
        messageBuilder.setEmbed(embedBuilder.build());
        messageBuilder.setContent(Objects.requireNonNull(guild.getRoleById(roleGroup.getRoleResultID())).getAsMention());
        warningChannel.sendMessage(messageBuilder.build()).queue();
    }

    @NotNull
    @Transactional
    EmbedBuilder getEmbedBuilder(RoleGroup roleGroup, Role role, String message) {
        val embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(String.format("Aviso do Servidor: %s", role.getName()));
        embedBuilder.setColor(role.getColor());
        embedBuilder.setAuthor(roleGroup.getServer().getName());
        embedBuilder.setThumbnail(roleGroup.getImageLink());
        embedBuilder.addField("Mensagem:", message, false);
        return embedBuilder;
    }

    @Override
    public EmbedBuilder getHelp() {
        return null;
    }
}