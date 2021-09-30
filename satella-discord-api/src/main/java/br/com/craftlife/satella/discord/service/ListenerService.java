package br.com.craftlife.satella.discord.service;


import net.dv8tion.jda.api.hooks.EventListener;

import java.util.List;

public interface ListenerService {
    List<EventListener> getEventListeners();
}
