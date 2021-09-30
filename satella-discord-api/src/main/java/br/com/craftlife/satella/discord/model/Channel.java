package br.com.craftlife.satella.discord.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "channel")
public class Channel {

    @Id
    @Column(name = "id_channel", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_server", nullable = false)
    private Server server;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "channel_type", nullable = false, columnDefinition = "smallint")
    private ChannelType type;


}
