package br.com.craftlife.satella.discord.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ChannelType {

    WARNING("aviso"), SUGGESTION("sugestão"), REPORT("denúncia"), COMMAND("comando"), MEDIA("media");

    private final String description;

}
