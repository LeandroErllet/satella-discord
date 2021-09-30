package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.model.CommandHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class CommandUtilServiceImpl implements CommandUtilService {

    @Getter
    private final List<CommandHandler> eventHandlers = new ArrayList<>();

    @Override
    public Optional<String> getTextFromArgument(String string, Integer arg, Integer limit) {
        return Optional.ofNullable(string)
                .map(s -> s.split(" ", limit))
                .filter(strings -> strings.length > 1)
                .map(strings -> strings[arg])
                .filter(s -> !s.isEmpty());
    }
}
