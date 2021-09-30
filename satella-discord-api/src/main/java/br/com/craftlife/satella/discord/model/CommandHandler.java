package br.com.craftlife.satella.discord.model;


import lombok.Getter;
import lombok.val;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Arrays;
import java.util.List;

public abstract class CommandHandler {

	@Getter
	private final String name;
	@Getter
	private final List<String> alias;

	public CommandHandler() {
		val cmdAnnotation = this.getClass().getAnnotation(Command.class);
		this.name = cmdAnnotation.name();
		this.alias = Arrays.asList(cmdAnnotation.alias());
	}


	public abstract void execute(Context message);

	public abstract EmbedBuilder getHelp();
}
