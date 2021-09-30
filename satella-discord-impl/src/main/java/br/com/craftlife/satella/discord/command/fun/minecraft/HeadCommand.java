package br.com.craftlife.satella.discord.command.fun.minecraft;


import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.model.CommandHandler;
import br.com.craftlife.satella.discord.model.Command;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.net.URLEncoder;
import java.util.Objects;

@Component
@Command(name ="head", alias = {"cabeça"})
public class HeadCommand extends CommandHandler {

    @SneakyThrows
    @Override
    public void execute(Context context) {
        val eb = new EmbedBuilder();
        val skin = getSkinFromContext(context);
        eb.setTitle(String.format("Cabeça do(a) jogador(a) %s", skin), null);
        eb.setColor(Color.blue);
        eb.setAuthor("Satella", null, "https://i.imgur.com/ABJeWPY.png");
        eb.setImage(String.format("https://minotar.net/avatar/%s/1280.png", URLEncoder.encode(skin, "UTF-8")));
        context.getEvent().getChannel().sendMessage(eb.build()).queue();
    }

    private String getSkinFromContext(Context context) {
        return context.getEvent().getMessage().getMentions()
                .stream()
                .map(iMentionable -> context.getEvent().getJDA().getUserById(iMentionable.getId()))
                .filter(Objects::nonNull)
                .map(User::getName)
                .findFirst()
                .orElse(context.getParameter() == null ? context.getEvent().getAuthor().getName() : context.getParameter());
    }

    @Override
    public EmbedBuilder getHelp() {
        return null;
    }
}