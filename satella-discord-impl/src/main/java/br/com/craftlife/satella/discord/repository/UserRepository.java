package br.com.craftlife.satella.discord.repository;

import br.com.craftlife.satella.discord.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Integer> {
}
