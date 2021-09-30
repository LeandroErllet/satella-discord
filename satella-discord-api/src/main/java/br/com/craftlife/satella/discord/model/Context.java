package br.com.craftlife.satella.discord.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.val;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;


@Getter
@EqualsAndHashCode
public class Context {

	private String command;
	private String parameter;
	private MessageReceivedEvent event;

	public Context(MessageReceivedEvent event, String message) {
		this.event = event;
		if (!message.contains(" ")) {
			command = message;
		} else {
			val messageAndParam = message.split(" ", 2);
			command = messageAndParam[0];
			parameter = messageAndParam[1];
		}
	}

}
