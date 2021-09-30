package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.model.CommandHandler;
import br.com.craftlife.satella.discord.model.Context;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



public interface CommandService {
	List<CommandHandler> findAppropriateHandler(Context context);

	List<CommandHandler> getEventHandlers();
}
