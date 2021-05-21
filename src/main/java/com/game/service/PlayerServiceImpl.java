package com.game.service;

import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository repository;

    public PlayerServiceImpl(PlayerRepository repository) {
        this.repository = repository;
    }


    @Override
    public Player create(Map<String, String> params) throws Exception {
        // Проверяем входящий запрос
        String name = params.getOrDefault("name", null);
        String title = params.getOrDefault("title", null);
        Race race = Race.valueOf(params.get("race"));
        Profession profession = Profession.valueOf(params.get("profession"));

        long date = Long.parseLong(params.get("birthday"));
        if (date < 0)
            throw new Exception("[Error] PlayerService.create");
        Date birthday = new Date(date);

        Boolean banned = Boolean.parseBoolean(params.get("banned"));
        int experience = Integer.parseInt(params.get("experience"));

        // Запрос валидный, создаём нового игрока
        Player player = new Player(name, title, race, profession, birthday, banned, experience);

        // Сохраняем пользователя в БД и возвращаем ссылку на него
        return repository.save(player);
    }

    @Override
    public Player findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void delete(Player player) {
        repository.delete(player);
    }

    @Override
    public Player update(Player player) {
        return repository.save(player);
    }

}
