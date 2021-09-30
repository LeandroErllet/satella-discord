package br.com.craftlife.satella.discord.service;

import java.util.Optional;


public interface CommandUtilService {
    Optional<String> getTextFromArgument(String string, Integer arg, Integer limit);
}
