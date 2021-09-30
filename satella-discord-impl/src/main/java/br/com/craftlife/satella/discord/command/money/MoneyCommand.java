package br.com.craftlife.satella.discord.command.money;

import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.model.CommandHandler;
import br.com.craftlife.satella.discord.model.Command;
import br.com.craftlife.satella.discord.service.ListenerService;
import br.com.craftlife.satella.discord.service.MoneyService;
import br.com.craftlife.satella.discord.util.NetUtils;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.Objects;

@Component
@Command(name ="money" )
public class MoneyCommand extends CommandHandler {


    @Autowired
    private MoneyService moneyService;

    @Autowired
    private ListenerService commandEventHandlerService;

    @SneakyThrows
    @Override
    public void execute(Context context) {
        val eb = new EmbedBuilder();
        val name = NetUtils.encode(getNameFromContext(context));
        context.getEvent().getChannel().sendMessage("carregando...").queue(messageEmbed -> {
            eb.setTitle(String.format("Money do(a) jogador(a) %s", name), null);
            eb.setColor(Color.blue);
            eb.addField("Survival", moneyService.getMoneyInServer("survival", name), true);
            eb.addField("Factions", moneyService.getMoneyInServer("factions", name), true);
            eb.setThumbnail(String.format("https://minotar.net/avatar/%s/1280.png", name));
            eb.addField("KitPvP", moneyService.getMoneyInServer("kitpvp", name), true);
            eb.setAuthor("Satella", null, "https://i.imgur.com/ABJeWPY.png");
            val messageBuilder = new MessageBuilder();
            messageBuilder.setEmbed(eb.build());
            messageBuilder.setContent("Vamos ver se você é isso tudo mesmo");
            messageEmbed.addReaction("🤑").queue();
            messageEmbed.editMessage(messageBuilder.build()).queue();
        });
    }

    private String getNameFromContext(Context context) {
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