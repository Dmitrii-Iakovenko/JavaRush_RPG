package com.game.service;

import com.game.controller.GetPlayersRequest;
import com.game.entity.Player;
import com.game.entity.Profession;
import com.game.entity.Race;
import com.game.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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


    @Override
    public List<Player> getPlayers(GetPlayersRequest request) throws Exception {
        return repository.findAll().stream()
                .filter(request::predicateName)
                .filter(request::predicateTitle)
                .filter(request::predicateRace)
                .filter(request::predicateProfession)
                .filter(request::predicateBirthday)
                .filter(request::predicateBanned)
                .filter(request::predicateExperience)
                .filter(request::predicateLevel)
                .sorted(request.getComparator())
                .skip(request.skip())
                .limit(request.limit())
                .collect(Collectors.toList());
    }

    @Override
    public Integer getCount(GetPlayersRequest request) {
        return Math.toIntExact(repository.findAll().stream()
                .filter(request::predicateName)
                .filter(request::predicateTitle)
                .filter(request::predicateRace)
                .filter(request::predicateProfession)
                .filter(request::predicateBirthday)
                .filter(request::predicateBanned)
                .filter(request::predicateExperience)
                .filter(request::predicateLevel)
                .count());
    }


}
