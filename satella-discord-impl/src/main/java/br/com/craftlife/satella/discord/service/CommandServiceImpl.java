package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.model.Context;
import br.com.craftlife.satella.discord.model.CommandHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CommandServiceImpl implements CommandService {

	private final List<CommandHandler> eventHandlers;

	private final CommandUtilServiceImpl commandUtilService;

	@Override
	public List<CommandHandler> findAppropriateHandler(Context context) {
		return getEventHandlers()
				.stream()
				.filter(handler -> handler.getName().equalsIgnoreCase(context.getCommand())
						|| handler.getAlias().stream().anyMatch(s -> s.equalsIgnoreCase(context.getCommand())))
				.peek(commandHandler -> commandUtilService.getEventHandlers().add(commandHandler))
				.collect(Collectors.toList());
	}

	@Override
	public List<CommandHandler> getEventHandlers() {
		return eventHandlers;
	}
}
