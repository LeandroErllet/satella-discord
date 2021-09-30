package br.com.craftlife.satella.discord.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "member")
public class Member {

    @Id
    @Column(name = "id_member", unique = true, nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @ManyToOne
    @JoinColumn(name = "id_server", nullable = false)
    private Server server;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_type", nullable = false, columnDefinition = "smallint")
    private RoleType type = RoleType.MEMBER;


}
