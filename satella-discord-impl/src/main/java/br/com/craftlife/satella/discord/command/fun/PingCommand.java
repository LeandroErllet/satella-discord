package br.com.craftlife.satella.discord.command.fun;


import br.com.craftlife.satella.discord.model.Command;
import br.com.craftlife.satella.discord.model.CommandHandler;
import br.com.craftlife.satella.discord.model.Context;
import lombok.SneakyThrows;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
@Command(name ="ping" )
public class PingCommand extends CommandHandler {

    @SneakyThrows
    @Override
    public void execute(Context context) {
        val eb = new EmbedBuilder();
        eb.setTitle("Skin do(a) jogador(a) %s", null);
        eb.setColor(Color.red);
        eb.setColor(new Color(0xF40C0C));
        eb.setColor(new Color(255, 0, 54));
        eb.setAuthor("Satella", null, "https://i.imgur.com/ABJeWPY.png");
         context.getEvent().getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public EmbedBuilder getHelp() {
        return null;
    }
}