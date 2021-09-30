package br.com.craftlife.satella.discord.repository;

import br.com.craftlife.satella.discord.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServerRepository extends JpaRepository<Server, Long> {
}
