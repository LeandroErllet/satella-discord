package br.com.craftlife.satella.discord.service;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.hooks.EventListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
@AllArgsConstructor
public class ListenerServiceImpl implements ListenerService {
	private final List<EventListener> eventListeners;

	private final JDA jda;

	@PostConstruct
	public void init() {
		eventListeners
				.stream()
				.filter(eventListener -> !eventListener.getClass().getName().contains("MessageListener"))
				.forEach(jda::addEventListener);
	}

	@Override
	public List<EventListener> getEventListeners() {
		return eventListeners;
	}
}
