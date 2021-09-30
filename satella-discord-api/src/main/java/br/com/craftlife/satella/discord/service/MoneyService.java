package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.util.NetUtils;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;


public interface MoneyService {
    @SneakyThrows
    String getMoneyInServer(String server, String user);
}
