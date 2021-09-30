package br.com.craftlife.satella.discord.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleType {

    ADMIN("admin"), MODERATOR("moderador"), HELPER("ajudante"), MEMBER("membro");

    private final String descricao;

}
