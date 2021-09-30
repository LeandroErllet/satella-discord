package br.com.craftlife.satella.discord.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reaction")
public class Reaction {

    @Id
    @Column(name = "id_reaction", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_server", nullable = false)
    private Server server;


}
