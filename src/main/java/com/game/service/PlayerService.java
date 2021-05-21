package com.game.service;

import com.game.entity.Player;

import java.util.Map;

public interface PlayerService {
    Player create(Map<String, String> params) throws Exception;
    Player findById(Long id);
    void delete(Player player);
    Player update(Player player);
}
