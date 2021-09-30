package br.com.craftlife.satella.discord.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "server")
public class Server {

    @Id
    @Column(name = "id_server", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @OneToMany(mappedBy = "server", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Channel> channels = new ArrayList<>();

    @OneToMany(mappedBy = "server", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "server", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Reaction> reactions = new ArrayList<>();

    @OneToMany(mappedBy = "server", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<RoleGroup> roleGroups = new ArrayList<>();



}
