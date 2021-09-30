package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.repository.UserRepository;
import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDA;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final JDA jda;


    @Override
    public void createUser() {

    }
}
