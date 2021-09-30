package br.com.craftlife.satella.discord.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "role_group")
public class RoleGroup {

    @Id
    @Column(name = "id_role_group", unique = true, nullable = false)
    private int id;

    @Column(name = "role_source_id", nullable = false, length = 80)
    private String roleSourceID;

    @Column(name = "role_result_id", nullable = false, length = 80)
    private String roleResultID;

    @Column(name = "image_link", nullable = false, length = 80)
    private String imageLink;

    @ManyToOne
    @JoinColumn(name = "id_server", nullable = false)
    private Server server;


}
