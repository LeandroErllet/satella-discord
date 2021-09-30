package br.com.craftlife.satella.discord.listener;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;
import org.jetbrains.annotations.NotNull;


@Slf4j
public class ReadyEventListener implements EventListener {

	@Override
	public void onEvent(@NotNull GenericEvent event) {
		if (event instanceof ReadyEvent) {
			log.info("Bot is ready!");
		}
	}

}