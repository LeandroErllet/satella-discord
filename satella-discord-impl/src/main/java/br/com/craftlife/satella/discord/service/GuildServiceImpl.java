package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.model.ChannelType;
import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.model.RoleGroup;
import br.com.craftlife.satella.discord.model.Server;
import br.com.craftlife.satella.discord.repository.ServerRepository;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.requests.restaction.MessageAction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GuildServiceImpl implements GuildService {

    private final ServerRepository serverRepository;

    @SneakyThrows
    @Transactional
    @Override
    public Optional<TextChannel> getSuggestionChannel(Guild guild) {
        return serverRepository
                .findById(guild.getIdLong())
                .map(Server::getChannels)
                .orElseThrow(() -> new Exception("Guild não encontrada"))
                .stream()
                .filter(channel -> channel.getType().equals(ChannelType.SUGGESTION))
                .map(channel -> guild.getTextChannelById(channel.getId()))
                .findFirst();
    }


    @SneakyThrows
    @Transactional
    @Override
    public Optional<TextChannel> getWarningChannel(Guild guild) {
        return serverRepository
                .findById(guild.getIdLong())
                .map(Server::getChannels)
                .orElseThrow(() -> new Exception("Guild não encontrada"))
                .stream()
                .filter(channel -> channel.getType().equals(ChannelType.WARNING))
                .map(channel -> guild.getTextChannelById(channel.getId()))
                .findFirst();
    }

    @SneakyThrows
    @Transactional
    @Override
    public Optional<TextChannel> getMediaChannel(Guild guild) {
        return serverRepository
                .findById(guild.getIdLong())
                .map(Server::getChannels)
                .orElseThrow(() -> new Exception("Guild não encontrada"))
                .stream()
                .filter(channel -> channel.getType().equals(ChannelType.MEDIA))
                .map(channel -> guild.getTextChannelById(channel.getId()))
                .findFirst();
    }


    @SneakyThrows
    @Transactional
    @Override
    public Optional<TextChannel> getReportChannel(Guild guild) {
        return serverRepository
                .findById(guild.getIdLong())
                .map(Server::getChannels)
                .orElseThrow(() -> new Exception("Guild não encontrada"))
                .stream()
                .filter(channel -> channel.getType().equals(ChannelType.REPORT))
                .map(channel -> guild.getTextChannelById(channel.getId()))
                .findFirst();
    }


    @SneakyThrows
    @Transactional
    @Override
    public List<Emote> getReactionEmotes(Guild guild) {
        return serverRepository
                .findById(guild.getIdLong())
                .map(Server::getReactions)
                .orElseThrow(() -> new Exception("Guild não encontrada"))
                .stream()
                .map(channel -> guild.getEmoteById(channel.getId()))
                .collect(Collectors.toList());
    }

    @SneakyThrows
    @Transactional
    @Override
    public Optional<Role> getServersRolesFromContext(Context context) {
        val roles = serverRepository
                .findById(context.getEvent().getGuild().getIdLong())
                .map(Server::getRoleGroups)
                .orElseThrow(() -> new Exception("Guild não encontrada"))
                .stream()
                .map(roleGroup -> context.getEvent().getJDA().getRoleById(roleGroup.getRoleSourceID()))
                .filter(Objects::nonNull)
                .map(Role::getId)
                .collect(Collectors.toList());
        return context.getEvent().getMessage().getMentions()
                .stream()
                .map(iMentionable -> context.getEvent().getJDA().getRoleById(iMentionable.getId()))
                .filter(Objects::nonNull)
                .filter(role -> roles.contains(role.getId()))
                .findFirst();
    }


    @SneakyThrows
    @Transactional
    @Override
    public List<RoleGroup> getRoleGroup(Guild guild) {
        return new ArrayList<>(serverRepository
                .findById(guild.getIdLong())
                .map(Server::getRoleGroups)
                .orElseThrow(() -> new Exception("Guild não encontrada")));
    }


    @Override
    public void addEmotesToMessage(MessageAction messageAction, List<Emote> emotes) {
        messageAction.queue(msg -> emotes.forEach(emote -> msg.addReaction(emote).queue()));
    }
}
