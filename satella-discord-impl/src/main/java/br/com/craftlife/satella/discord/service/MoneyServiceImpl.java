package br.com.craftlife.satella.discord.service;

import br.com.craftlife.satella.discord.util.NetUtils;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Optional;

@Service
public class MoneyServiceImpl implements MoneyService {


    @SneakyThrows
    @Override
    public String getMoneyInServer(String server, String user) {
        val format = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        val balance = Optional.ofNullable(NetUtils.getJSON(String.format("https://api.craftlife.com.br/money/%s/%s",
                NetUtils.encode(server),
                NetUtils.encode(user))))
                .map(jsonObject -> jsonObject.getInt("balance"))
                .orElse(0);
        return format.format(balance);
    }
}
