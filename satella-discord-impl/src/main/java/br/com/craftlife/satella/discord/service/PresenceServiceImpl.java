package br.com.craftlife.satella.discord.service;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
public class PresenceServiceImpl implements PresenceService {

    private static final String[] TIPS_MESSAGES = {"Use !ajuda para ajuda!",
            String.format("Ajude a me manter online compre VIP %s", "https://www.craftlife.com.br/")};


    private final JDA jda;

    @Scheduled(fixedDelay = 1000 * 60)
    private void autoUpdatePresence() {
        updatePresence(String.format("Dica | %s"
                , TIPS_MESSAGES[ThreadLocalRandom.current().nextInt(TIPS_MESSAGES.length)]));
    }

    @Override
    public void updatePresence(String text) {
        jda.getPresence()
                .setPresence(Activity.streaming(text, "https://www.twitch.tv/gaules"), true);
    }
}
