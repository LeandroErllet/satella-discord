package br.com.craftlife.satella.discord.controller;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController()
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsersController {

    private final JDA jda;

    @GetMapping("/todos")
    public List<String> findAll() {
        return jda.getUsers()
                .stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }

    @GetMapping("/buscar-por-nome/{name}")
    public Optional<String> findByName(@PathVariable("name") String name) {
        return jda.getUsersByName(name, true)
                .stream()
                .map(User::getName)
                .findFirst();
    }
}
