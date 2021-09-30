package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.model.RoleGroup;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.Emote;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


public interface GuildService {
    @SneakyThrows
    @Transactional
    Optional<TextChannel> getSuggestionChannel(Guild guild);

    @SneakyThrows
    @Transactional
    Optional<TextChannel> getWarningChannel(Guild guild);

    @SneakyThrows
    @Transactional
    Optional<TextChannel> getMediaChannel(Guild guild);

    @SneakyThrows
    @Transactional
    Optional<TextChannel> getReportChannel(Guild guild);

    @SneakyThrows
    @Transactional
    List<Emote> getReactionEmotes(Guild guild);

    @SneakyThrows
    @Transactional
    Optional<Role> getServersRolesFromContext(Context context);

    @SneakyThrows
    @Transactional
    List<RoleGroup> getRoleGroup(Guild guild);

    void addEmotesToMessage(MessageAction messageAction, List<Emote> emotes);
}
